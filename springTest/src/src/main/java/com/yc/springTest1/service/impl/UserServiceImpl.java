package com.yc.springTest1.service.impl;

import com.yc.springTest1.dao.UserDao;
import com.yc.springTest1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-07-25 18:56
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void add(String uname) {
        userDao.add(uname);
    }
}
