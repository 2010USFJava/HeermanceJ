package com.bank.dao;

import java.sql.SQLException;
import java.util.List;

import com.bank.bean.Accounts;

public interface AccountsDAO {
	
	public long newAccount(Accounts a) throws SQLException;
	public List<Accounts> getInactiveAccounts() throws SQLException;
	public void updateAccountStatus(long acctid) throws SQLException;
	public Accounts getAccountByID(long acctid) throws SQLException;
	public void deposit(long acctid, double amount,String username) throws SQLException;
	public void withdraw(long acctid,double amount,String username) throws SQLException;
	public void transfer(long acctid1, long acctid2,double amount,String username) throws SQLException;
	public void deleteAccount(long acctid) throws SQLException;

}
