package org.ycframework.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ycframework.annotation.YcComponentScan;
import org.ycframework.annotation.YcConfiguration;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-07-27 18:43
 */
public class YcAnnotationConfigApplicationContext implements YcApplicationContext {
    private Logger logger= LoggerFactory.getLogger(YcAnnotationConfigApplicationContext.class);
    private Map<String,YcBeanDefinition> beanDefinitionMap=new HashMap<>();
    private  Map<String,Object> beanMap=new HashMap<>();
    private Properties pros;

    public YcAnnotationConfigApplicationContext(Class...configClasses){
        pros=System.getProperties();
        List<String> toscanPackagePath=new ArrayList<>();
        for (Class cls: configClasses){
            if(cls.isAnnotationPresent(YcConfiguration.class)==false){
                continue;
            }
            if(cls.isAnnotationPresent(YcComponentScan.class)){
                YcComponentScan ycComponentScan = (YcComponentScan) cls.getAnnotation(YcComponentScan.class);
                String[] basePackages=ycComponentScan.basePackages();
                if(basePackages==null||basePackages.length<0){
                    basePackages=new String[1];
                    basePackages[0]=cls.getPackage().getName();
                }
                logger.info(cls.getName()+"类上有@YcComponentScan注解，他要扫描的路径:"+basePackages[0]);
            }


        }



    }


    @Override
    public Object getBean(String beanid) {
        return null;
    }


}
