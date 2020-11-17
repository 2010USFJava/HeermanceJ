package com.bank.bean;

import java.io.Serializable;

public class Customer implements Serializable{

	private static final long serialVersionUID = 8091160448725173489L;
	
	private long custid;
	private String fname;
	private String mname;
	private String lname;
	private String address_street;
	private String address_city;
	private String address_state;
	private String address_zipcode;
	private String username;
	private String password;
	private int account_num;
	
	public Customer() {
		super();
	}
	
	public Customer(long custid, String fname, String mname, String lname, String address_street, 
			String address_city, String address_state, String address_zipcode, String username,
			String password, int account_num) {
		this.custid=custid;
		this.fname=fname;
		this.mname=mname;
		this.lname=lname;
		this.address_street=address_street;
		this.address_city=address_city;
		this.address_state=address_state;
		this.address_zipcode=address_zipcode;
		this.username=username;
		this.password=password;
		this.account_num=account_num;		
	}
	
	public long getCustid() {
		return custid;
	}
	public void setCustid(long custid) {
		this.custid=custid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getAddress_street() {
		return address_street;
	}
	public void setAddress_street(String address_street) {
		this.address_street = address_street;
	}
	public String getAddress_city() {
		return address_city;
	}
	public void setAddress_city(String address_city) {
		this.address_city = address_city;
	}
	public String getAddress_state() {
		return address_state;
	}
	public void setAddress_state(String address_state) {
		this.address_state = address_state;
	}
	public String getAddress_zipcode() {
		return address_zipcode;
	}
	public void setAddress_zipcode(String address_zipcode) {
		this.address_zipcode = address_zipcode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAccount_num() {
		return account_num;
	}
	public void setAccount_num(int account_num) {
		this.account_num = account_num;
	}

	@Override
	public String toString() {
		return "---------------------------------------------------------------------------------\n"
				+ "Customer ID: " + custid + ", Name: " + fname +" "+ mname +" "+ lname + ", Address: "
				+ address_street + " " + address_city + ", " + address_state
				+ " " + address_zipcode + ",\n Username: " + username + ", Password:" + password
				+ ", Number of Accounts: " + account_num + 
				"\n--------------------------------------------------------------------------------";
	}
	
	
	
	
}
