package com.yc;

import com.yc.biz.OrderBiz;
import com.yc.biz.OrderBizImpl;
import com.yc.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-08-01 10:11
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext app = new AnnotationConfigApplicationContext(SpringConfig.class);
        OrderBiz o = (OrderBiz) app.getBean(OrderBiz.class);
//        o.makeOrder(1, 5);
        o.findPid("apple");
        o.findPid("apple");
        o.findPid("pear");

    }
}
