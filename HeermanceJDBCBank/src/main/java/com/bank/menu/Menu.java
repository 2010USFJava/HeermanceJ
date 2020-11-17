package com.bank.menu;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.bank.bean.Accounts;
import com.bank.bean.Accounts.accountType;
import com.bank.bean.Customer;
import com.bank.daoimpl.AccountsDaoImpl;
import com.bank.daoimpl.AccountsUsersDaoImpl;
import com.bank.daoimpl.CustomerDaoImpl;
import com.bank.exceptions.NotMoneyException;
import com.bank.exceptions.WrongLoginException;
import com.bank.util.AllFiles;
import com.bank.util.ConnFactory;
import com.bank.util.FileStuff;
import com.bank.util.LogThis;

public class Menu {
	
	static Scanner scan = new Scanner(System.in);
	static DecimalFormat df = new DecimalFormat("#.00");
	static boolean continueInput = true;
	static boolean continueInput2 = true;
	static CustomerDaoImpl cdi = new CustomerDaoImpl();
	static AccountsDaoImpl adi = new AccountsDaoImpl();
	static AccountsUsersDaoImpl audi = new AccountsUsersDaoImpl();
	static ConnFactory cf = ConnFactory.getInstance();
	static Connection conn = cf.getConnection();
	
	
	public static void startMenu() {
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
	
	
	public static void register() {
		System.out.println("Please enter your first name.");
		String fname = scan.nextLine();
		System.out.println("Please enter your middle name, or enter space if you want to skip this.");
		String mname = scan.nextLine();
		System.out.println("Please enter your last name.");
		String lname = scan.nextLine();
		System.out.println("Please enter your street address, including any apartment numbers.");
		String address_street = scan.nextLine();
		System.out.println("Please enter your city.");
		String address_city = scan.nextLine();
		System.out.println("Please enter your state.");
		String address_state = scan.nextLine();
		System.out.println("Please enter your zipcode.");
		String address_zipcode = scan.nextLine();
		System.out.println("Please create a username.");
		String ruser = scan.nextLine();
		do {
			try {
				if(cdi.usernameExists(ruser)==true) {
					System.out.println("This username is not available. Please try again.");
					scan.nextLine();
				}else {
					System.out.println("This username is available.");
					continueInput=false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}while(continueInput);
		
		System.out.println("Please create a password.");
		String rpass = scan.nextLine();
		Customer c = new Customer(0,fname, mname, lname, address_street, address_city, address_state, address_zipcode, ruser, rpass, 0);
		try {
			cdi.newCustomer(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Congratulations! You have created a new account! Please log in.");
		startMenu();
	}
	
	public static void login() throws WrongLoginException{
		System.out.println("Please enter your username.");
		String username = scan.nextLine();
		System.out.println("Please enter your password.");
		String password = scan.nextLine();
		String sql = "select * from \"Customer\" where \"Username\"=? and \"Password\"=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			Customer cust = null;
			FileInputStream fis = new FileInputStream("database.properties");
			Properties p = new Properties();
			p.load(fis);
			String auser = (String) p.get("auser");
			String apass = (String) p.get("apass");
			if(rs != null) {
				cust = cdi.getCustomerByUsername(username);
			}
			if(cust!=null) {
				LogThis.LogIt("info", "Customer "+cust.getCustid()+" has logged in.");
				Menu.customerMenu(cust);
			}else if(auser.equals(username)&&apass.equals(password)){
				LogThis.LogIt("info", "Admin has logged in.");
				Menu.adminMenu(username);
			}else {
				throw new WrongLoginException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void customerMenu(Customer cust) {
		do {
			try {
				System.out.println("Hello " + cust.getFname());
				if(cust.getAccount_num()==0) {
					customerMenu1(cust);
				}else {
					System.out.println("Please select which account you would like to access. Press 0 to log out.");
					audi.getAccountsByCustID(cust.getCustid());	
					int acctid = scan.nextInt();
					if(acctid==0) {
						LogThis.LogIt("info","Account "+cust.getCustid()+" logged out.");
						System.out.println("=======================================================");
						System.out.println("Thank you for using 3rd Jeneral Bank. Have a great day!");
						System.out.println("=======================================================");
					}else {
						Accounts acct = null;
						acct = adi.getAccountByID(acctid);
						if(acct==null) {
							System.out.println("This is not a valid account ID.");
							customerMenu(cust);
						}else {
							customerMenu2(cust, acct);
						}
					}
				}
				continueInput=false;
			}catch(InputMismatchException ex) {
				System.out.println("Invalid input. A number is required.");
				scan.nextLine();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}while(continueInput);
	}
	
	public static void customerMenu1(Customer cust) {
		do {
			try {
				System.out.println("Please select from the following account options:");
				System.out.println("\t[1] Reset your password");
				System.out.println("\t[2] Apply for an account");
				System.out.println("\t[0] Logout");
				int option = scan.nextInt();
				switch(option) {
				case 1://reset password
					resetPassword(cust);
					break;
				case 2://Apply for account
					applyForAccount(cust);
					break;
				case 0://Exit
					LogThis.LogIt("info","Account "+cust.getCustid()+" logged out.");
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
				System.out.println("\t[2] Update your address");
				System.out.println("\t[3] Apply for an account");
				System.out.println("\t[4] Check your account balance");
				System.out.println("\t[5] Deposit");
				System.out.println("\t[6] Withdraw");
				System.out.println("\t[7] Initiate a Transfer");
				System.out.println("\t[0] Logout");
				int option = scan.nextInt();
				
				switch(option) {
				case 1://reset password
					resetPassword(cust);
					break;
				case 2://Update address
					updateAddress(cust);
					break;
				case 3://Apply for account
					applyForAccount(cust);
					break;
				case 4://check balance
					System.out.println("Your current balance is " + df.format(acct.getBalance()));
					customerMenu(cust);
					break;
				case 5://deposit
					do {
						try {
							System.out.println("Please enter the amount that you would like to deposit.");
							double amount = scan.nextDouble();
							System.out.println("You want to deposit "+df.format(amount)+" ? (y/n)");
							String dep = scan.next();
							if(dep.equalsIgnoreCase("y")){
								adi.deposit(acct.getAcctid(), amount, cust.getUsername());
								continueInput=false;
							}
							customerMenu(cust);
						}catch(InputMismatchException ex1) {
							throw new NotMoneyException();
						}catch(NotMoneyException ex2) {
							ex2.getMessage();
							scan.next();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}while(continueInput);
					customerMenu(cust);
					break;
				case 6://withdraw
					do {
						try {
							System.out.println("Please enter the amount that you would like to withdraw.");
							double amount = scan.nextDouble();
							System.out.println("You want to withdraw "+df.format(amount)+" ? (y/n)");
							String wd = scan.next();
							if(wd.equalsIgnoreCase("y")){
								adi.withdraw(acct.getAcctid(), amount, cust.getUsername());
								continueInput=false;
							}
							customerMenu(cust);
						}catch(InputMismatchException ex1) {
							throw new NotMoneyException();
						}catch(NotMoneyException ex2) {
							ex2.getMessage();
							scan.next();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}while(continueInput);
					customerMenu(cust);
					break;	
				case 7://transfer
					do {
						try {
							System.out.println("Please select the account to transfer FROM.");
							audi.getAccountsByCustID(cust.getCustid());
							Long acctid1 = scan.nextLong();
							Accounts acct1 = null;
							acct1 = adi.getAccountByID(acctid1);
							if(acct1==null) {
								System.out.println("This is not a valid account ID.");
								customerMenu(cust);
							}
							
							System.out.println("Please select the account to transfer TO.");
							audi.getAccountsByCustID(cust.getCustid());
							Long acctid2 = scan.nextLong();
							Accounts acct2 = null;
							acct2 = adi.getAccountByID(acctid2);
							if(acct2==null) {
								System.out.println("This is not a valid account ID.");
								customerMenu(cust);
							}
							
							System.out.println("Please input the amount you want to transfer.");
							double amount = scan.nextDouble();
							System.out.println("You want to transfer $"+df.format(amount)+"? (y/n)");
							String tf = scan.next();
							if(tf.equalsIgnoreCase("y")) {
								adi.transfer(acctid1, acctid2, amount, cust.getUsername());
								continueInput=false;
							}
							
						}catch(InputMismatchException ex1) {
							throw new NotMoneyException();
						}catch(NotMoneyException ex2) {
							ex2.getMessage();
							customerMenu(cust);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}while(continueInput);
					customerMenu(cust);
					break;
				case 0://Logout
					LogThis.LogIt("info","Account "+cust.getCustid()+" logged out.");
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
	
	
	public static void adminMenu(String username) {
		do {
			try {
				System.out.println("Please choose from the following options.");
				System.out.println("\t [1] Approve or Deny Account Application");
				System.out.println("\t [2] Manage Customer Accounts");
				System.out.println("\t [0] Exit");
				int choice = scan.nextInt();
				switch(choice) {
				case 1://approve/deny acct
					approveOrDeny(username);
					break;
				case 2://cust mgmt
					System.out.println("Please enter the customer's username.");
					String custUser = scan.next();
					Customer cust = cdi.getCustomerByUsername(custUser);
					System.out.println("Please select from the following options:");
					System.out.println("\t [1] Create New Customer Account");
					System.out.println("\t [2] View Account Information");
					System.out.println("\t [3] View Customer Information");
					System.out.println("\t [4] Complete Customer Transaction");
					System.out.println("\t [5] Cancel Customer Account");
					System.out.println("\t [0] Exit");
					int opt = scan.nextInt();
					switch(opt) {
					case 1://create new cust acct
						break;
					case 2://view acct info
						AllFiles.findAcctsByUser(custUser);
						System.out.println("Press any key to return.");
						scan.next();
						adminMenu(username);
						break;
					case 3://view cust info
						System.out.println(cust.toString());
						System.out.println("Press any key to return.");
						scan.next();
						adminMenu(username);
						break;
					case 4://cust transaction
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
							System.out.println("\t [0] Logout");
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
					case 5://cancel cust acct
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
					LogThis.LogIt("info","Admin has logged out.");
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
			cust.setPassword(newpass2);
			try {
				cdi.updateCustomerPassword(cust,newpass1);
				System.out.println("Your password has been updated.");
				customerMenu(cust);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Your entries did not match.");
			resetPassword(cust);
		}
	}
	
	public static void updateAddress(Customer cust) {
		System.out.println("Please enter your street address, including any apartment numbers.");
		String address_street = scan.nextLine();
		System.out.println("Please enter your city.");
		String address_city = scan.nextLine();
		System.out.println("Please enter your state.");
		String address_state = scan.nextLine();
		System.out.println("Please enter your zipcode.");
		String address_zipcode = scan.nextLine();
		try {
			cdi.updateCustomerAddress(cust,address_street,address_city,address_state,address_zipcode);
			System.out.println("Your address has been updated.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void applyForAccount(Customer cust) {
		do {
			try {
				System.out.println("Please select the type of account you would like to open:");
				System.out.println("\t[1] Checking");
				System.out.println("\t[2] Savings");
				int choice = scan.nextInt();
				Accounts a = new Accounts(0,null,false,0.00);
				long acctid=0;
				switch(choice) {
				case 1:
					a.setAccountType(accountType.CHECKING);
					acctid=adi.newAccount(a);
					break;
				case 2:
					a.setAccountType(accountType.SAVINGS);
					acctid=adi.newAccount(a);
					break;
				default: 
					System.out.println("I'm sorry. That was not a valid response.");
					customerMenu(cust);
				} 
				LogThis.LogIt("info", "Account "+a.getAcctid()+ " was applied for by user" +cust.getCustid()+ ".");
				audi.insertRelationship(cust.getCustid(), acctid);
				System.out.println("Would you like to add another user onto this account? (y/n)");
				String joint = scan.nextLine();
				if(joint.equalsIgnoreCase("y")) {
					System.out.println("Please enter their customer ID.");
					do {
						long jointid = scan.nextLong();
						Customer jointcust = null;
						jointcust = cdi.getCustomerByID(jointid);
						if(jointcust==null) {
							System.out.println("That customer ID does not exist. Would you like to add another user onto this account? (y/n)");
							scan.nextLine();
						}else {
							audi.insertRelationship(jointcust.getCustid(), acctid);
							System.out.println("Customer "+jointcust.getCustid()+" has been added to the account. Would you like to add another user to this account? (y/n)");
							String another = scan.next();
							if(another.equalsIgnoreCase("n")) {
								continueInput2=false;
							}else {
								scan.nextLine();
							}
						}
					}while(continueInput2);
				}
				System.out.println("Thank you for applying for a new account. You will be notified when it is approved.");
				customerMenu(cust);
				continueInput=false;
			}catch(InputMismatchException ex) {
				System.out.println("Invalid input. A number is required.");
				scan.nextLine();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}while(continueInput);
	}
	
	//fix the things, cust acct num +1, make updateCustomerAccounts
	public static void approveOrDeny(String username) {
		do {
			try {
				System.out.println("Please select the account ID of the account to review. Press 0 to exit.");
				adi.getInactiveAccounts();
				int review = scan.nextInt();
				if(review==0) {
					adminMenu(username);
				}else {
					Accounts acct = adi.getAccountByID(review);
					if(acct==null) {
						System.out.println("This is not a valid account ID.");
						approveOrDeny(username);
					}
					List<Customer> custList = audi.getCustomersByAcctID(review);
					
					System.out.println("Would you like to approve account " + acct.getAcctid() + "? (y/n)");
					String approve = scan.next();
					if(approve.equalsIgnoreCase("y")) {
						adi.updateAccountStatus(review);
						for(int i = 0; i<custList.size();i++) {
							Customer cust = custList(i);
							cdi.updateCustomerAccounts(cust, 1);
							int num = cust.getAccount_num();
							String sql = "update \"Customers\" set \"Accounts\"=? where \"Customer_ID\"=?";
							PreparedStatement ps = conn.prepareStatement(sql);
							ps.setInt(1, num);
							ps.setLong(2, cust.getCustid());
							ps.executeUpdate();
						}
						System.out.println("You have approved account "+acct.getAcctid()+".");
						LogThis.LogIt("info", "Account"+review+" has been approved by " + username + ".");
						approveOrDeny(username);
					}else {
						System.out.println("You have denied this account.");
						adi.deleteAccount(review);
						LogThis.LogIt("info", "Account "+review+" has been denied by " + username + ".");
						approveOrDeny(username);
					}
				}
				continueInput=false;
			}catch(InputMismatchException | SQLException ex) {
				System.out.println("Invalid input. A number is required.");
				scan.nextLine();
			}
		}while(continueInput);	
		
	}

}
