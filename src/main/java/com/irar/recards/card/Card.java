package com.irar.recards.card;

import java.util.ArrayList;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class Card {
	
	public String id;
	public Suit suit;
	public CardType type;
	public ArrayList<PotionEffect> potionEffects = new ArrayList<PotionEffect>();
	public int[] maxAmps;
	public int maxLvl;
	
	public Card(Suit suit, CardType type, int[] potionIds, int[] potionAmps, int[] maxAmps, int[] maxLvl){
		this.suit = suit;
		this.type = type;
		this.id = suit + "_" + type;
		this.maxAmps = maxAmps;
		this.maxLvl = maxLvl == null ? 255 : maxLvl[0];
		if(potionIds != null && potionAmps != null) {
			for(int i = 0; i < potionIds.length; i++){
				potionEffects.add(new PotionEffect(Potion.getPotionById(potionIds[i]), 10000, potionAmps[i]));
			}
		}
	}
	
	public static String[] getSuitAndTypeFromId(String id){
		return id.split("_");
	}
	
	public static String getIdFromSuitAndType(String[] sAndT){
		return sAndT[0] + "_" + sAndT[1];
	}
	
}
