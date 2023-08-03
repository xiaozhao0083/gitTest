package org.ycframework.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.ycframework.annotation.*;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Filter;

public class YcAnnotationConfigApplicationContext implements YcApplicationContext {
    private Logger logger = LoggerFactory.getLogger(YcAnnotationConfigApplicationContext.class);

    //存每个 带托管的bean的定义信息
    private Map<String, YcBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    //存每个 实例化的bean
    private Map<String, Object> beanMap = new HashMap<>();
    //存系统属性  db。properties
    private Properties pros;

    public YcAnnotationConfigApplicationContext(Class... configClasses) {
        try {

            //读取系统属性   存好
            pros = System.getProperties();
            List<String> toScanPackagePath = new ArrayList<>();
            for (Class cls : configClasses) {
                if (cls.isAnnotationPresent(YcConfiguration.class) == false) {
                    continue;
                }
                String[] basePackages = null;
                //扫描配置上的 @YcComponentScan注解 读取要扫描的包
                if (cls.isAnnotationPresent(YcComponentScan.class)) {
                    //如果   则说明此配置类上有@YcComponentScan 则读取 basePackages
                    YcComponentScan ycComponentScan = (YcComponentScan) cls.getAnnotation(YcComponentScan.class);
                    basePackages = ycComponentScan.basePackages();
                    if (basePackages == null || basePackages.length <= 0) {
                        basePackages = new String[1];
                        basePackages[0] = cls.getPackage().getName();
                    }
                    logger.info(cls.getName() + "类上由@YcComponentScan注解   它要扫描的路径：" + basePackages[0]);
                }
                //开始扫描basePackages包下的bean，加载包装成BeanDefinition对象，存到beanDefinition
                recursiveLoadBeanDefinition(basePackages);
            }
            //循环存到beanDefinition 创建bean（是否单例和懒加载）存到beanmap
            creatBean();
            //循环所有的托管的beanMap中的bean 看属性和方法是否有@Autowire，@Resource，@Value
            doDi();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 循环所有的托管的beanMap中的bean 看属性和方法是否有@Autowire，@Resource，@Value
     */
    private void doDi() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        //循环的是 beanMap 这是托管Bean
        for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
            String beadId = entry.getKey();
            Object beanObj = entry.getValue();
            //情况一：属性上有 @YcResource注解的情况
            //TODO 情况二：方法有@YcResource注解的情况
            //TODO 情况三：构造方法有@YcResource注解的情况
            Field[] fields = beanObj.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(YcResource.class)) {
                    YcResource ycResource = field.getAnnotation(YcResource.class);
                    String toDiBeanId = ycResource.name();
                    //从beanMap找  singleton lazy
                    Object obj = getToDiBean(toDiBeanId);

                    //注入
                    field.setAccessible(true);
                    field.set(beanObj, obj);
                }
            }
        }
    }

    /**
     * 从beanMap找  singleton lazy
     *
     * @param toDiBeanId
     * @return
     */
    private Object getToDiBean(String toDiBeanId) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (beanMap.containsKey(toDiBeanId)) {
            return beanMap.get(toDiBeanId);
        }
        //判断beanMap中没有此bean是否为lazy
        if (!beanDefinitionMap.containsKey(toDiBeanId)) {
            throw new RuntimeException("spring容器中没有加载class:" + toDiBeanId);
        }
        YcBeanDefinition bd = beanDefinitionMap.get(toDiBeanId);
        if (bd.isLazy()) {
            //是因为懒   所有没有托管
            String classpath = bd.getClassInfo();
            Object beanobj = Class.forName(classpath).newInstance();
            beanMap.put(toDiBeanId, beanobj);
            return beanobj;
        }
        //是否因为prototype
        if (bd.getScope().equalsIgnoreCase("prototype")) {
            //是因为多例   所有没有托管
            String classpath = bd.getClassInfo();
            Object beanobj = Class.forName(classpath).newInstance();
            //beanMap.put(toDiBeanId,beanobj);
            return beanobj;
        }
        return null;
    }

    /**
     * 循环存到beanDefinition 创建bean（是否单例和懒加载）存到beanmap
     */
    private void creatBean() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        for (Map.Entry<String, YcBeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanId = entry.getKey();
            YcBeanDefinition ybd = (YcBeanDefinition) entry.getValue();
            if (!ybd.isLazy() && !ybd.getScope().equalsIgnoreCase("prototype")) {
                String classInfo = ybd.getClassInfo();
                Object obj = Class.forName(classInfo).newInstance();
                beanMap.put(beanId, obj);
                logger.trace("spring托管了：" + beanId + "=====>" + classInfo);
            }
        }
    }

    /**
     * 开始扫描basePackages包下的bean，加载包装成BeanDefinition对象，存到beanDefinition
     *
     * @param basePackages
     */
    private void recursiveLoadBeanDefinition(String[] basePackages) {
        for (String basePackage : basePackages) {
            //将 . 替换成 路径的 /
            String packagePath = basePackage.replaceAll("\\.", "/");
            //target/class  /com/yc
            //Enumeration集合
            try {
                Enumeration<URL> files = Thread.currentThread().getContextClassLoader().getResources(packagePath);
                //循环这个files,看是否是要加载的资源

                while (files.hasMoreElements()) {
                    URL url = files.nextElement();
                    logger.trace("当前正在递归加载:" + url.getFile());
                    //查找此包的类 com/yc全路径 com/yc包名
                    findPackageClasses(url.getFile(), basePackage);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void findPackageClasses(String packagePath, String basePackage) {
        //路径异常的处理 ，前面由 / 则去掉他
        if (packagePath.startsWith("/")) {
            packagePath = packagePath.substring(1);
        }
        //取这个路径下所有的字节码文件（目录下有其他资源）
        File file = new File(packagePath);
        //只取后缀名为 .class的字节码
        //写法一
        //TODO 视频
        //写法二：lambda
        File[] classFiles = file.listFiles((pathname) -> {
            if (pathname.getName().endsWith(".class") || pathname.isDirectory()) {
                return true;
            }
            return false;
        });
        //循环classFiles
        if (classFiles == null || classFiles.length <= 0) {
            return;
        }
        for (File cf : classFiles) {
            if (cf.isDirectory()) {
                //继续递归
                logger.trace("递归:" + cf.getAbsolutePath() + ",它对应的包名为：" + (basePackage + "." + cf.getName()));
                findPackageClasses(cf.getAbsolutePath(), basePackage + "." + cf.getName());
            } else {
                //是class文件 则取出文件 判断文件对应的class中是否有 @Component注解
                URLClassLoader uc = new URLClassLoader(new URL[]{});
                //    UserDaoImpl.class
                Class cls = null;
                try {
                    cls = uc.loadClass(basePackage + "." + cf.getName().replaceAll(".class", ""));
                    //TODO 可以支持 YcComponent 字注解
                    if (cls.isAnnotationPresent(YcComponent.class)
                            || cls.isAnnotationPresent(YcConfiguration.class)
                            || cls.isAnnotationPresent(YcController.class)
                            || cls.isAnnotationPresent(YcRepository.class)
                            || cls.isAnnotationPresent(YcService.class)
                    ) {
                        logger.info("加载到一个待托管的类：" + cls.getName());
                        //TODO 包装成BeanDefinition
                        YcBeanDefinition bd = new YcBeanDefinition();
                        if (cls.isAnnotationPresent(YcLazy.class)) {
                            bd.setLazy(true);
                        }
                        if (cls.isAnnotationPresent(YcScope.class)) {
                            YcScope ycScope = (YcScope) cls.getAnnotation(YcScope.class);
                            String scope = ycScope.value();
                            bd.setScope(scope);
                        }
                        //classInfo中保存这个托管的类的包路径 com.yc.dao.UserDaoImpl
                        //cls.newInstance()
                        bd.setClassInfo(basePackage + "." + cf.getName().replaceAll(".class", ""));
                        //存到 beanDefinition 中 "beanId"--->BeanDefinition对象
                        String beanId = getBeanId(cls);
                        this.beanDefinitionMap.put(beanId, bd);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getBeanId(Class cls) {
        YcComponent ycComponent = (YcComponent) cls.getAnnotation(YcComponent.class);
        YcController ycController = (YcController) cls.getAnnotation(YcController.class);
        YcService ycService = (YcService) cls.getAnnotation(YcService.class);
        YcRepository ycRepository = (YcRepository) cls.getAnnotation(YcRepository.class);

        YcConfiguration ycConfiguration = (YcConfiguration) cls.getAnnotation(YcConfiguration.class);

        if (ycConfiguration != null) {
            return cls.getName(); //全路径
        }
        String beanId = null;
        if (ycComponent != null) {
            beanId = ycComponent.value();
        } else if (ycController != null) {
            beanId = ycController.value();
        } else if (ycService != null) {
            beanId = ycService.value();
        } else if (ycRepository != null) {
            beanId = ycRepository.value();
        }
        if (beanId == null || "".equalsIgnoreCase(beanId)) {
            String typename = cls.getSimpleName();
            beanId = typename.substring(0, 1).toLowerCase() + typename.substring(1);
        }
        return beanId;
    }

    @Override
    public Object getBean(String beanid) {
        YcBeanDefinition bd = beanDefinitionMap.get(beanid);
        if (bd == null) {
            throw new RuntimeException("容器中没有加载次bean");
        }
        String scope = bd.getScope();
        if ("prototype".equalsIgnoreCase(scope)) {
            Object obj = null;
            try {
                obj = Class.forName(bd.getClassInfo()).newInstance();
                return obj;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (beanMap.containsKey(beanid)) {
            return beanMap.get(beanid);
        }
        if (bd.isLazy()) {
            Object obj = null;
            try {
                obj = Class.forName(bd.getClassInfo()).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        }
        return null;
    }
}
