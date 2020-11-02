package com.revature.driver;

import com.revature.menu.Menu;
import com.revature.util.FileStuff;

public class Driver {
	
	static {FileStuff.readHalloweenSurvey();}
	public static void main(String[] args){
		Menu.surveyMenu();
	}

}
