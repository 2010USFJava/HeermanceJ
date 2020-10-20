package com.revature.driver;

import com.revature.beans.Beans;
import com.revature.beans.Food;
public class Driver {
	
	public static void main(String[] args) {
		
		Beans jellybean = new Beans("Jellybean", "fruity", "chewy");
		Beans pintoBean = new Beans("Pinto Bean", "savory", "soft");
		System.out.println(jellybean);
		System.out.println(pintoBean);
		Food enchiladas = new Food("Enchiladas", "Dinner", true, pintoBean);
		System.out.println(enchiladas);
		
	}

}
