package com.yc.biz;

import com.yc.bean.Account;


public interface AccountBiz {
    // void addAccount(int accountId, double money);

    /**
     * 银行开户
     *
     * @param money
     * @return
     */
    Account openAccount(double money);

    Account deposit(int accountId, double money);

    /**
     * 存款,给accountid存入money,并返回最后的余额信息
     *
     * @param accountId
     * @param money
     * @param transferId
     * @return
     */
    Account deposit(int accountId, double money, Integer transferId);

    Account withdraw(int accountId, double money);

    /**
     * 取款:给accountId取出money,并返回最后的余额信息
     *
     * @param accountId
     * @param money
     * @return
     */
    Account withdraw(int accountId, double money, Integer transferId);

    /**
     * 从accountId中转出money到toAccountId账户
     *
     * @param accountId
     * @param money
     * @param toAccountId
     * @return
     */
    Account transfer(int accountId, double money, int toAccountId);

    /**
     * 查询是否存在accountId账户
     *
     * @param accountId
     * @return
     */
    Account findAccount(int accountId);
}
