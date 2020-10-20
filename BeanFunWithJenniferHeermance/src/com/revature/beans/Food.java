package com.revature.beans;

public class Food {
	
	private String name;
	private String meal;
	private boolean hasBeans;
	private Beans beanType;
	
	public Food() {
		
	}
	
	public Food(String name, String meal, boolean hasBeans, Beans beanType) {
		this.name=name;
		this.meal = meal;
		this.hasBeans = hasBeans;
		this.beanType = beanType;
		if(this.hasBeans == true) {
			System.out.println("This meal has beans.");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	public boolean isHasBeans() {
		return hasBeans;
	}

	public void setHasBeans(boolean hasBeans) {
		this.hasBeans = hasBeans;
	}

	public Beans getBeanType() {
		return beanType;
	}

	public void setBeanType(Beans beanType) {
		this.beanType = beanType;
	}

	@Override
	public String toString() {
		return "Food [name=" + name + ", meal=" + meal + ", hasBeans=" + hasBeans + ", beanType=" + beanType + "]";
	}
	
	



}
