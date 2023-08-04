package com.yc.biz;


import com.yc.bean.Account;
import com.yc.bean.OpRecord;
import com.yc.bean.OpType;
import com.yc.dao.AccountDao;
import com.yc.dao.OpRecordDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Log4j2
@Transactional
public class AccountBizImpl implements AccountBiz {

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private OpRecordDao opRecordDao;

	@Override
	public Account openAccount(double money) {
		//返回新账户的id
		int accountId = this.accountDao.insert(money);
		//包装日志信息
		OpRecord opRecord = new OpRecord();
		opRecord.setAccountid(accountId);
		opRecord.setOpmoney(money);
		opRecord.setOptype(OpType.DEPOSITE);
		this.opRecordDao.insertOpRecord(opRecord);
		//返回新账户信息
		Account account = new Account();
		account.setAccountid(accountId);
		account.setMoney(money);
		return account;
	}

	@Override
	public Account deposit(int accountId, double money) {
		return this.deposit(accountId,money,null);
	}

	@Override
	public Account deposit(int accountId, double money, Integer transferId) {
		Account account = null;
		try {
			account = this.accountDao.findById(accountId);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException("查无此账户: "+accountId+" ,无法完成存款操作...");
		}
		account.setMoney(account.getMoney()+money);
		this.accountDao.update(accountId,account.getMoney());

		OpRecord opRecord = new OpRecord();
		opRecord.setAccountid(accountId);
		opRecord.setOpmoney(money);
		if (transferId != null) {
			opRecord.setOptype(OpType.TRANSFER);
			opRecord.setTransferid(transferId);
		}else {
			opRecord.setOptype(OpType.DEPOSITE);
		}
		this.opRecordDao.insertOpRecord(opRecord);
		return account;
	}

	@Override
	public Account withdraw(int accountId, double money) {
		return this.withdraw(accountId,money,null);
	}

	@Override
	public Account withdraw(int accountId, double money, Integer transferId) {
		Account account = null;
		try {
			account = this.accountDao.findById(accountId);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException("查无此账户: "+accountId+" ,无法完成取款操作...");
		}
		account.setMoney(account.getMoney()-money);

		OpRecord opRecord = new OpRecord();
		opRecord.setAccountid(accountId);
		opRecord.setOpmoney(money);
		if (transferId != null) {
			opRecord.setOptype(OpType.TRANSFER);
			opRecord.setTransferid(transferId);
		}else {
			opRecord.setOptype(OpType.WITHDRAW);
		}

		//  dao -> datasource -> connection -> jdbc隐式事务提交 -> 一条sql commit一次
		this.opRecordDao.insertOpRecord(opRecord);
		this.accountDao.update(accountId,account.getMoney());
		return account;
	}

	@Override
	public Account transfer(int accountId, double money, int toAccountId) {
		this.deposit(toAccountId, money, accountId);
		Account account = this.withdraw(accountId, money, toAccountId);
		return account;
	}

	@Transactional(readOnly = true)
	@Override
	public Account findAccount(int accountId) {
		return this.accountDao.findById(accountId);
	}

	// @Override
	// public void addAccount(int accountId, double money) {
	// 	System.out.println("添加账户: " + accountId + " 金额: " + money);
	// }
}
