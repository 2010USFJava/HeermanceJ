package com.bank.menu;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.bank.users.Accounts;
import com.bank.users.Accounts.accountType;
import com.bank.users.Admin;
import com.bank.users.Customer;
import com.bank.users.Employee;
import com.bank.util.AllFiles;
import com.bank.util.FileStuff;
import com.bank.util.LogThis;

public class Menu {
	
	static Scanner scan = new Scanner(System.in);
	static DecimalFormat df = new DecimalFormat("#.00");
	static boolean continueInput = true;
	
	public static void startMenu(){
		System.out.println("=====================================");
		System.out.println("Welcome to 3rd Jeneral Bank! \nPlease either [l]og in or [r]egister!");
		System.out.println("=====================================");
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
		System.out.println("Please enter your middle name, or enter space if you want to skip this.");
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
		do {
			try {
				System.out.println("Hello " + cust.getFname());
				if(cust.getAccount()==0) {
					customerMenu1(cust);
				}else {//add in option to choose accounts if more than 1
					System.out.println("Please select which account you would like to access. Press 0 to exit.");
					AllFiles.findAcctsByUser(cust.getUsername());
					int account = scan.nextInt();
					if(account==0) {
						System.out.println("=======================================================");
						System.out.println("Thank you for using 3rd Jeneral Bank. Have a great day!");
						System.out.println("=======================================================");
					}else {
						Accounts acct = AllFiles.accList.get(account-1);
						customerMenu2(cust, acct);
					}
				}
				continueInput=false;
			}catch(InputMismatchException ex) {
				System.out.println("Invalid input. A number is required.");
				scan.nextLine();
			}
		}while(continueInput);
	}
	
