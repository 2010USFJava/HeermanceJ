package com.bank.users;

import java.io.Serializable;

import com.bank.util.AllFiles;
import com.bank.util.FileStuff;
import com.bank.util.LogThis;

public class Employee implements Serializable{

	private static final long serialVersionUID = 2936737997575049234L;
	
	private String username;
	private String password;
	
	public Employee() {
		super();
		AllFiles.emap.put(username,password);
		FileStuff.writeEmployeeFile(AllFiles.emap);
		System.out.println("A new employee login has been created.");
		LogThis.LogIt("info", "A new employee login has been created.");
	}
	
	public Employee(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		AllFiles.emap.put(username,password);
		FileStuff.writeEmployeeFile(AllFiles.emap);
		System.out.println("A new employee login has been created.");
		LogThis.LogIt("info", "A new employee login has been created.");
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
	
	@Override
	public String toString() {
		return "Employee [username=" + username + ", password=" + password + "]";
	}
	
	

}
