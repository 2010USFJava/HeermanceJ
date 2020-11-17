package com.bank.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Test;

import com.bank.bean.Customer;
import com.bank.daoimpl.CustomerDaoImpl;
import com.bank.util.ConnFactory;

public class CustomerDaoImplTest {
	
	static CustomerDaoImpl cdi = new CustomerDaoImpl();
	static ConnFactory cf = ConnFactory.getInstance();
	static Connection conn = cf.getConnection();
	
	//p.execute is true/false
	//p.executeQuery for resultset object
	//p.executeUpdate for insert, delete, update statements
	
	@After
	public void deleteNewData() {
		try {
			PreparedStatement p = conn.prepareStatement("delete from \"Customer\" where \"Username\"='TestUsertest'");
			p.executeUpdate();
		} catch (SQLException e) {
			System.out.println("delete problems");
			e.printStackTrace();
		}
	}

	@Test
	public void testNewCustomer() {
		Customer c = new Customer(0,"firsttest","mtest","lasttest","123 Test st","City","ST","12345","TestUsertest","TestPasswordtest",0);
		try {
			cdi.newCustomer(c);
			PreparedStatement ps = conn.prepareStatement("select * from \"Customer\" where \"Username\"=\'TestUsertest\'");
			ResultSet rs = ps.executeQuery();
			Customer cust = null;
			if(rs!=null) {
				while(rs.next()) {
					cust = new Customer(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
							rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getInt(11));
					System.out.println(cust.toString());
				}
			}else {
				fail("Test failure");
			}
		} catch (SQLException e) {
			System.out.println("testNewCustomer problem");
			e.printStackTrace();
		}
		
		
	}
//	
//	@Test
//	public void testGetCustomerByUsername(){
//		Customer c = new Customer(0,"firsttest","mtest","lasttest","123 Test st","City","ST","12345","TestUsertest","TestPasswordtest",0);
//		try {
//			cdi.newCustomer(c);
//			boolean test;
//			test = cdi.getCustomerByUsername("TestUsertest");
//			assertEquals(test,true);
//		} catch (SQLException e) {
//			System.out.println("testGetCustomerByUsername problem");
//			e.printStackTrace();
//		}
//	}

//	@Test
//	public void testGetCustomerByID() {
//		Customer c = new Customer(0,"firsttest","mtest","lasttest","123 Test st","City","ST","12345","TestUsertest","TestPasswordtest",0);
//		try {
//			cdi.newCustomer(c);
//			long test = 0;
//			test = cdi.getCustIDByUsername("TestUsertest");
//			Customer cust = null;
//			cust = cdi.getCustomerByID(test);
//			System.out.println(cust.toString());
//			System.out.println(c.toString());
//			assertTrue(cust.toString().equals(c.toString()));
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//		try {
//			long test = cdi.getCustIDByUsername("TestUsertest");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

//	@Test
//	public void testUpdateCustomerPassword() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testUpdateCustomerAddress() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testUpdateCustomerAccounts() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDeleteCustomer() {
//		fail("Not yet implemented");
//	}

}
