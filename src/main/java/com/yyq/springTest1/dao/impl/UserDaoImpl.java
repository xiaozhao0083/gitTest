package com.yyq.springTest1.dao.impl;

import com.yyq.springTest1.dao.UserDao;
import org.springframework.stereotype.Repository;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-07-25 17:10
 */

@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public void add(String uname) {
        System.out.println("userdao add"+uname);
    }
}
