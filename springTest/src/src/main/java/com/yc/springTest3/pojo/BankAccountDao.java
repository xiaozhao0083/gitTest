package com.yc.springTest3.pojo;

import com.yc.springTest3.jdbcTemplate.JdbcTemplate;
import com.yc.springTest3.jdbcTemplate.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-07-27 15:34
 */
@Repository
public class BankAccountDao extends JdbcTemplate {
    @Autowired
    public BankAccountDao(DataSource dataSource){
        super(dataSource);
    }

    public List<BankAccount> findAll(){
        return super.executeQuery("select * from bank where id=?", new RowMapper<BankAccount>() {
            @Override
            public BankAccount mapper(ResultSet rs, int i) throws SQLException {
                BankAccount bankAccount = new BankAccount();
                bankAccount.setId(rs.getInt(1));
                bankAccount.setMoney(rs.getDouble(2));
                return bankAccount;
            }
        }, 1);

    }

}
