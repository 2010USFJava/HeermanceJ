package com.bank.dao;

import java.sql.SQLException;

import com.bank.bean.Customer;

public interface CustomerDAO {
	
	public long newCustomer(Customer c) throws SQLException;
	public boolean usernameExists(String username) throws SQLException;
	public Customer getCustomerByUsername(String username) throws SQLException;
	public Customer getCustomerByID(long id) throws SQLException;
	public void updateCustomerPassword(Customer cust, String newpass1) throws SQLException;
	public void updateCustomerAddress(Customer cust,String address_street,String address_city,String address_state,String address_zipcode) throws SQLException;
	public void updateCustomerAccounts(Customer cust, int add) throws SQLException;
	public void deleteCustomer(Customer cust) throws SQLException;
	

}
