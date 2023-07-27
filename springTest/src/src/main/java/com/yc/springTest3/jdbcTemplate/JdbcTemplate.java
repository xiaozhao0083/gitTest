package com.yc.springTest3.jdbcTemplate;

import com.yc.springTest3.dataSource.MyDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-07-26 21:23
 */
public class JdbcTemplate<T> {
    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<T> executeQuery(String sql, RowMapper<?> rowMapper, Object... params) {
        List<T> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(sql);
            setParams(pstmt, params);
            rs = pstmt.executeQuery();
            int i = 0;
            while (rs.next()) {
                T t = (T) rowMapper.mapper(rs, i);
                i++;
                list.add(t);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            ((MyDataSource) dataSource).returnConnection(con);

        }
        return list;
    }

    private void setParams(PreparedStatement pstmt, Object... params) throws SQLException {
        if (null == params || params.length <= 0) {
            return;
        }
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i+1, params[i]);
        }

    }

}
