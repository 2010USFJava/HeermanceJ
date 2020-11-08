package com.bank.driver;

import com.bank.menu.Menu;
import com.bank.util.FileStuff;

public class Driver {
	
	static {FileStuff.readCustomerMap();}
	static {FileStuff.readCustomerFile();}
	static {FileStuff.readAccountsList();}
//	static {FileStuff.readEmployeeMap();}
//	static {FileStuff.readAdminMap();}
	
	public static void main(String[] args) {
		Menu.startMenu();
		
	}

}
