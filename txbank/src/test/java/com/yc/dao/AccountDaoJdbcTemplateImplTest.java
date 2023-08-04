package com.yc.dao;


import com.yc.bean.Account;
import com.yc.config.SpringConfig;
import junit.framework.TestCase;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
@Log4j2
public class AccountDaoJdbcTemplateImplTest extends TestCase {

	@Autowired
	private AccountDao accountDao;

	@Test
	public void insert() {
		int accountId = accountDao.insert(200);
		log.info("新开账户为: " + accountId);
		Assert.assertNotNull(accountId);
	}

	@Test
	public void update() {
		accountDao.update(1, -10);
	}

	@Test
	public void delete() {
		accountDao.delete(2);
	}

	@Test
	public void findCount() {
		int count = accountDao.findCount();
		log.info("账户总数为: " + count);
		// Assert.assertEquals(1,count);
	}

	@Test
	public void findAll() {
		List<Account> list = accountDao.findAll();
		log.info(list);
		for (Account account : list) {
			System.out.println(account.getAccountid() + "\t" + account.getMoney());
		}
	}

	@Test
	public void findById() {
		Account account = accountDao.findById(2);
		log.info(account);
		System.out.println(account.getAccountid() + "\t" + account.getMoney());
	}
}