package com.yc.dao;

import com.yc.bean.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class AccountDaoJdbcTemplateImpl implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void init(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int insert(double money) {
        final String INSERT_SQL = "insert into accounts (balance) values(?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[]{"accountid"});
            ps.setDouble(1, money);
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void update(int accountid, double money) {
        this.jdbcTemplate.update(
                "update accounts set balance = ? where accountid = ?",
                money, accountid);
    }

    @Override
    public void delete(int accountId) {
        this.jdbcTemplate.update(
                "delete from accounts where accountid = ?",
                Integer.valueOf(accountId));
    }

    @Override
    public int findCount() {
        int rowCount = this.jdbcTemplate.queryForObject("select count(*) from accounts", Integer.class);
        return rowCount;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = this.jdbcTemplate.query(
                "select * from accounts",
                (resultSet, rowNum) -> {
                    Account account = new Account();
                    account.setAccountid(resultSet.getInt(1));
                    account.setMoney(resultSet.getDouble(2));
                    return account;
                });
        return accounts;
    }

    @Override
    public Account findById(int accountid) {
        //模板模式
        Account account = this.jdbcTemplate.queryForObject(
                "select * from accounts where accountid = ?",
                (resultSet, rowNum) -> {
                    Account newAccount = new Account();
                    newAccount.setAccountid(resultSet.getInt(1));
                    newAccount.setMoney(resultSet.getDouble(2));
                    return newAccount;
                },
                accountid);
        return account;
    }
}
