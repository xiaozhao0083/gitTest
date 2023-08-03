package com.yc.staticproxy;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-08-01 19:02
 */
public class OrderBizImpl implements OrderBiz{
    @Override
    public void addOrder(int pid, int num) {
        System.out.println("添加了订单："+pid+"，数量为："+num);
    }

    @Override
    public void findOrder() {
        System.out.println("查询订单");
    }
}
