package com.yc.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * @program: API
 * description:
 * @author:yyq
 * @create: 2023-07-03 14:53
 */
@Data
@Log4j2
public class JdbcConfig {
    @Value("${mysql.driver}")
    private String driver;
    @Value("${mysql.url}")
    private String url;
    @Value("${mysql.username}")
    private String username;
    @Value("${mysql.password}")
    private String password;
    @Value("#{T(Runtime).getRuntime().availableProcessors()*2}")
    private int cpuCount;

    @Bean
    public DataSource dataSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setInitialSize(cpuCount);
        log.info("配置druid的连接池大小："+cpuCount);
        ds.setMaxActive(cpuCount*2);
        return ds;
    }

}
