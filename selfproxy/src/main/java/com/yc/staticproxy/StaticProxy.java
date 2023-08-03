package com.yc.staticproxy;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-08-01 19:03
 */
public class StaticProxy implements OrderBiz{
    private  OrderBiz orderBiz;

    public StaticProxy(OrderBiz orderBiz) {
        this.orderBiz = orderBiz;
    }

    @Override
    public void addOrder(int pid, int num) {
        showHello();
        orderBiz.addOrder(pid, num);
        showBye();
    }

    @Override
    public void findOrder() {

    }

    private void showBye() {
        System.out.println("bye");

    }

    private void showHello() {
        System.out.println("hello");

    }
}
