package org.ycframework.context;

import lombok.Data;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-07-27 20:03
 */
@Data
public class YcBeanDefinition {
    private boolean isLazy;
    private String scope = "sigleton";
    private boolean isPrimary;
    private String classInfo;//类的实例信息
    private  BeanDefinition beanDefinition;
}
