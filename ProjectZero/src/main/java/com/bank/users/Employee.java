package com.bank.users;

import java.io.Serializable;

public class Employee implements Serializable{

	private static final long serialVersionUID = 2936737997575049234L;
	
	private String username;
	private String password;
	private double deposit;
	private double withdrawal;
	private double transfer;
	static boolean accountStatus = false;
	
	public Employee() {
		super();
	}
	
	public Employee(String username, String password, double deposit, double withdrawal, double transfer) {
		super();
		this.username = username;
		this.password = password;
		this.deposit = deposit;
		this.withdrawal = withdrawal;
		this.transfer = transfer;
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
	public double getDeposit() {
		return deposit;
	}
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}
	public double getWithdrawal() {
		return withdrawal;
	}
	public void setWithdrawal(double withdrawal) {
		this.withdrawal = withdrawal;
	}
	public double getTransfer() {
		return transfer;
	}
	public void setTransfer(double transfer) {
		this.transfer = transfer;
	}
	public static boolean isAccountStatus() {
		return accountStatus;
	}
	public static void setAccountStatus(boolean accountStatus) {
		Employee.accountStatus = accountStatus;
	}
	
	@Override
	public String toString() {
		return "Employee [username=" + username + ", password=" + password + ", deposit=" + deposit + ", withdrawal="
				+ withdrawal + ", transfer=" + transfer + "]";
	}
	
	

}
