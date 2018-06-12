package com.irar.recards.card;

import java.util.ArrayList;
import java.util.Random;

import com.irar.recards.item.ItemCard;

import net.minecraft.item.Item;

public class Cards {
	
	private static Random rand = new Random();
	public static ArrayList<Card> allCards = new ArrayList<Card>();
	public static ArrayList<Item> allCardItems = new ArrayList<Item>();
	public static ArrayList<ArrayList<Card>> cardsBySuit = new ArrayList<>();
	public static int[][][][] PEMAP = new int[Suit.values().length][CardType.values().length][2][];
	
	public static void init() {
		PEMAP[0][0] = new int[][]{{5, 1}, {1, 0}};
		PEMAP[0][1] = new int[][]{{5, 8}, {1, 0}};
		PEMAP[0][2] = new int[][]{{5, 13}, {1, 0}};
		PEMAP[0][3] = new int[][]{{5, 14}, {1, 0}};
		PEMAP[0][4] = new int[][]{{5, 24}, {1, 0}};
		PEMAP[0][5] = new int[][]{{5, 4}, {1, 0}};
		PEMAP[0][6] = new int[][]{{5, 18}, {1, 0}};
		PEMAP[0][7] = new int[][]{{5, 26}, {1, 0}};
		PEMAP[0][8] = new int[][]{{5, 27}, {1, 0}};
		PEMAP[0][9] = new int[][]{{5}, {2}};
		PEMAP[0][10] = new int[][]{{5, 2}, {1, 0}};
		PEMAP[0][11] = new int[][]{{5, 16}, {1, 0}};
		PEMAP[0][12] = new int[][]{{5, 22}, {1, 0}};
		
		PEMAP[1][0] = new int[][]{{11, 1}, {0, 1}};
		PEMAP[1][1] = new int[][]{{11, 8}, {0, 1}};
		PEMAP[1][2] = new int[][]{{11, 13}, {0, 1}};
		PEMAP[1][3] = new int[][]{{11, 14}, {0, 1}};
		PEMAP[1][4] = new int[][]{{11, 24}, {0, 1}};
		PEMAP[1][5] = new int[][]{{11, 4}, {0, 1}};
		PEMAP[1][6] = new int[][]{{11, 18}, {0, 1}};
		PEMAP[1][7] = new int[][]{{11, 26}, {0, 1}};
		PEMAP[1][8] = new int[][]{{11, 27}, {0, 1}};
		PEMAP[1][9] = new int[][]{{11}, {2}};
		PEMAP[1][10] = new int[][]{{11, 2}, {0, 1}};
		PEMAP[1][11] = new int[][]{{11, 16}, {0, 1}};
		PEMAP[1][12] = new int[][]{{11, 22}, {0, 1}};
		
		PEMAP[2][0] = new int[][]{{3, 1}, {1, 0}};
		PEMAP[2][1] = new int[][]{{3, 8}, {1, 0}};
		PEMAP[2][2] = new int[][]{{3, 13}, {1, 0}};
		PEMAP[2][3] = new int[][]{{3, 14}, {1, 0}};
		PEMAP[2][4] = new int[][]{{3, 24}, {1, 0}};
		PEMAP[2][5] = new int[][]{{3, 4}, {1, 0}};
		PEMAP[2][6] = new int[][]{{3, 18}, {1, 0}};
		PEMAP[2][7] = new int[][]{{3, 26}, {1, 0}};
		PEMAP[2][8] = new int[][]{{3, 27}, {1, 0}};
		PEMAP[2][9] = new int[][]{{3}, {2}};
		PEMAP[2][10] = new int[][]{{3, 2}, {1, 0}};
		PEMAP[2][11] = new int[][]{{3, 16}, {1, 0}};
		PEMAP[2][12] = new int[][]{{3, 22}, {1, 0}};
		
		PEMAP[3][0] = new int[][]{{10, 1}, {1, 0}};
		PEMAP[3][1] = new int[][]{{10, 8}, {1, 0}};
		PEMAP[3][2] = new int[][]{{10, 13}, {1, 0}};
		PEMAP[3][3] = new int[][]{{10, 14}, {1, 0}};
		PEMAP[3][4] = new int[][]{{10, 24}, {1, 0}};
		PEMAP[3][5] = new int[][]{{10, 4}, {1, 0}};
		PEMAP[3][6] = new int[][]{{10, 18}, {1, 0}};
		PEMAP[3][7] = new int[][]{{10, 26}, {1, 0}};
		PEMAP[3][8] = new int[][]{{10, 27}, {1, 0}};
		PEMAP[3][9] = new int[][]{{10}, {2}};
		PEMAP[3][10] = new int[][]{{10, 2}, {1, 0}};
		PEMAP[3][11] = new int[][]{{10, 16}, {1, 0}};
		PEMAP[3][12] = new int[][]{{10, 22}, {1, 0}};
		
		for(int i = 0; i < PEMAP.length; i++) {
			ArrayList<Card> cardsInSuit = new ArrayList<>();
			for(int j = 0; j < PEMAP[i].length; j++) {
				Card card = new Card(Suit.values()[i], CardType.values()[j], PEMAP[i][j][0], PEMAP[i][j][1]);
				cardsInSuit.add(card);
				addCard(card);
			}
			cardsBySuit.add(cardsInSuit);
		}
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
