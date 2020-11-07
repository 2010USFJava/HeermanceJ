package com.bank.users;

import java.io.Serializable;

import com.bank.util.AllFiles;
import com.bank.util.FileStuff;
import com.bank.util.LogThis;

public class Customer implements Serializable{
	
	private static final long serialVersionUID = 1102760888190820267L;
	
	private String fname;
	private String mname;
	private String lname;
	private String address;
	private String username;
	private String password;
	private int account;
	
	public Customer() {
		super();
		AllFiles.cmap.put(username, password);
		FileStuff.writeCustomerMap(AllFiles.cmap);
		AllFiles.cList.add(this);
		FileStuff.writeCustomerFile(AllFiles.cList);
		LogThis.LogIt("info", "A new customer account has been recorded.");
	}

	public Customer(String fname, String mname, String lname, String address, String username, String password,
			int account) {
		super();
		this.fname = fname;
		this.mname = mname;
		this.lname = lname;
		this.address = address;
		this.username = username;
		this.password = password;
		this.account = account;
		AllFiles.cmap.put(username, password);
		FileStuff.writeCustomerMap(AllFiles.cmap);
		AllFiles.cList.add(this);
		FileStuff.writeCustomerFile(AllFiles.cList);
		LogThis.LogIt("info", "A new customer account has been recorded.");
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Customer [First Name = " + fname + ", Middle Name = " + mname + ", Last Name = " + lname + ", Address = " + address
				+ ", Username = " + username + ", Password = " + password + ", Number of Accounts = " + account + "]";
	}
	
}
