package com.yyq.springTest1.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-07-25 17:10
 */
@Configuration
@ComponentScan({"com.yyq.springTest1.service", "com.yyq.springTest1.dao"})
public class SpringConfig {

}
