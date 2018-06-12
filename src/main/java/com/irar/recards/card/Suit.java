package com.irar.recards.card;

public enum Suit {
	CLUB("club", "Clubs", 0),
	DIAMOND("diamond", "Diamonds", 255 * 256 * 256),
	SPADE("spade", "Spades", 0),
	HEART("heart", "Hearts", 255 * 256 * 256);
	
	
	public final int color;
	public final String name;
	public final String id;
	
	Suit(String id, String name, int color){
		this.id = id;
		this.name = name;
		this.color = color;
	}
	
}
