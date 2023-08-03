package com.yc;

import com.yc.config.JdbcConfig;
import com.yc.config.SpringConfig;
import junit.framework.TestCase;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-08-02 15:25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
@Log4j2
public class Test2_DataSourceConfig  {
    @Autowired
    private JdbcConfig jc;
    @Autowired
    private  DataSource dataSource;

//    @Autowired
//    private Environment env;

    @Test
    public void testDataSource() throws SQLException {
        Connection con = dataSource.getConnection();
        System.out.println(con);
    }

}
