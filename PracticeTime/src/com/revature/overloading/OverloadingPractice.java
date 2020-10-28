package com.revature.overloading;

/*
 * Overloading
 * Creating methods with the same name but different method signatures within the same class
 * (i.e. number of and types of parameters and different return types)
 * 
 * Overloading Order of Operations
 * - Exact Match
 * - Conversion - primitives/objects casting themselves to other types (ex. myDouble.intValue())
 * 				- primitive to primitive OR Object to Object
 * - Boxing - see Wrapperz
 * - Varargs - arguments wrapped into an array
 */

public class OverloadingPractice {
	
	static int intExample(int a, int b) {
		System.out.println("This was exact match");		
		return a + b;
		
	}
	
	static int intExample(int a, double b) {
		System.out.println("This was conversion.");
		return (int) (a+b);
	}
	
	static int intExample(int a, Integer b) {
		System.out.println("This was Wrapping");
		return a+b;
	}
	
	static int intExample(int ... z) {
		System.out.println("This was varArgs");
		int c = 0;
		for(int x:z) {
			c+=x;
		}
		return c;
	}
	
	static double x = 13.7;
	
	static Integer y = 54;	
	
	public static void main(String[] args) {
		
		//exact match
		System.out.println(intExample(3,7));
		
		//Conversion
		System.out.println(intExample(3,x));
		
		//Boxing
		System.out.println(intExample(3,y));
		
		//Varargs
		System.out.println(intExample(3,4,5,6,7));
		
	}

}
