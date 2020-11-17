package com.bank.exceptions;

public class OverdraftException extends RuntimeException{

	private static final long serialVersionUID = -8790097700447633688L;

	public OverdraftException() {
		System.out.println("You can not move a higher amount than your available balance.");
	}
}
