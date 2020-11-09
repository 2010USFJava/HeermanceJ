package com.bank.testing;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.bank.users.Accounts;
import com.bank.users.Accounts.accountType;
import com.bank.users.Customer;

public class AccountsTesting {
	
	static Customer cust;
	static Customer cust2;
	static Accounts a;
	static Accounts b;
	static Accounts c;
	
	@BeforeClass
	public static void loadAccts() {
		accountType checking = accountType.CHECKING;
		accountType savings = accountType.SAVINGS;
		accountType joint = accountType.JOINTCHECKING;
		cust = new Customer("First","M","Last","address","Username","Password",2);
		cust2 = new Customer("f","m","l","address","user","pass",1);
		a = new Accounts(cust.getUsername(),null,123456,checking,true,100.0);
		b = new Accounts(cust.getUsername(),null,987654,savings,true,500.0);
		c = new Accounts(cust.getUsername(),cust2.getUsername(),159753,joint,true,250);
		System.out.println("Loaded accounts:");
		System.out.println(a.toString());
		System.out.println(b.toString());	
		System.out.println(c.toString());
	}
	

	@SuppressWarnings("deprecation")
	@Test
	public void depositTest() {//input 50
		Accounts.deposit(cust, a);
		double aBalance = a.getBalance();
		System.out.println(aBalance);
		assertEquals(150.0, aBalance);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void withdrawalTest() {//input 150
		Accounts.withdraw(cust, b);
		System.out.println(b.getBalance());
		assertEquals(350.0,b.getBalance());
	}
	
	@Test
	public void transferTest() {//input 
		Accounts.transfer(cust);
	}

}
