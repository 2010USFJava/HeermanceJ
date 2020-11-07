package com.bank.users;

public class Accounts {
	
	private String username;
	private String jointUser;
	private int accountNumber;
	public enum accountType {
		CHECKING,
		SAVINGS,
		JOINTCHECKING;
	}
	public accountType type;
	private boolean accountStatus = false;
	private double balance;
	
	public Accounts() {
		super();
	}
	public Accounts(String username, String jointUser, int accountNumber, accountType type, boolean accountStatus, double balance) {
		super();
		this.username = username;
		this.jointUser = jointUser;
		this.accountNumber = accountNumber;
		this.type = type;
		this.accountStatus = accountStatus;
		this.balance = balance;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getJointUser() {
		return jointUser;
	}
	public void setJointUser(String jointUser) {
		this.jointUser = jointUser;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public accountType getType() {
		return type;
	}
	public void setAccountType(accountType type) {
		this.type = type;
	}
	public boolean isAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(boolean accountStatus) {
		this.accountStatus = accountStatus;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Accounts [Username = " + username + ", Joint User = " + jointUser + ", Account Number = " + accountNumber + ", Account Type = " + type
				+ ", Account Active? = " + accountStatus + "Balance = " + balance + "]";
	}
	
}
