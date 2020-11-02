package com.revature.menu;

import java.util.Scanner;

import com.revature.beans.Halloween;
import com.revature.util.LogThis;
import com.revature.util.Survey;

public class Menu {
	
	static Scanner scan = new Scanner(System.in);
	
	public static void surveyMenu() {
		System.out.println("Thank you for choosing to participate in this survey!\n Please enter the following information:");
		System.out.println("Your name");
		String name = scan.nextLine();
		System.out.println("Did you wear a costume? (y/n)");
		String cost = scan.nextLine();
		String costume = null;
		if(cost.equalsIgnoreCase("y")) {
			System.out.println("What was your costume?");
			costume = scan.nextLine();
		}else if(cost.equalsIgnoreCase("n")){
			costume = null;
		}else {
			System.out.println("Input not recognized. Returning you to the beginning.");
			surveyMenu();
		}
		System.out.println("What is your favorite candy?");
		String candy = scan.nextLine();
		System.out.println("Did you go trick-or-treating this year? (y/n)");
		String tot = scan.nextLine();
		boolean trickOrTreat = false;
		switch (tot.toLowerCase()) {
			case "y":
				trickOrTreat = true;
				break;
			case "n":
				trickOrTreat = false;
				break;
			default:
				System.out.println("Input not recognized. Returning you to the beginning.");
				surveyMenu();
		}
		Halloween a = new Halloween(name, costume, candy, trickOrTreat);
		LogThis.LogIt("info", "\n" + a.getName() + " submitted their survey entry.");
		System.out.println(Survey.halloweenSurvey.toString());
		System.out.println("Thank you for participating in our Spooky Survey. Goodbye!");
		
	}

}
