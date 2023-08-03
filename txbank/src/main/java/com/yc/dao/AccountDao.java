package com.yc.dao;

import com.yc.bean.Account;

import java.util.List;

public interface AccountDao {

    /**
     * 添加Account账号
     * @param money
     * @return
     */
    public int insert(double money);

    /**
     * 更新账户余额
     * @param accountid
     * @param money
     * @return
     */
    public void update(int accountid,double money);

    /**
     * 删除账户
     * @param accountid
     * @return
     */
    public void delete(int accountid);

    /**
     * 查询账户总数
     * @return
     */
    public int findCount();

    /**
     * 查询所有账户
     * @return
     */
    public List<Account> findAll();

    /**
     * 根据id查询账户
     * @param accountid
     * @return
     */
    public Account findById(int accountid);

}
