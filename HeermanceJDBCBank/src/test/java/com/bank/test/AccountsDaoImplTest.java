package com.bank.test;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	@Test
//	public void testUpdateAccountStatus() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDeposit() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testWithdraw() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testTransfer() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testUpdateAccountBalance() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDeleteAccount() {
//		fail("Not yet implemented");
//	}

}
