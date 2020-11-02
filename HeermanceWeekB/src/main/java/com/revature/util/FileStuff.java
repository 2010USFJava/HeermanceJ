package com.revature.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Halloween;

public class FileStuff {
	
	public static final String halloweenSurvey = "halloweenSurvey.txt";
	
	public static void writeHalloweenSurvey(List<Halloween> hList) {
		try {
			ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(halloweenSurvey));
			objectOut.writeObject(hList);
			objectOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void readHalloweenSurvey() {
		try {
			ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(halloweenSurvey));
			Survey.halloweenSurvey = (ArrayList<Halloween>)objectIn.readObject();
			objectIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
