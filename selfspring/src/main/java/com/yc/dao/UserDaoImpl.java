package com.yc.dao;

import org.ycframework.annotation.YcLazy;
import org.ycframework.annotation.YcRepository;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-07-27 19:56
 */
@YcRepository("userDaoImpl")
public class UserDaoImpl implements UserDao{

    @Override
    public void add(String uname) {
        System.out.println("UserDaoImpl add"   );
    }
}
