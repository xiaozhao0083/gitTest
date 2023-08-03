package com.yc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-08-02 14:36
 */
@Configuration
@ComponentScan("com.yc")
@PropertySource("classpath:db.properties")
@Import({JdbcConfig.class})
public class SpringConfig {
}
