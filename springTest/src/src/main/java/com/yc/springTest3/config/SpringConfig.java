package com.yc.springTest3.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-07-26 10:02
 */
@Configuration
@ComponentScan("com.yc.springTest3")
@PropertySource("classpath:db.properties")
public class SpringConfig {
}
