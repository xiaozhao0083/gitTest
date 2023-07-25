package com.yyq;

import com.yyq.config.SpringConfig;
import com.yyq.dao.UserDao;
import com.yyq.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-07-25 19:00
 */
public class UserController {
    public static void main(String[] args) {
//        ApplicationContext app = new AnnotationConfigApplicationContext(SpringConfig.class);
//        UserDao dao= (UserDao) app.getBean(UserDao.class);
//        dao.add("lisi");
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserDao userDao = (UserDao) ctx.getBean(UserDao.class);
        System.out.println(userDao);
    }

}
