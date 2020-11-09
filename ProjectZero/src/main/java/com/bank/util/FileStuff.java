package com.bank.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

import com.bank.users.Accounts;
import com.bank.users.Customer;

public class FileStuff {
	//customer file
	public static final String customerFile = "customers.txt";
	//write
	public static void writeCustomerFile(List<Customer> cList) {
		try {
			ObjectOutputStream cListOut = new ObjectOutputStream(new FileOutputStream(customerFile));
			cListOut.writeObject(cList);
			cListOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	//read
	@SuppressWarnings("unchecked")
	public static void readCustomerFile() {
		try {
			ObjectInputStream cListIn = new ObjectInputStream(new FileInputStream(customerFile));
			AllFiles.cList = (List<Customer>)cListIn.readObject();
			cListIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	//customer login map
	public static final String customerMap = "customerMap.txt";
	//write
	public static void writeCustomerMap(Map<String, String> cmap) {
		try {
			ObjectOutputStream cMapOut = new ObjectOutputStream(new FileOutputStream(customerMap));
			cMapOut.writeObject(cmap);
			cMapOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	//read
	@SuppressWarnings("unchecked")
	public static void readCustomerMap() {
		try {
			ObjectInputStream cMapIn = new ObjectInputStream(new FileInputStream(customerMap));
			AllFiles.cmap = (Map<String,String>)cMapIn.readObject();
			cMapIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	//employee login map
	public static final String employeeMap = "employeeMap.txt";
	
	public static void writeEmployeeFile(Map<String,String> emap) {
		try {
			ObjectOutputStream emapOut = new ObjectOutputStream(new FileOutputStream(employeeMap));
			emapOut.writeObject(emap);
			emapOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	//read
	@SuppressWarnings("unchecked")
	public static void readEmployeeMap() {
		try {
			ObjectInputStream emapIn = new ObjectInputStream(new FileInputStream(employeeMap));
			AllFiles.emap = (Map<String,String>)emapIn.readObject();
			emapIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	
	//admin login map
	public static final String adminMap = "adminMap.txt";
	
	public static void writeAdminFile(Map<String,String> amap) {
		try {
			ObjectOutputStream amapOut = new ObjectOutputStream(new FileOutputStream(adminMap));
			amapOut.writeObject(amap);
			amapOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	//read
	@SuppressWarnings("unchecked")
	public static void readAdminMap() {
		try {
			ObjectInputStream amapIn = new ObjectInputStream(new FileInputStream(adminMap));
			AllFiles.amap = (Map<String,String>)amapIn.readObject();
			amapIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	//Accounts file
	public static final String accountsList = "accountsList.txt";
	//write
	public static void writeAccountsList(List<Accounts> accList) {
		try {
			ObjectOutputStream accListOut = new ObjectOutputStream(new FileOutputStream(accountsList));
			accListOut.writeObject(accList);
			accListOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	//read
	@SuppressWarnings("unchecked" )
	public static void readAccountsList() {
		try {
			ObjectInputStream accListIn = new ObjectInputStream(new FileInputStream(accountsList));
			AllFiles.accList = (List<Accounts>)accListIn.readObject();
			accListIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

}
