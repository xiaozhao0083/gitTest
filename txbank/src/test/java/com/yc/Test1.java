package com.yc;

import com.yc.biz.AccountBiz;
import com.yc.config.SpringConfig;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-08-02 15:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class Test1 extends  TestCase {
    @Autowired
    private AccountBiz accountBiz;

    @Test
    public void testAddAccount(){
        accountBiz.addAccount(1, 9);
    }

    public void testAdd(){
        int x30,y=4;
    }


}
