package com.bank.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import com.bank.bean.Accounts;
import com.bank.bean.Accounts.accountType;
import com.bank.daoimpl.AccountsDaoImpl;
import com.bank.util.ConnFactory;

public class AccountsDaoImplTest {
	
	static AccountsDaoImpl adi = new AccountsDaoImpl();
	static ConnFactory cf = ConnFactory.getInstance();
	static Connection conn = cf.getConnection();
	
	@After
	public void deleteNewData() {
		try {
			PreparedStatement p = conn.prepareStatement("truncate table \"Accounts\" cascade");
			p.executeUpdate();
		} catch (SQLException e) {
			System.out.println("delete problems");
			e.printStackTrace();
		}
	}

	@Test
	public void testNewAccount() {
		Accounts a = new Accounts(0,accountType.CHECKING,true,100.00);
		try {
			adi.newAccount(a);
			PreparedStatement ps = conn.prepareStatement("select * from \"Accounts\"");
			ResultSet rs = ps.executeQuery();
			Accounts acct = null;
			if(rs!=null) {
				while(rs.next()) {
					acct = new Accounts(rs.getLong(1),accountType.valueOf(rs.getString(2)),rs.getBoolean(3),rs.getDouble(4));
					System.out.println(acct.toString());
				}
			}else {
				fail("Test failure");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetInactiveAccounts() {
		Accounts a = new Accounts(0,accountType.CHECKING,false,100.00);
		try {
			adi.newAccount(a);
			List<Accounts> alist = adi.getInactiveAccounts();
			assertTrue(alist.size()>0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateAccountStatus() {
		Accounts a = new Accounts(0,accountType.CHECKING,false,100.00);
		try {
			long acctid = 0;
			acctid = adi.newAccount(a);
			adi.updateAccountStatus(acctid);
			Accounts acct = adi.getAccountByID(acctid);
			assertEquals(true,acct.isAccountStatus());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAcctById() {
		Accounts a = new Accounts(0,accountType.CHECKING,false,100.00);
		try {
			long acctid = 0;
			acctid = adi.newAccount(a);
			Accounts acct = adi.getAccountByID(acctid);
			assertEquals(100.00,acct.getBalance(),.02);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDeposit() {
		Accounts a = new Accounts(0,accountType.CHECKING,false,100.00);
		try {
			long acctid = 0;
			acctid = adi.newAccount(a);
			adi.deposit(acctid, 50.75, "admin");
			Accounts acct = adi.getAccountByID(acctid);
			assertEquals(150.75,acct.getBalance(),.02);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testWithdraw() {
		Accounts a = new Accounts(0,accountType.CHECKING,false,100.00);
		try {
			long acctid = 0;
			acctid = adi.newAccount(a);
			adi.withdraw(acctid, 50.75, "admin");
			Accounts acct = adi.getAccountByID(acctid);
			assertEquals(49.25,acct.getBalance(),.02);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testTransfer() {
		Accounts a = new Accounts(0,accountType.CHECKING,false,100.00);
		Accounts b = new Accounts(0,accountType.SAVINGS,false,500.00);
		try {
			long acctid1 = 0;
			long acctid2 = 0;
			acctid1 = adi.newAccount(a);
			acctid2 = adi.newAccount(b);
			adi.transfer(acctid1, acctid2, 25.62, "admin");
			Accounts acct1 = adi.getAccountByID(acctid1);
			Accounts acct2 = adi.getAccountByID(acctid2);
			assertEquals(74.38,acct1.getBalance(),.02);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteAccount() {
		Accounts a = new Accounts(0,accountType.CHECKING,false,0.00);
		try {
			boolean test;
			long acctid = 0;
			acctid = adi.newAccount(a);
			adi.deleteAccount(acctid);
			String sql = "select * from \"Accounts\" where \"Balance\"=0";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					test = true;
				}
			}test = false;
			assertEquals(false,test);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
