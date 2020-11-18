package com.bank.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Test;

import com.bank.daoimpl.AccountsDaoImpl;
import com.bank.util.ConnFactory;

public class AccountsUsersDaoImplTest {
	
	static AccountsDaoImpl adi = new AccountsDaoImpl();
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
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetAccountsByCustId() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetCustomersByAcctID() {
		fail("Not yet implemented");
	}

}
