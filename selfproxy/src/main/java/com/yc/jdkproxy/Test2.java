package com.yc.jdkproxy;

import com.yc.staticproxy.OrderBiz;
import com.yc.staticproxy.OrderBizImpl;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-08-01 19:58
 */
public class Test2 {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", true);
        JdkProxyTool jpt = new JdkProxyTool(new OrderBizImpl());
        OrderBiz ob= (OrderBiz) jpt.createProxy();
        ob.findOrder();
        ob.addOrder(1, 99);
    }
}
