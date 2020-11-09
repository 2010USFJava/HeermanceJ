package com.bank.users;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.bank.util.AllFiles;
import com.bank.util.FileStuff;
import com.bank.util.LogThis;

public class Accounts implements Serializable{
	
	private static final long serialVersionUID = -892130961581860878L;
	
	static Scanner scan = new Scanner(System.in);
	static DecimalFormat df = new DecimalFormat("#.00");
	static boolean continueInput = true;
	
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
				+ ", Account Active? = " + accountStatus + ", Balance = " + balance + "]";
	}
	
	public static void deposit(Customer cust, Accounts acct) {
		do {
			try {
				System.out.println("Please enter the amount that you would like to deposit.");
				double deposit = scan.nextDouble();
				if(deposit < 0) {
					System.out.println("You cannot deposit a negative number.");
					deposit(cust, acct);
				}else System.out.println("You want to deposit " + df.format(deposit) +"? (y/n)");
					String dep = scan.next();
					if(dep.equalsIgnoreCase("y")) {
						acct.setBalance(acct.getBalance() + deposit);
						FileStuff.writeAccountsList(AllFiles.accList);
						LogThis.LogIt("info", cust.getFname() + " " + cust.getLname() +" has deposited " + df.format(deposit) + " into account " + acct.getAccountNumber() + ".");
					}
				continueInput=false;
			}catch(InputMismatchException ex) {
				System.out.println("Invalid input. A number is required.");
				scan.next();
			}
		}while (continueInput);
	}
	
	public static void withdraw(Customer cust, Accounts acct) {
		do {
			try {
				System.out.println("Please enter the amount that you would like to withdraw.");
				double withdrawal = scan.nextDouble();
				if(withdrawal < 0) {
					System.out.println("You cannot withdraw a negative number.");
					withdraw(cust, acct);
				}else if(acct.getBalance() - withdrawal < 0) {
					System.out.println("You cannot withdraw a higher amount than your available balance.");
					withdraw(cust, acct);
				}else System.out.println("You want to withdraw " + df.format(withdrawal) +"? (y/n)");
					String wd = scan.next();
					if(wd.equalsIgnoreCase("y")) {
						acct.setBalance(acct.getBalance() - withdrawal);
						FileStuff.writeAccountsList(AllFiles.accList);
						LogThis.LogIt("info", cust.getFname() + " " + cust.getLname() +" has withdrawn " + df.format(withdrawal) + " from account " + acct.getAccountNumber() + ".");
					}
					continueInput=false;
			}catch(InputMismatchException ex) {
				System.out.println("Invalid input. A number is required.");
				scan.next();
			}
		}while (continueInput);
	}
	
	public static void transfer(Customer cust) {
		Accounts acct1 = null;
		Accounts acct2 = null;
		do {
			try {
				System.out.println("Please select the account to transfer to.");
				AllFiles.findAcctsByUser(cust.getUsername());
				int choose1 = scan.nextInt();
				if(choose1==0) {
					transfer(cust);
				}else if(choose1 > AllFiles.accList.size()+1) {
					System.out.println("Invalid option.");
					transfer(cust);
				}else {
					acct1 = AllFiles.accList.get(choose1-1);
				}
				System.out.println("Please select the account to transfer from.");
				AllFiles.findAcctsByUser(cust.getUsername());
				int choose2 = scan.nextInt();
				if(choose2==0) {
					transfer(cust);
				}else if(choose2 > AllFiles.accList.size()+1) {
					System.out.println("Invalid option.");
					transfer(cust);
				}else {
					acct2 = AllFiles.accList.get(choose2-1);
				}
				System.out.println("Please input the amount you want to transfer.");
				double amount = scan.nextDouble();
				if(amount < 0) {
					System.out.println("You can't transfer a negative number.");
					transfer(cust);
				}else if(amount > acct2.getBalance()) {
					System.out.println("You can't transfer a higher amount than your available balance. ");
					transfer(cust);
				}else {
					acct1.setBalance(acct1.getBalance()+amount);
					acct2.setBalance(acct2.getBalance()-amount);
					FileStuff.writeAccountsList(AllFiles.accList);
					System.out.println(amount + " was transferred to account " + acct1.getAccountNumber() + " from " + acct2.getAccountNumber() + ".");
					System.out.println(acct1.getAccountNumber() + " balance: " + acct1.getBalance());
					System.out.println(acct2.getAccountNumber() + " balance: " + acct2.getBalance());
					LogThis.LogIt("info", amount + " was transferred to account " + acct1.getAccountNumber() + " from " + acct2.getAccountNumber() + ".");
				}	
				continueInput=false;
			}catch(InputMismatchException ex3) {
				System.out.println("Invalid input. A number is required.");
				scan.next();
			}
		}while(continueInput);
		
	}
	
}
