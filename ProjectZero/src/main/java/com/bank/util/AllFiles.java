package com.bank.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bank.menu.Menu;
import com.bank.users.Accounts;
import com.bank.users.Customer;

public class AllFiles {
	
	public static List<Customer> cList = new ArrayList<>();
	public static Map<String,String> cmap = new HashMap<>();
	public static Map<String,String> emap = new HashMap<>();
	public static Map<String,String> amap = new HashMap<>();
	public static List<Accounts> accList = new ArrayList<>();
	public static Set<Integer> accNumbers = new HashSet<>();
	
	public static Customer findCustByUser(String username) {
		for(int i = 0; i < cList.size();i++) {
			String name = cList.get(i).getUsername();
			if(username.equals(name)) {
				return cList.get(i);
			}
		}
		System.err.println("Customer not found.");
		Menu.startMenu();
		return null;
	}
	
	public static int accountNumber() {
		int num = (int) (Math.random()*100000);
		if(accNumbers.contains(num)==true) {
			accountNumber();
		}else {
			accNumbers.add(num);
		}
		return num;
	}
	
	public static Accounts findAcctByNumber(int num) {
		for(int i = 0; i < accList.size(); i++) {
			int acctNum = accList.get(i).getAccountNumber();
			if(num==acctNum) {
				return accList.get(i);
			}
		}
		System.err.println("Account not found.");
		Menu.startMenu();
		return null;
	}
	
	public static void findAcctsByUser(String user) {
		try {
			for(int i = 0; i < AllFiles.accList.size(); i++) {
				Accounts acct = AllFiles.accList.get(i);
				if(acct.getUsername().equals(user)) {
					System.out.println("\n["+(i+1)+"]" + acct);
				}else if(acct.getJointUser().equals(user)) {
					System.out.println("\n["+(i+1)+"]" + acct);
				}else {
					System.out.println("This user has no accounts.");
				}
			}
		}catch(NullPointerException nex) {
			System.out.println("still issues with getJointUser");
		}
		
	}

}
