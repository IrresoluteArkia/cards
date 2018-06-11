package com.irar.recards.card;

import java.util.ArrayList;
import java.util.Random;

import com.irar.recards.item.ItemCard;

import net.minecraft.item.Item;

public class Cards {
	
	private static Random rand = new Random();
	public static ArrayList<Card> allCards = new ArrayList<Card>();
	public static ArrayList<Item> allCardItems = new ArrayList<Item>();
	public static ArrayList<String> numbers = new ArrayList();
	static {
		numbers.add("ace");
		numbers.add("two");
		numbers.add("three");
		numbers.add("four");
		numbers.add("five");
		numbers.add("six");
		numbers.add("seven");
		numbers.add("eight");
		numbers.add("nine");
		numbers.add("ten");
		numbers.add("jack");
		numbers.add("queen");
		numbers.add("king");
	}
	
	public static final Card clubAce = new Card("club", "ace", new int[]{5, 1}, new int[]{1, 0});
	public static final Card clubTwo = new Card("club", "two", new int[]{5, 8}, new int[]{1, 0});
	public static final Card clubThree = new Card("club", "three", new int[]{5, 13}, new int[]{1, 0});
	public static final Card clubFour = new Card("club", "four", new int[]{5, 14}, new int[]{1, 0});
	public static final Card clubFive = new Card("club", "five", new int[]{5, 24}, new int[]{1, 0});
	public static final Card clubSix = new Card("club", "six", new int[]{5, 4}, new int[]{1, 0});
	public static final Card clubSeven = new Card("club", "seven", new int[]{5, 18}, new int[]{1, 0});
	public static final Card clubEight = new Card("club", "eight", new int[]{5, 26}, new int[]{1, 0});
	public static final Card clubNine = new Card("club", "nine", new int[]{5, 27}, new int[]{1, 0});
	public static final Card clubTen = new Card("club", "ten", new int[]{5}, new int[]{2});
	public static final Card clubJack = new Card("club", "jack", new int[]{5, 2}, new int[]{1, 0});
	public static final Card clubQueen = new Card("club", "queen", new int[]{5, 16}, new int[]{1, 0});
	public static final Card clubKing = new Card("club", "king", new int[]{5, 22}, new int[]{1, 0});
	
	public static final Card diamondAce = new Card("diamond", "ace", new int[]{11, 1}, new int[]{0, 1});
	public static final Card diamondTwo = new Card("diamond", "two", new int[]{11, 8}, new int[]{0, 1});
	public static final Card diamondThree = new Card("diamond", "three", new int[]{11, 13}, new int[]{0, 1});
	public static final Card diamondFour = new Card("diamond", "four", new int[]{11, 14}, new int[]{0, 1});
	public static final Card diamondFive = new Card("diamond", "five", new int[]{11, 24}, new int[]{0, 1});
	public static final Card diamondSix = new Card("diamond", "six", new int[]{11, 4}, new int[]{0, 1});
	public static final Card diamondSeven = new Card("diamond", "seven", new int[]{11, 18}, new int[]{0, 1});
	public static final Card diamondEight = new Card("diamond", "eight", new int[]{11, 26}, new int[]{0, 1});
	public static final Card diamondNine = new Card("diamond", "nine", new int[]{11, 27}, new int[]{0, 1});
	public static final Card diamondTen = new Card("diamond", "ten", new int[]{11}, new int[]{2});
	public static final Card diamondJack = new Card("diamond", "jack", new int[]{11, 2}, new int[]{0, 1});
	public static final Card diamondQueen = new Card("diamond", "queen", new int[]{11, 16}, new int[]{0, 1});
	public static final Card diamondKing = new Card("diamond", "king", new int[]{11, 22}, new int[]{0, 1});
	
