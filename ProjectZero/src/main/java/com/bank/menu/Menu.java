package com.bank.menu;

import java.text.DecimalFormat;
import java.util.Scanner;

import com.bank.users.Accounts;
import com.bank.users.Accounts.accountType;
import com.bank.users.Customer;
import com.bank.util.AllFiles;
import com.bank.util.LogThis;

public class Menu {
	
	static Scanner scan = new Scanner(System.in);
	static DecimalFormat df = new DecimalFormat("#.00");
	
	public static void startMenu(){
		System.out.println("============================");
		System.out.println("Welcome to 3rd Jeneral Bank! \nPlease either [l]og in or [r]egister!");
		System.out.println("============================");
		String input = scan.nextLine();
		
		switch(input.toLowerCase()) {
		case "l":
			login();
			break;
		case "r":
			register();
			break;
		default:
			System.out.println("Input not recognized. Please try again.");
			startMenu();
			break;
		}
	}
	
	public static void login() {
		System.out.println("Please enter your username.");
		String username = scan.nextLine();
		System.out.println("Please enter your password.");
		String password = scan.nextLine();
		System.out.println(AllFiles.cmap.keySet());
		System.out.println(AllFiles.cmap.values());
		if(AllFiles.cmap.getOrDefault(username,"password").equals(password)) {
				Customer cust = AllFiles.findCustByUser(username);
				customerMenu(cust);
			}
		else if(AllFiles.emap.getOrDefault(username,"password").equals(password)) {
				employeeMenu(username);
			}
		else if(AllFiles.amap.getOrDefault(username,"password").equals(password)) {
				adminMenu(username);
			}
		else {
			System.out.println("This username/password combination was not found. Please try again.");
			login();
			}
	}
	
	public static void register() {
		System.out.println("Please enter your first name.");
		String fname = scan.nextLine();
		System.out.println("Please enter your middle name, or press Enter if you want to skip this.");
		String mname = scan.nextLine();
		System.out.println("Please enter your last name.");
		String lname = scan.nextLine();
		System.out.println("Please enter your address.");
		String address = scan.nextLine();
		System.out.println("Please create a username.");
		String ruser = scan.nextLine();
		if(AllFiles.cmap.containsKey(ruser)==false) {
			System.out.println("This username is available.");
		}else {
			System.out.println("This username is not available. Please try a different username.");
			register();
		}
		System.out.println("Please create a password.");
		String rpass = scan.nextLine();
		new Customer(fname, mname, lname, address, ruser, rpass, 0);
		System.out.println("Congratulations! You have created a new account! Please login.");
		LogThis.LogIt("info", "A new account has been created");
		startMenu();
		
	}
	
	public static void customerMenu(Customer cust) {
		System.out.println("Hello " + cust.getFname());
		if(cust.getAccount()==0||AllFiles.findAcctByNumber(cust.getAccount()).isAccountStatus()==false) {
			customerMenu1(cust);
		}else {//add in option to choose accounts if more than 1
//			System.out.println("Please select which account you would like to access:");
			
			Accounts acct = AllFiles.findAcctByNumber(cust.getAccount());
			customerMenu2(cust, acct);
		}
	}
	
	public static void customerMenu1(Customer cust) {
		System.out.println("Please select from the following account options:");
		System.out.println("\t[1] Reset your password");
		System.out.println("\t[2] Apply for an account");
		System.out.println("\t[0] Exit");
		int option = scan.nextInt();
		switch(option) {
		case 1://reset password
			resetPassword(cust);
			break;
		case 2://Apply for account
			applyForAccount(cust);
			break;
		case 0://Exit
			System.out.println("Thank you for using 3rd Jeneral Bank. Have a great day!");
			System.out.println("=======================================================");
			break;
		default:
			System.out.println("Input not recognized.");
			customerMenu(cust);
		}
		
	}
	
