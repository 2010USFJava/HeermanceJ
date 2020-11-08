package com.bank.users;

import java.io.Serializable;

import com.bank.util.AllFiles;
import com.bank.util.FileStuff;
import com.bank.util.LogThis;

public class Admin implements Serializable{

	private static final long serialVersionUID = 1639333659977845702L;
	
	private String username;
	private String password;
	
	public Admin() {
		super();
		AllFiles.amap.put(username,password);
		FileStuff.writeAdminFile(AllFiles.amap);
		LogThis.LogIt("info", "A new admin login has been created.");
	}
	
	public Admin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		AllFiles.emap.put(username,password);
		FileStuff.writeAdminFile(AllFiles.amap);
		LogThis.LogIt("info", "A new admin login has been created.");
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
		return "Admin [username=" + username + ", password=" + password + "]";
	}

}
