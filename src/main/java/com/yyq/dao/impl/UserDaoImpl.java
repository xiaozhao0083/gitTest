package com.yyq.dao.impl;

import com.yyq.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
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
