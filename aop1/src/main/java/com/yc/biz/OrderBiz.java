package com.yc.biz;

import org.springframework.stereotype.Service;

public interface OrderBiz {
    public void makeOrder(int pid,int num);

    public int findOrderId(String pname);

    public int findPid(String pname);
}
