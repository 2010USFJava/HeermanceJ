package com.bank.bean;

import java.io.Serializable;

public class Accounts_Users implements Serializable{
	
	private static final long serialVersionUID = 7537479169691653597L;
	
	private long custid;
	private long acctid;
	public Accounts_Users() {
		super();
	}
	public Accounts_Users(long custid, long acctid) {
		super();
		this.custid = custid;
		this.acctid = acctid;
	}
	public long getCustid() {
		return custid;
	}
	public void setCustid(long custid) {
		this.custid = custid;
	}
	public long getAcctid() {
		return acctid;
	}
	public void setAcctid(long acctid) {
		this.acctid = acctid;
	}
	@Override
	public String toString() {
		return "Accounts_Users [custid=" + custid + ", acctid=" + acctid + "]";
	}
	
	

}
