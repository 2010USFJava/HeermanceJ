package com.revature.chaining;

public class ChainGang {
	
	private String title;
	private int releaseYear;
	private String bandName;
	private String genre;
	private double length;
	private String songWriter;
	
	public ChainGang() {
		this("Back on the Chain Gang");
		
	}
	
	public ChainGang(String title) {
		this(title, "The Pretenders");
		
	}
	
	public ChainGang(String title, String bandName) {
		this(title, bandName, "Rock");
		
	}
	
	public ChainGang(String title, String bandName, String genre) {
		this(title, bandName, genre, 1982);
	
	}

	public ChainGang(String title, String bandName, String genre, int releaseYear) {
		this(title, bandName, genre, releaseYear, 3.85);
	
	}
	
	public ChainGang(String title, String bandName, String genre, int releaseYear, double length) {
		this(title, bandName, genre, releaseYear, length, "Chrissy Hynde");
	
	}

	public ChainGang(String title, String bandName, String genre, int releaseYear, double length, String songWriter) {
		this.title=title;
		this.bandName=bandName;
		this.genre=genre;
		this.releaseYear=releaseYear;
		this.length=length;
		this.songWriter=songWriter;
	}
	
	public static void main(String[] args) {
		ChainGang song= new ChainGang();
		System.out.println(song);
	}

	@Override
	public String toString() {
		return "ChainGang [title=" + title + ", releaseYear=" + releaseYear + ", bandName=" + bandName + ", genre="
				+ genre + ", length=" + length + ", songWriter=" + songWriter + "]";
	}
	
	

	
}
