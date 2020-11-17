package com.bank.exceptions;

import com.bank.menu.Menu;

public class WrongLoginException extends RuntimeException{

	private static final long serialVersionUID = -8294422472573654047L;

	public WrongLoginException() {
		System.out.println("Invalid username/password combination. Please try again.");
		Menu.startMenu();
	}
	
}
