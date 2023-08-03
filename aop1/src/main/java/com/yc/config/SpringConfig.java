package com.yc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-08-01 10:04
 */
@Configuration
@ComponentScan("com.yc")
@EnableAspectJAutoProxy
public class SpringConfig {
}
