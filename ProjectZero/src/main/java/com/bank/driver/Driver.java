package com.bank.driver;

import com.bank.menu.Menu;
import com.bank.util.FileStuff;

public class Driver {
	
	static {FileStuff.readCustomerMap();}
	static {FileStuff.readCustomerFile();}
	static {FileStuff.readAccountsList();}
	static {FileStuff.readEmployeeMap();}
	static {FileStuff.readAdminMap();}
	
	public static void main(String[] args) {
//		new Employee("emp1","empPass1");
//		new Employee("emp2","empPass2");
//		new Admin("admin1","adminPass1");
		
		Menu.startMenu();
		
			
	}

}
