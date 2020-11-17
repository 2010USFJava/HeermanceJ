package com.bank.exceptions;

public class NegativeAmountException extends RuntimeException{

	private static final long serialVersionUID = -3464480330793568289L;

	public NegativeAmountException() {
		System.out.println("You cannot input a negative amount.");
	}
	
}
