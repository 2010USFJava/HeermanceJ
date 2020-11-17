package com.bank.dao;

import java.sql.SQLException;
import java.util.List;

import com.bank.bean.Accounts;
import com.bank.bean.Customer;

public interface AccountsUsersDAO {
	
	public void insertRelationship(long custid, long acctid) throws SQLException;
	public List<Accounts> getAccountsByCustID(long custid) throws SQLException;
	public List<Customer> getCustomersByAcctID(long acctid) throws SQLException;
	
}
