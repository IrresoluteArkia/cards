package com.irar.recards.card;

import com.irar.recards.Ref;

import net.minecraft.util.ResourceLocation;

public enum CardType {
	ACE("ace", "Ace"),
	TWO("two", "Two"),
	THREE("three", "Three"),
	FOUR("four", "Four"),
	FIVE("five", "Five"),
	SIX("six", "Six"),
	SEVEN("seven", "Seven"),
	EIGHT("eight", "Eight"),
	NINE("nine", "Nine"),
	TEN("ten", "Ten"),
	JACK("jack", "Jack"),
	QUEEN("queen", "Queen"),
	KING("king", "King"),
	FE("eleven", "Forgotten Eleven"),
	UKD("unknown", "Unknown Door");
	
	public final ResourceLocation texture;
	public final String name;
	public final String id;
	
	CardType(String id, String name){
		this.id = id;
		this.name = name;
		this.texture = new ResourceLocation(Ref.MODID, id);
	}
}
