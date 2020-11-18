package com.bank.test;

import static org.junit.Assert.assertEquals;
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
	
	@Test
	public void testUsernameExists() {
		Customer c = new Customer(0,"firsttest","mtest","lasttest","123 Test st","City","ST","12345","TestUsertest","TestPasswordtest",0);
		try {
			boolean test = false;
			cdi.newCustomer(c);
			test = cdi.usernameExists("TestUsertest");
			assertEquals(true, test);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	@Test
	public void testGetCustomerByUsername(){
		Customer c = new Customer(0,"firsttest","mtest","lasttest","123 Test st","City","ST","12345","TestUsertest","TestPasswordtest",0);
		try {
			cdi.newCustomer(c);
			Customer test;
			test = cdi.getCustomerByUsername("TestUsertest");
			assertEquals("mtest",test.getMname());
		} catch (SQLException e) {
			System.out.println("testGetCustomerByUsername problem");
			e.printStackTrace();
		}
	}

	@Test
	public void testGetCustomerByID() {
		Customer c = new Customer(0,"firsttest","mtest","lasttest","123 Test st","City","ST","12345","TestUsertest","TestPasswordtest",0);
		try {
			long test = 0;
			test = cdi.newCustomer(c);
			Customer customer = cdi.getCustomerByID(test);
			assertEquals("firsttest",customer.getFname());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}

	@Test
	public void testUpdateCustomerPassword() {
		Customer c = new Customer(0,"firsttest","mtest","lasttest","123 Test st","City","ST","12345","TestUsertest","TestPasswordtest",0);
		try {
			long test = 0;
			test = cdi.newCustomer(c);
			String newpass = "newPassword";
			Customer customer = null;
			customer = cdi.getCustomerByID(test);
			cdi.updateCustomerPassword(customer, newpass);
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
			assertEquals(newpass,cust.getPassword());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	@Test
	public void testUpdateCustomerAddress() {
		Customer c = new Customer(0,"firsttest","mtest","lasttest","123 Test st","City","ST","12345","TestUsertest","TestPasswordtest",0);
		try {
			long test = 0;
			test = cdi.newCustomer(c);
			Customer customer = null;
			customer = cdi.getCustomerByID(test);
			cdi.updateCustomerAddress(customer, "555 Test Avenue", "Tampa", "FL", "55555");
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
			assertEquals("55555",cust.getAddress_zipcode());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Test
	public void testUpdateCustomerAccounts() {
		Customer c = new Customer(0,"firsttest","mtest","lasttest","123 Test st","City","ST","12345","TestUsertest","TestPasswordtest",6);
		try {
			long test = 0;
			test = cdi.newCustomer(c);
			Customer customer = null;
			customer = cdi.getCustomerByID(test);
			cdi.updateCustomerAccounts(customer, 1);
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
			assertEquals(7,cust.getAccount_num());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Test
	public void testDeleteCustomer() {
		Customer c = new Customer(0,"firsttest","mtest","lasttest","123 Test st","City","ST","12345","TestUsertest","TestPasswordtest",0);
		try {
			long test = 0;
			test = cdi.newCustomer(c);
			Customer customer = null;
			customer = cdi.getCustomerByID(test);
			cdi.deleteCustomer(customer);
			boolean test2 = cdi.usernameExists("TestUsertest");
			assertEquals(false,test2);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}
