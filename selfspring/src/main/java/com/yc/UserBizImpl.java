package com.yc;

import org.ycframework.annotation.YcResource;
import org.ycframework.annotation.YcService;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-07-27 19:58
 */
@YcService
public class UserBizImpl implements UserBiz {

    @YcResource(name = "userDaoImpl")
    private  UserDao userDao;

    @Override
    public void add(String uname) {
        userDao.add(uname);
    }
}
