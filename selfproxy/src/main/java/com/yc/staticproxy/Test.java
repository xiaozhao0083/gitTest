package com.yc.staticproxy;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-08-01 19:06
 */
public class Test {
    public static void main(String[] args) {

        OrderBiz ob = new StaticProxy(new OrderBizImpl());
        ob.addOrder(1, 100);

    }

}
