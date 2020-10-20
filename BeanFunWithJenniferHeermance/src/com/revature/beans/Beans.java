package com.revature.beans;

public class Beans {
	
	static String shape = "Bean shape";
	private String name;
	private String flavor;
	private String texture;
	
	public Beans() {
		
	}
	
	public Beans(String name, String flavor, String texture) {
		this.name=name;
		this.flavor = flavor;
		this.texture = texture;
	}


	public static String getShape() {
		return shape;
	}

	public static void setBeanShape(String shape) {
		Beans.shape = shape;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlavor() {
		return flavor;
	}

	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	public String getTexture() {
		return texture;
	}

	public void setTexture(String texture) {
		this.texture = texture;
	}

	@Override
	public String toString() {
		return "Beans [name=" + name + ", flavor=" + flavor + ", texture=" + texture + ", shape=" + shape + "]";
	}
	
	

}