	public static void customerMenu2(Customer cust, Accounts acct) { //still need to transfer and add in viewing multiple accounts
		System.out.println("Please select from the following account options:");
		System.out.println("\t[1] Reset your password");
		System.out.println("\t[2] Apply for an account");
		System.out.println("\t[3] Check your account balance");
		System.out.println("\t[4] Deposit");
		System.out.println("\t[5] Withdraw");
		System.out.println("\t[6] Initiate a Transfer");
		System.out.println("\t[0] Exit");
		int option = scan.nextInt();
		
		switch(option) {
		case 1://reset password
			resetPassword(cust);
			break;
		case 2://Apply for account
			applyForAccount(cust);
			break;
		case 3://check balance
			System.out.println("Your current balance is " + df.format(acct.getBalance()));
			customerMenu(cust);
			break;
		case 4://deposit
			System.out.println("Please enter the amount that you would like to deposit.");
			double deposit = 0.0;
			if(deposit < 0) {
				System.out.println("You cannot deposit a negative number.");
				customerMenu(cust);
			}else System.out.println("You want to deposit " + df.format(deposit) +"? (y/n)");
				String dep = scan.nextLine();
				if(dep.equalsIgnoreCase("y")) {
					acct.setBalance(acct.getBalance() + deposit);
					LogThis.LogIt("info", cust.getFname() + " " + cust.getLname() +" has deposited " + df.format(deposit) + " into account " + acct.getAccountNumber() + ".");
				}
				else customerMenu(cust);
			break;
		case 5://withdraw
			System.out.println("Please enter the amount that you would like to withdraw.");
			double withdrawal = 0.0;
			if(withdrawal < 0) {
				System.out.println("You cannot withdraw a negative number.");
				customerMenu(cust);
			}else if(acct.getBalance() - withdrawal < 0) {
				System.out.println("This withdrawal is higher than your available balance.");
				customerMenu(cust);
			}else System.out.println("You want to withdraw " + df.format(withdrawal) +"? (y/n)");
				String wd = scan.nextLine();
				if(wd.equalsIgnoreCase("y")) {
					acct.setBalance(acct.getBalance() - withdrawal);
					LogThis.LogIt("info", cust.getFname() + " " + cust.getLname() +" has withdrawn " + df.format(withdrawal) + " from account " + acct.getAccountNumber() + ".");
				}
				else customerMenu(cust);
			break;	
		case 6://transfer
//			
			break;
		case 0://Exit
			System.out.println("Thank you for using 3rd Jeneral Bank. Have a great day!");
			System.out.println("=======================================================");
			break;
		default:
			System.out.println("Input not recognized.");
			customerMenu(cust);
		}
	}
	
	public static void employeeMenu(String username) {//make sure to add +1 to customer object when approving account
		
	}
	
	public static void adminMenu(String username) {
		
	}
	
	public static void resetPassword(Customer cust) {
		System.out.println("Please enter your new password.");
		String newpass1 = scan.next();
		System.out.println("Please re-enter your new password.");
		String newpass2 = scan.next();
		if(newpass1.equals(newpass2)) {
			cust.setPassword(newpass2);
			AllFiles.cmap.replace(cust.getUsername(), cust.getPassword(), newpass1);
			LogThis.LogIt("info", cust.getUsername() + " has updated this account's password.");
			System.out.println("Your password has been updated.");
			customerMenu(cust);
		}else {
			System.out.println("Your entries did not match.");
			resetPassword(cust);
		}
	}
	
	public static void applyForAccount(Customer cust) {
		System.out.println("Please select the type of account you would like to open:");
		System.out.println("\t[1] Checking");
		System.out.println("\t[2] Savings");
		System.out.println("\t[3] Joint Checking");
		int choice = scan.nextInt();
		accountType type = null;
		int num = AllFiles.accountNumber();
		switch(choice) {
		case 1:
			type = accountType.CHECKING;
			new Accounts(cust.getUsername(),null, num, type, false, 0.0);
			break;
		case 2:
			type = accountType.SAVINGS;
			new Accounts(cust.getUsername(),null, num, type, false, 0.0);
			break;
		case 3:
			type = accountType.JOINTCHECKING;
			System.out.println("Please enter the username of the customer you would like to apply for a joint account with.");
			String jointUser = scan.nextLine();
			new Accounts(cust.getUsername(),jointUser, num, type, false, 0.0);
			break;
		default: 
			System.out.println("I'm sorry. That was not a valid response.");
			customerMenu(cust);
		} 
		LogThis.LogIt("info", "An account was applied for.");
		System.out.println("Thank you for applying for a new account. You will receive an email when it is approved.");
		customerMenu(cust);
	}

	
	
	
}
