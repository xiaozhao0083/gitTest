package com.yyq.springTest1;

import com.yyq.springTest1.config.SpringConfig;
import com.yyq.springTest1.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-07-25 19:00
 */
public class App {
    public static void main(String[] args) {
//        ApplicationContext app = new AnnotationConfigApplicationContext(SpringConfig.class);
//        UserDao dao= (UserDao) app.getBean(UserDao.class);
//        dao.add("lisi");
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserDao userDao = (UserDao) ctx.getBean(UserDao.class);
        System.out.println(userDao);
    }

}
