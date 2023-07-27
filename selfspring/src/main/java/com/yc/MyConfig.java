package com.yc;

import org.ycframework.annotation.YcComponent;
import org.ycframework.annotation.YcComponentScan;
import org.ycframework.annotation.YcConfiguration;
import org.ycframework.annotation.YcPropertySource;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-07-27 15:16
 */
@YcComponent
@YcConfiguration
@YcComponentScan(basePackages = {"com.yc","com.yc2"})
@YcPropertySource(value = "db.properties")
public class MyConfig {
}
