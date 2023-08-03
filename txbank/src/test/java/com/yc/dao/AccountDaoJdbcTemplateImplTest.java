package com.yc.dao;

import com.yc.bean.Account;
import com.yc.config.SpringConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.swing.*;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class AccountDaoJdbcTemplateImplTest {
    @Autowired
    private AccountDao accountDao;

    @Test
    public void insert() {
        int id = accountDao.insert(5);
        System.out.println(id);

    }

    @Test
    public void update() {
        accountDao.update(1, 100);
    }

    @Test
    public void delete() {
        accountDao.delete(1);
    }

    @Test
    public void findCount() {
        int count = accountDao.findCount();
        System.out.println(count);
    }

    @Test
    public void findAll() {
        List<Account> list = accountDao.findAll();
        System.out.println(list);
    }

    @Test
    public void findById() {
        Account a = accountDao.findById(2);
        System.out.println(a);
    }
}