	public static void customerMenu1(Customer cust) {
		do {
			try {
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
					System.out.println("=======================================================");
					System.out.println("Thank you for using 3rd Jeneral Bank. Have a great day!");
					System.out.println("=======================================================");
					break;
				default:
					System.out.println("Input not recognized.");
					customerMenu(cust);
				}
				continueInput=false;
			}catch(InputMismatchException ex) {
				System.out.println("Invalid input. A number is required.");
	            scan.nextLine();
			}
		}while(continueInput);
		
	}
	
	public static void customerMenu2(Customer cust, Accounts acct) {
		do {
			try {
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
					Accounts.deposit(cust, acct);
					customerMenu(cust);
					break;
				case 5://withdraw
					Accounts.withdraw(cust, acct);
					customerMenu(cust);
					break;	
				case 6://transfer
					Accounts.transfer(cust);
					customerMenu(cust);
					break;
				case 0://Exit
					System.out.println("=======================================================");
					System.out.println("Thank you for using 3rd Jeneral Bank. Have a great day!");
					System.out.println("=======================================================");
					break;
				default:
					System.out.println("Input not recognized.");
					customerMenu(cust);
				}
				continueInput=false;
			}catch(InputMismatchException ex1) {
				System.out.println("Invalid input. A number is required.");
				scan.nextLine();
			}
		}while(continueInput);
	}
	
	public static void employeeMenu(String username) {
		do {
			try {
				System.out.println("Please choose from the following options.");
				System.out.println("\t [1] Approve or Deny Account Applications");
				System.out.println("\t [2] Assist a Customer");
				System.out.println("\t [0] Exit");
				int choice = scan.nextInt();
				switch(choice) {
				case 1://approve/deny acct
					approveOrDeny(username);
					break;
				case 2://assist customer
					System.out.println("Please enter the customer's username.");
					String custUser = scan.next();
					Customer cust = AllFiles.findCustByUser(custUser);
					System.out.println(cust.toString());
					System.out.println("Please select from the following options:");
					System.out.println("\t [1] View Account Information");
					System.out.println("\t [2] View Customer Information");
					System.out.println("\t [3] Complete Customer Transaction");
					System.out.println("\t [0] Exit");
					int opt = scan.nextInt();
					switch(opt) {
					case 1://view acct info
						AllFiles.findAcctsByUser(custUser);
						System.out.println("Press any key to return.");
						scan.next();
						employeeMenu(username);
						break;
					case 2://view cust info
						System.out.println(cust.toString());
						System.out.println("Press any key to return.");
						scan.next();
						employeeMenu(username);
						break;
					case 3://cust transaction
						System.out.println("Please select which account to use. Press 0 to exit.");
						AllFiles.findAcctsByUser(custUser);
						int choose = scan.nextInt();
						if(choose==0) {
							employeeMenu(username);
						}else {
							Accounts acct = AllFiles.accList.get(choose-1);
							System.out.println("What would you like to do?");
							System.out.println("\t [1] Check Account Balance");
							System.out.println("\t [2] Deposit");
							System.out.println("\t [3] Withdraw");
							System.out.println("\t [4] Initiate a Transfer");
							System.out.println("\t [0] Exit");
							int again = scan.nextInt();
							switch(again) {
							case 1://check balance
								System.out.println("Your current balance is " + df.format(acct.getBalance()));
								employeeMenu(username);
								break;
							case 2://deposit
								Accounts.deposit(cust, acct);
								employeeMenu(username);
								break;
							case 3://withdraw
								Accounts.withdraw(cust, acct);
								employeeMenu(username);
								break;
							case 4://transfer
								Accounts.transfer(cust);
								employeeMenu(username);
								break;
							case 0://Exit
								employeeMenu(username);
								break;
							default: System.out.println("Input not recognized. Please try again.");
									employeeMenu(username);
							}
						}
						break;
					case 0://exit
						employeeMenu(username);
						break;
					default: System.out.println("Input not recognized. Please try again.");
							employeeMenu(username);
					}
					break;
				case 0:
					System.out.println("Goodbye.");
					break;
				default:
					System.out.println("Input not recognized. Please try again.");
					employeeMenu(username);
				}
				continueInput=false;
			}catch(InputMismatchException ex) {
				System.out.println("Invalid input. A number is required.");
				scan.nextLine();
			}
		}while(continueInput);
	}
	
	public static void adminMenu(String username) {
		do {
			try {
				System.out.println("Please choose from the following options.");
				System.out.println("\t [1] Approve or Deny Account Application");
				System.out.println("\t [2] Manage Employee Accounts");
				System.out.println("\t [3] Manage Customer Accounts");
				System.out.println("\t [0] Exit");
				int choice = scan.nextInt();
				switch(choice) {
				case 1://approve/deny acct
					approveOrDeny(username);
					break;
				case 2://employee mgmt
					System.out.println("Please select from the following options:");
					System.out.println("\t[1] View All Employee Logins");
					System.out.println("\t[2] Add New Employee Logins");
					System.out.println("\t[3] Delete Employee Login");
					System.out.println("\t[4] View All Admin Logins");
					System.out.println("\t[5] Add New Admin Login");
					System.out.println("\t[6] Delete Admin Login");
					System.out.println("\t[0] Exit");
					int admin = scan.nextInt();
					switch(admin) {
					case 1://view employees
						System.out.println("Employee usernames and passwords:");
						System.out.println(AllFiles.emap);
						adminMenu(username);
						break;
					case 2://add employee
						System.out.println("Enter the new employee's username:");
						String euser = scan.next();
						System.out.println("Enter the new employee's password:");
						String epass = scan.next();
						new Employee(euser,epass);
						adminMenu(username);
						break;
					case 3://delete employee
						System.out.println("Please enter the username of the employee whose login you wish to delete:");
						String edelete = scan.next();
						if(AllFiles.emap.containsKey(edelete)==true) {
							System.out.println("Are you sure you want to delete the login for " + edelete + "?");
							String edcheck = scan.next();
							if(edcheck.equalsIgnoreCase("y")) {
								AllFiles.emap.remove(AllFiles.emap.get(edelete));
								FileStuff.writeEmployeeFile(AllFiles.emap);
								adminMenu(username);
							}
						}else {
							System.out.println("That login was not found. Please try again.");
							adminMenu(username);
						}
						break;
					case 4://view admins
						System.out.println("Admin usernames and passwords:");
						System.out.println(AllFiles.amap);
						adminMenu(username);
						break;
					case 5://add admin
						System.out.println("Enter the new admin's username:");
						String auser = scan.next();
						System.out.println("Enter the new admin's password:");
						String apass = scan.next();
						new Admin(auser,apass);
						adminMenu(username);
						break;
					case 6://delete admin
						System.out.println("Please enter the username of the admin whose login you wish to delete:");
						String adelete = scan.next();
						if(AllFiles.amap.containsKey(adelete)==true) {
							System.out.println("Are you sure you want to delete the login for " + adelete + "?");
							String adcheck = scan.next();
							if(adcheck.equalsIgnoreCase("y")) {
								AllFiles.amap.remove(AllFiles.amap.get(adelete));
								FileStuff.writeAdminFile(AllFiles.amap);
								adminMenu(username);
							}
						}else {
							System.out.println("That login was not found. Please try again.");
							adminMenu(username);
						}
						break;
					case 0://exit
						adminMenu(username);
						break;
					default: System.out.println("Input not recognized. Please try again.");
							adminMenu(username);
					}
					break;
				case 3://cust mgmt
					System.out.println("Please enter the customer's username.");
					String custUser = scan.next();
					Customer cust = AllFiles.findCustByUser(custUser);
					cust.toString();
					System.out.println("Please select from the following options:");
					System.out.println("\t [1] View Account Information");
					System.out.println("\t [2] View Customer Information");
					System.out.println("\t [3] Complete Customer Transaction");
					System.out.println("\t [4] Cancel Customer Account");
					System.out.println("\t [0] Exit");
					int opt = scan.nextInt();
					switch(opt) {
					case 1://view acct info
						AllFiles.findAcctsByUser(custUser);
						System.out.println("Press any key to return.");
						scan.next();
						adminMenu(username);
						break;
					case 2://view cust info
						System.out.println(cust.toString());
						System.out.println("Press any key to return.");
						scan.next();
						adminMenu(username);
						break;
					case 3://cust transaction
						System.out.println("Please select which account to use. Press 0 to exit.");
						AllFiles.findAcctsByUser(custUser);
						int choose = scan.nextInt();
						if(choose==0) {
							adminMenu(username);
						}else {
							Accounts acct = AllFiles.accList.get(choose-1);
							System.out.println("What would you like to do?");
							System.out.println("\t [1] Check Account Balance");
							System.out.println("\t [2] Deposit");
							System.out.println("\t [3] Withdraw");
							System.out.println("\t [4] Initiate a Transfer");
							System.out.println("\t [0] Exit");
							int again = scan.nextInt();
							switch(again) {
							case 1://view balance
								System.out.println("Your current balance is " + df.format(acct.getBalance()));
								adminMenu(username);
								break;
							case 2://deposit
								Accounts.deposit(cust, acct);
								adminMenu(username);
								break;
							case 3://withdraw
								Accounts.withdraw(cust, acct);
								adminMenu(username);
								break;
							case 4://transfer
								Accounts.transfer(cust);
								adminMenu(username);
								break;
							case 0://exit
								adminMenu(username);
								break;
							default: System.out.println("Input not recognized. Please try again.");
									adminMenu(username);
							}
						}
						break;
					case 4://cancel cust acct
						System.out.println("Please select which account to cancel. Press 0 to exit.");
						AllFiles.findAcctsByUser(custUser);
						int del = scan.nextInt();
						if(del==0) {
							adminMenu(username);
						}else {
							Accounts acct = AllFiles.accList.get(del-1);
							System.out.println("Are you sure you want to cancel account " + acct.getAccountNumber() + "? (y/n)");
							String delete = scan.next();
							if(delete.equalsIgnoreCase("y")) {
								AllFiles.accList.remove(del-1);
								FileStuff.writeAccountsList(AllFiles.accList);
								System.out.println("Account " + acct.getAccountNumber() + " has been deleted.");
								LogThis.LogIt("info", "Admin " + username + " has deleted customer account " + acct.getAccountNumber() + ".");
							}else adminMenu(username);
						}
						break;
					case 0://exit
						adminMenu(username);
						break;
					default: System.out.println("Input not recognized. Please try again.");
							adminMenu(username);
					}
					break;
				case 0://exit
					System.out.println("Goodbye.");
					break;
				default:
					System.out.println("Input not recognized. Please try again.");
					adminMenu(username);
				}
				continueInput=false;
			}catch(InputMismatchException ex) {
				System.out.println("Invalid input. A number is required.");
				scan.nextLine();
			}
		}while(continueInput);
	}
	
	public static void resetPassword(Customer cust) {
		System.out.println("Please enter your new password.");
		String newpass1 = scan.next();
		System.out.println("Please re-enter your new password.");
		String newpass2 = scan.next();
		if(newpass1.equals(newpass2)) {
			AllFiles.cmap.replace(cust.getUsername(), newpass2);
			cust.setPassword(newpass2);
			FileStuff.writeCustomerMap(AllFiles.cmap);
			FileStuff.writeCustomerFile(AllFiles.cList);
			LogThis.LogIt("info", cust.getUsername() + " has updated this account's password.");
			System.out.println("Your password has been updated.");
			customerMenu(cust);
		}else {
			System.out.println("Your entries did not match.");
			resetPassword(cust);
		}
	}
	
	public static void applyForAccount(Customer cust) {
		do {
			try {
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
					Accounts a = new Accounts(cust.getUsername(),null, num, type, false, 0.0);
					AllFiles.accList.add(a);
					FileStuff.writeAccountsList(AllFiles.accList);
					break;
				case 2:
					type = accountType.SAVINGS;
					Accounts b = new Accounts(cust.getUsername(),null, num, type, false, 0.0);
					AllFiles.accList.add(b);
					FileStuff.writeAccountsList(AllFiles.accList);
					break;
				case 3:
					type = accountType.JOINTCHECKING;
					System.out.println("Please enter the username of the customer you would like to apply for a joint account with.");
					String jointUser = scan.next();
					Accounts c = new Accounts(cust.getUsername(),jointUser, num, type, false, 0.0);
					AllFiles.accList.add(c);
					FileStuff.writeAccountsList(AllFiles.accList);
					break;
				default: 
					System.out.println("I'm sorry. That was not a valid response.");
					customerMenu(cust);
				} 
				LogThis.LogIt("info", "An account was applied for.");
				System.out.println("Thank you for applying for a new account. You will receive an email when it is approved.");
				customerMenu(cust);
				continueInput=false;
			}catch(InputMismatchException ex) {
				System.out.println("Invalid input. A number is required.");
				scan.nextLine();
			}
		}while(continueInput);
	}

	public static void approveOrDeny(String username) {
		do {
			try {
				System.out.println("Please select the account to review. Press 0 to exit.");
				for(int i = 0; i < AllFiles.accList.size(); i++) {
					Accounts acct = AllFiles.accList.get(i);
					if(acct.isAccountStatus()==false) {
						System.out.println("\n["+(i+1)+"]" + acct);
					}
				}
				int review = scan.nextInt();
				if(review==0) {
					if(AllFiles.emap.containsKey(username)==true) {
						employeeMenu(username);
					}else if(AllFiles.amap.containsKey(username)==true) {
						adminMenu(username);
					}
				}else {
					Accounts acc = AllFiles.accList.get(review-1);
					Customer a = AllFiles.findCustByUser(acc.getUsername());
					a.toString();
					System.out.println("Would you like to approve account " + acc.getAccountNumber() + "? (y/n)");
					String approve = scan.next();
					if(approve.equalsIgnoreCase("y")) {
						acc.setAccountStatus(true);
						a.setAccount(a.getAccount()+1);
						FileStuff.writeAccountsList(AllFiles.accList);
						FileStuff.writeCustomerFile(AllFiles.cList);
						System.out.println("You have approved this account.");
						LogThis.LogIt("info", "A " + acc.getType() + " account for " + a.getUsername() + " has been approved by " + username + ".");
						approveOrDeny(username);
					}else if(approve.equalsIgnoreCase("n")) {
						System.out.println("You have denied this account.");
						AllFiles.accList.remove(review-1);
						LogThis.LogIt("info", "A " + acc.getType() + " account for " + a.getUsername() + " has been denied by " + username + ".");
						approveOrDeny(username);
					}
				}
				continueInput=false;
			}catch(InputMismatchException ex) {
				System.out.println("Invalid input. A number is required.");
				scan.nextLine();
			}
		}while(continueInput);	
	}
	
	
}
