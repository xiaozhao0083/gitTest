package com.yc.springTest2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-07-26 10:02
 */
@Configuration
@ComponentScan("com.yc.springTest2")
@PropertySource("classpath:com/yc/springTest3/pojo/db.properties")
@Import({JdbcConfig.class})
public class SpringConfig {
}
