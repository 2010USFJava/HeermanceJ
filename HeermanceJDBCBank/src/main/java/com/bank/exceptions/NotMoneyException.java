package com.bank.exceptions;

public class NotMoneyException extends RuntimeException{

	private static final long serialVersionUID = 7657468478245206781L;

	public NotMoneyException() {
		System.out.println("Invalid input. The input should be a dollar amount of format X.XX");
	}
	
}
