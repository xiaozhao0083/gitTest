package com.yc.springTest3;

import com.yc.springTest3.config.SpringConfig;
import com.yc.springTest3.pojo.BankAccount;
import com.yc.springTest3.pojo.BankAccountDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-07-26 21:12
 */
public class App {
    public static void main(String[] args) throws SQLException {
        ApplicationContext app = new AnnotationConfigApplicationContext(SpringConfig.class);

        DataSource ds= (DataSource) app.getBean("myDataSource");
        Connection con = ds.getConnection();
        System.out.println(con);

//        BankAccountDao dao= (BankAccountDao) app.getBean("bankAccountDao");
//        List<BankAccount> list = dao.findAll();
//        System.out.println(list);

    }
}
