package com.yc.biz;

import org.springframework.stereotype.Service;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-08-01 10:03
 */
@Service
public class OrderBizImpl implements OrderBiz{
    @Override
    public void makeOrder(int pid, int num) {
        System.out.println("makerOrder 的方法调用： 下单："+pid+"\t"+num);
    }

    @Override
    public int findOrderId(String pname) {
        System.out.println("findOrderId: 根据商品名:"+pname+"查找的订单号");
        return 2;
    }

    @Override
    public int findPid(String pname) {
        System.out.println("findOrderId: 根据商品名:"+pname+"查找的订单号");
        return (int)(Math.random()*10);
    }
}
