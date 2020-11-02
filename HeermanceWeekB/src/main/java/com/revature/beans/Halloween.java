package com.revature.beans;

import java.io.Serializable;

import com.revature.util.FileStuff;
import com.revature.util.LogThis;
import com.revature.util.Survey;

public class Halloween implements Serializable{

	private static final long serialVersionUID = -2340499516259010072L;
	
	private String name;
	private String costume;
	private String candy;
	private boolean trickOrTreat;
	public Halloween() {
		super();
		Survey.halloweenSurvey.add(this);
		FileStuff.writeHalloweenSurvey(Survey.halloweenSurvey);
		LogThis.LogIt("info", "\nA new survey entry has been recorded.");
	}
	
	public Halloween(String name, String costume, String candy, boolean trickOrTreat) {
		super();
		this.name = name;
		this.costume = costume;
		this.candy = candy;
		this.trickOrTreat = trickOrTreat;
		Survey.halloweenSurvey.add(this);
		FileStuff.writeHalloweenSurvey(Survey.halloweenSurvey);
		LogThis.LogIt("info", "\nA new survey entry has been recorded.");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCostume() {
		return costume;
	}

	public void setCostume(String costume) {
		this.costume = costume;
	}

	public String getCandy() {
		return candy;
	}

	public void setCandy(String candy) {
		this.candy = candy;
	}

	public boolean isTrickOrTreat() {
		return trickOrTreat;
	}

	public void setTrickOrTreat(boolean trickOrTreat) {
		this.trickOrTreat = trickOrTreat;
	}

	@Override
	public String toString() {
		return "\nHalloween [Name = " + name + ", Costume = " + costume + ", Favorite Candy = " + candy + ", Trick or Treat = "
				+ trickOrTreat + "]";
	}
	
	

}
