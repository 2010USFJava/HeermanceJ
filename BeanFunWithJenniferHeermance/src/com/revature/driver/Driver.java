package com.revature.driver;

import com.revature.beans.Beans;

public class Driver {
	
	public static void main(String[] args) {
		
		Beans jellybean = new Beans("Jellybean", "fruity", "chewy");
		Beans kidneyBean = new Beans("Kidney Bean", "savory", "soft");
		System.out.println(jellybean);
		System.out.println(kidneyBean);
		
	}

}
