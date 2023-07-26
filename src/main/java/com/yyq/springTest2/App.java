package com.yyq.springTest2;

import com.yyq.springTest2.config.SpringConfig;
import com.yyq.springTest2.system.Container;
import com.yyq.springTest2.user.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-07-26 10:32
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext app= new AnnotationConfigApplicationContext(SpringConfig.class);
        Container container = app.getBean(Container.class);
        container.add(new Student("张三",170,90));
        container.add(new Student("李四",180,100));
        container.add(new Student("王五",160,70));
        container.add(new Student("",80,20));
        System.out.println(container.getMax());
        System.out.println(container.getMin());
        System.out.println(container.getAvg());
    }
}
