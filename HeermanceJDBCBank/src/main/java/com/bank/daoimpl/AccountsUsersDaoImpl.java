package com.bank.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.bean.Accounts;
import com.bank.bean.Accounts_Users;
import com.bank.bean.Customer;
import com.bank.bean.Accounts.accountType;
import com.bank.dao.AccountsUsersDAO;
import com.bank.util.ConnFactory;
import com.bank.util.LogThis;

public class AccountsUsersDaoImpl implements AccountsUsersDAO{
	
	public static ConnFactory cf = ConnFactory.getInstance();

	@Override
	public void insertRelationship(long custid, long acctid) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "insert into \"Account_Users\" values(?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, acctid);
		ps.setLong(2, custid);
		ps.executeUpdate();
		LogThis.LogIt("info", custid + " is a user for account "+ acctid);
	}

	@Override
	public List<Accounts> getAccountsByCustID(long custid) throws SQLException {
		List<Accounts> acctlist = new ArrayList<>();
		Connection conn = cf.getConnection();
		String sql = "select * from \"Account_Users\" right join \"Accounts\" on \"Account_Users\".\"Account_ID\"=\"Accounts\".\"Account_ID\" where \"Customer_ID\"=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, custid);
		ResultSet rs = ps.executeQuery();
		Accounts a = null;
		while(rs.next()) {
			a = new Accounts(rs.getLong(3),accountType.valueOf(rs.getString(4)),rs.getBoolean(5),rs.getDouble(6));
			acctlist.add(a);
		}
		if(acctlist.size()==0) {
			System.out.println("This user has no accounts.");
		}else {
			System.out.println(acctlist.toString());
		}
		return acctlist;
	}
	
	@Override
	public List<Customer> getCustomersByAcctID(long acctid) throws SQLException {
		List<Customer> custlist = new ArrayList<>();
		Connection conn = cf.getConnection();
		String sql = "select * from \"Account_Users\" right join \"Customer\" on \"Account_Users\".\"Customer_ID\" = \"Customer\".\"Customer_ID\" where \"Account_ID\"=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, acctid);
		ResultSet rs = ps.executeQuery();
		Customer c = null;
		while(rs.next()) {
			c = new Customer(rs.getLong(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
					rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getInt(13));
			custlist.add(c);
		}
		System.out.println(custlist.toString());
		return custlist;
	}

}
