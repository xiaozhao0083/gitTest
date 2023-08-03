package com.yc.biz;

import org.springframework.stereotype.Service;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-08-02 14:36
 */
@Service
public class AccountBizImpl implements AccountBiz{

    @Override
    public void addAccount(int accountId, double money) {
        System.out.println("添加账户："+accountId);
    }
}
