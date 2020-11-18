package com.bank.bean;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Accounts implements Serializable{

	private static final long serialVersionUID = 2700709332540864723L;
	
	static Scanner scan = new Scanner(System.in);
	static DecimalFormat df = new DecimalFormat("#.00");
	static boolean continueInput = true;
	
	private long acctid;
	public enum accountType{
		CHECKING("CHECKING"),
		SAVINGS("SAVINGS");
		private String accttype;
		private accountType(String atype) {
			this.accttype=atype;
		}
		@Override
		public String toString() {
			return accttype;
		}
	}
	public accountType type;
	private boolean accountStatus = false;
	private double balance;
	
	public Accounts() {
		super();
	}
	
	public Accounts(long acctid, accountType type, boolean accountStatus, double balance) {
		super();
		this.acctid=acctid;
		this.type=type;
		this.accountStatus=accountStatus;
		this.balance=balance;
	}
	public long getAcctid() {
		return acctid;
	}
	public void setAcctid(long acctid) {
		this.acctid=acctid;
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
		return "\n---------------------------------------------------------------------------------------------------\n"
				+ "Account ID: "+acctid+", Account Type = " + type + ", Account Active? = " + accountStatus + ", Balance=" + balance +
				"\n--------------------------------------------------------------------------------------------------";
	}
	
	

}
