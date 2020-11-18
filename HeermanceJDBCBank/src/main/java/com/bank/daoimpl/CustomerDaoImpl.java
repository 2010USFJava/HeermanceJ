package com.bank.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bank.bean.Customer;
import com.bank.dao.CustomerDAO;
import com.bank.util.ConnFactory;
import com.bank.util.LogThis;

public class CustomerDaoImpl implements CustomerDAO{
	
	public static ConnFactory cf = ConnFactory.getInstance();
	
	
	@Override
	public long newCustomer(Customer c) throws SQLException {
		long custid = 0;
		Connection conn = cf.getConnection();
		String sql = "insert into \"Customer\" values(DEFAULT,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		ps.setString(1,c.getFname());
		ps.setString(2,c.getMname());
		ps.setString(3,c.getLname());
		ps.setString(4,c.getAddress_street());
		ps.setString(5,c.getAddress_city());
		ps.setString(6,c.getAddress_state());
		ps.setString(7,c.getAddress_zipcode());
		ps.setString(8,c.getUsername());
		ps.setString(9,c.getPassword());
		ps.setInt(10,c.getAccount_num());
		int affectedRows = ps.executeUpdate();
		if(affectedRows>0) {
			ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					custid = rs.getLong(1);
				
			}
		}
		LogThis.LogIt("info","Account "+ custid +" has been created.");
		return custid;
	}
	
	@Override
	public boolean usernameExists(String username) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "select * from \"Customer\" where \"Username\"=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		if(rs!=null) {
			while(rs.next()) {
				return true;
			}
		}return false;
	}
	
	@Override
	public Customer getCustomerByUsername(String username) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "select * from \"Customer\" where \"Username\"=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		Customer cust = null;
		if(rs!=null) {
			while(rs.next()) {
				cust = new Customer(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getInt(11));
				return cust;
			}
		}return null;
	}

	@Override
	public Customer getCustomerByID(long id) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "select * from \"Customer\" where \"Customer_ID\"=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		Customer cust = null;
		if(rs!=null) {
			while(rs.next()) {
				cust = new Customer(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getInt(11));
				return cust;
			}
		}
		return null;
	}

	@Override
	public void updateCustomerPassword(Customer cust, String newpass1) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "update \"Customer\" set \"Password\" = ? where \"Customer_ID\" =?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, newpass1);
		ps.setLong(2, cust.getCustid());
		ps.executeUpdate();
		LogThis.LogIt("info", "Customer "+cust.getCustid()+" updated their password.");
	}

	@Override
	public void updateCustomerAddress(Customer cust,String address_street,String address_city,String address_state,String address_zipcode) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "update \"Customer\" set \"Address_Street\" = ?,\"Address_City\"=?,\"Address_State\"=?,\"Address_Zipcode\"=? where \"Customer_ID\" =?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, address_street);
		ps.setString(2, address_city);
		ps.setString(3, address_state);
		ps.setString(4, address_zipcode);
		ps.setLong(5, cust.getCustid());
		ps.executeUpdate();
		LogThis.LogIt("info", "Customer "+cust.getCustid()+" updated their address.");
	}

	@Override
	public void updateCustomerAccounts(Customer cust, int add) throws SQLException {
		Connection conn = cf.getConnection();
		String sql1 = "select \"Accounts\" from \"Customer\" where \"Customer_ID\"=?";
		PreparedStatement ps1 = conn.prepareStatement(sql1);
		ps1.setLong(1, cust.getCustid());
		ResultSet rs1 = ps1.executeQuery();
		int num = 0;
		if(rs1!=null) {
			while(rs1.next()) {
				num=rs1.getInt(1);
			}
		}
		num+=add;
		String sql2 = "update \"Customer\" set \"Accounts\"=? where \"Customer_ID\"=?";
		PreparedStatement ps2 = conn.prepareStatement(sql2);
		ps2.setInt(1, num);
		ps2.setLong(2, cust.getCustid());
		ps2.executeUpdate();
		if(add==1) {
			LogThis.LogIt("info", "Customer " +cust.getCustid()+ " has a new account approved.");
		}else if(add==-1) {
			LogThis.LogIt("info", "Customer " +cust.getCustid()+ " has one less account.");
		}
		
	}

	@Override
	public void deleteCustomer(Customer cust) throws SQLException {
		Connection conn = cf.getConnection();
		String sql1 = "select \"Accounts\" from \"Customer\" where \"Customer_ID\"=?";
		PreparedStatement ps1 = conn.prepareStatement(sql1);
		ps1.setLong(1, cust.getCustid());
		ResultSet rs1 = ps1.executeQuery();
		int num = 0;
		if(rs1!=null) {
			while(rs1.next()) {
				num=rs1.getInt(1);
			}
		}
		if(num!=0) {
			System.out.println("This customer has open accounts. Please close those accounts before proceeding.");
			
		}
		String sql2 = "delete from \"Customer\" where \"Customer_ID\"=?";
		PreparedStatement ps2 = conn.prepareStatement(sql2);
		ps2.setLong(1,cust.getCustid());
		ps2.executeUpdate();		
		LogThis.LogIt("info", "Customer "+cust.getCustid()+"'s account has been deleted.");
	}

}
