package com.bank.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import com.bank.bean.Accounts;
import com.bank.bean.Accounts_Users;
import com.bank.bean.Customer;
import com.bank.bean.Accounts.accountType;
import com.bank.daoimpl.AccountsDaoImpl;
import com.bank.daoimpl.AccountsUsersDaoImpl;
import com.bank.daoimpl.CustomerDaoImpl;
import com.bank.util.ConnFactory;

public class AccountsUsersDaoImplTest {
	
	static CustomerDaoImpl cdi = new CustomerDaoImpl();
	static AccountsDaoImpl adi = new AccountsDaoImpl();
	static AccountsUsersDaoImpl audi = new AccountsUsersDaoImpl();
	static ConnFactory cf = ConnFactory.getInstance();
	static Connection conn = cf.getConnection();
	
	@After
	public void deleteNewData() {
		try {
			PreparedStatement p = conn.prepareStatement("truncate table \"Account_Users\"");
			p.executeUpdate();
		} catch (SQLException e) {
			System.out.println("delete problems");
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertRelationship() {
		try {
			audi.insertRelationship(111,222);
			PreparedStatement ps = conn.prepareStatement("select * from \"Accounts_Users\" where \"Account_ID\"=111");
			ResultSet rs = ps.executeQuery();
			while(rs!=null) {
				Accounts_Users au = new Accounts_Users(rs.getLong(1),rs.getLong(2));
				assertEquals(222,au.getCustid());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAccountsByCustId() {
		Customer c = new Customer(0,"firsttest","mtest","lasttest","123 Test st","City","ST","12345","TestUsertest","TestPasswordtest",0);
		Accounts a = new Accounts(0,accountType.CHECKING,true,100.00);
		try {
			Long custid = cdi.newCustomer(c);
			Long acctid = adi.newAccount(a);
			audi.insertRelationship(acctid,custid);
			List<Accounts> alist = audi.getAccountsByCustID(custid);
			assertTrue(alist.size()>0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetCustomersByAcctID() {
		Customer c = new Customer(0,"firsttest","mtest","lasttest","123 Test st","City","ST","12345","TestUsertest","TestPasswordtest",0);
		Accounts a = new Accounts(0,accountType.CHECKING,true,100.00);
		try {
			Long custid = cdi.newCustomer(c);
			Long acctid = adi.newAccount(a);
			audi.insertRelationship(acctid,custid);
			List<Customer> alist = audi.getCustomersByAcctID(acctid);
			assertTrue(alist.size()>0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
