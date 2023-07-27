package com.yc.springTest3.jdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-07-26 21:23
 */
public interface RowMapper<T> {

    public T mapper(ResultSet rs,int i) throws SQLException;
}