	public static final Card spadeAce = new Card("spade", "ace", new int[]{3, 1}, new int[]{1, 0});
	public static final Card spadeTwo = new Card("spade", "two", new int[]{3, 8}, new int[]{1, 0});
	public static final Card spadeThree = new Card("spade", "three", new int[]{3, 13}, new int[]{1, 0});
	public static final Card spadeFour = new Card("spade", "four", new int[]{3, 14}, new int[]{1, 0});
	public static final Card spadeFive = new Card("spade", "five", new int[]{3, 24}, new int[]{1, 0});
	public static final Card spadeSix = new Card("spade", "six", new int[]{3, 4}, new int[]{1, 0});
	public static final Card spadeSeven = new Card("spade", "seven", new int[]{3, 18}, new int[]{1, 0});
	public static final Card spadeEight = new Card("spade", "eight", new int[]{3, 26}, new int[]{1, 0});
	public static final Card spadeNine = new Card("spade", "nine", new int[]{3, 27}, new int[]{1, 0});
	public static final Card spadeTen = new Card("spade", "ten", new int[]{3}, new int[]{2});
	public static final Card spadeJack = new Card("spade", "jack", new int[]{3, 2}, new int[]{1, 0});
	public static final Card spadeQueen = new Card("spade", "queen", new int[]{3, 16}, new int[]{1, 0});
	public static final Card spadeKing = new Card("spade", "king", new int[]{3, 22}, new int[]{1, 0});
	
	public static final Card heartAce = new Card("heart", "ace", new int[]{10, 1}, new int[]{1, 0});
	public static final Card heartTwo = new Card("heart", "two", new int[]{10, 8}, new int[]{1, 0});
	public static final Card heartThree = new Card("heart", "three", new int[]{10, 13}, new int[]{1, 0});
	public static final Card heartFour = new Card("heart", "four", new int[]{10, 14}, new int[]{1, 0});
	public static final Card heartFive = new Card("heart", "five", new int[]{10, 24}, new int[]{1, 0});
	public static final Card heartSix = new Card("heart", "six", new int[]{10, 4}, new int[]{1, 0});
	public static final Card heartSeven = new Card("heart", "seven", new int[]{10, 18}, new int[]{1, 0});
	public static final Card heartEight = new Card("heart", "eight", new int[]{10, 26}, new int[]{1, 0});
	public static final Card heartNine = new Card("heart", "nine", new int[]{10, 27}, new int[]{1, 0});
	public static final Card heartTen = new Card("heart", "ten", new int[]{10}, new int[]{2});
	public static final Card heartJack = new Card("heart", "jack", new int[]{10, 2}, new int[]{1, 0});
	public static final Card heartQueen = new Card("heart", "queen", new int[]{10, 16}, new int[]{1, 0});
	public static final Card heartKing = new Card("heart", "king", new int[]{10, 22}, new int[]{1, 0});
	
	static{
		addCard(clubAce);
		addCard(clubTwo);
		addCard(clubThree);
		addCard(clubFour);
		addCard(clubFive);
		addCard(clubSix);
		addCard(clubSeven);
		addCard(clubEight);
		addCard(clubNine);
		addCard(clubTen);
		addCard(clubJack);
		addCard(clubQueen);
		addCard(clubKing);
		addCard(diamondAce);
		addCard(diamondTwo);
		addCard(diamondThree);
		addCard(diamondFour);
		addCard(diamondFive);
		addCard(diamondSix);
		addCard(diamondSeven);
		addCard(diamondEight);
		addCard(diamondNine);
		addCard(diamondTen);
		addCard(diamondJack);
		addCard(diamondQueen);
		addCard(diamondKing);
		addCard(spadeAce);
		addCard(spadeTwo);
		addCard(spadeThree);
		addCard(spadeFour);
		addCard(spadeFive);
		addCard(spadeSix);
		addCard(spadeSeven);
		addCard(spadeEight);
		addCard(spadeNine);
		addCard(spadeTen);
		addCard(spadeJack);
		addCard(spadeQueen);
		addCard(spadeKing);
		addCard(heartAce);
		addCard(heartTwo);
		addCard(heartThree);
		addCard(heartFour);
		addCard(heartFive);
		addCard(heartSix);
		addCard(heartSeven);
		addCard(heartEight);
		addCard(heartNine);
		addCard(heartTen);
		addCard(heartJack);
		addCard(heartQueen);
		addCard(heartKing);
	}
	
	public static void addCard(Card card){
		allCards.add(card);
	}
	
	public static Card getCardFromId(String id){
		Card card = null;
		for(int i = 0; i < allCards.size(); i++){
			if(allCards.get(i).id.equals(id)){
				card = allCards.get(i);
			}
		}
//		System.out.println("returning a null card; THIS WILL PROBABLY CAUSE A PROBLEM!!!!");
		return card;
	}
	
	public static Card getRandomCard(){
		return allCards.get(rand.nextInt(allCards.size()));
	}
	
}
