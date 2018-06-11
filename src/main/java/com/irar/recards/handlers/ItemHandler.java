package com.irar.recards.handlers;


import java.util.ArrayList;
import java.util.Random;

import com.irar.recards.card.Card;
import com.irar.recards.card.Cards;
import com.irar.recards.item.ItemCard;
import com.irar.recards.item.ItemCardSelector;
import com.irar.recards.item.ItemCardSelectorTiered;
import com.irar.recards.item.ItemIrresoluteCard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;


public class ItemHandler {
	public static ArrayList<Item> allItems = new ArrayList<Item>();
	public static Item CardSelector;
	public static Item CardIrresolute;
	public static Item CardSelectorTiered;
/*	public static Item CardClubAce;
	public static Item CardClubTwo;
	public static Item CardClubThree;
	public static Item CardClubFour;
	public static Item CardClubFive;
	public static Item CardClubSix;
	public static Item CardClubSeven;
	public static Item CardClubEight;
	public static Item CardClubNine;
	public static Item CardClubTen;
	public static Item CardClubJack;
	public static Item CardClubQueen;
	public static Item CardClubKing;
	public static Item CardDiamondAce;
	public static Item CardDiamondTwo;
	public static Item CardDiamondThree;
	public static Item CardDiamondFour;
	public static Item CardDiamondFive;
	public static Item CardDiamondSix;
	public static Item CardDiamondSeven;
	public static Item CardDiamondEight;
	public static Item CardDiamondNine;
	public static Item CardDiamondTen;
	public static Item CardDiamondJack;
	public static Item CardDiamondQueen;
	public static Item CardDiamondKing;
	public static Item CardSpadeAce;
	public static Item CardSpadeTwo;
	public static Item CardSpadeThree;
	public static Item CardSpadeFour;
	public static Item CardSpadeFive;
	public static Item CardSpadeSix;
	public static Item CardSpadeSeven;
	public static Item CardSpadeEight;
	public static Item CardSpadeNine;
	public static Item CardSpadeTen;
	public static Item CardSpadeJack;
	public static Item CardSpadeQueen;
	public static Item CardSpadeKing;
	public static Item CardHeartAce;
	public static Item CardHeartTwo;
	public static Item CardHeartThree;
	public static Item CardHeartFour;
	public static Item CardHeartFive;
	public static Item CardHeartSix;
	public static Item CardHeartSeven;
	public static Item CardHeartEight;
	public static Item CardHeartNine;
	public static Item CardHeartTen;
	public static Item CardHeartJack;
	public static Item CardHeartQueen;
	public static Item CardHeartKing;*/
	
	public static void init(){
		CardSelector = new ItemCardSelector("card_selector");
		CardIrresolute = new ItemIrresoluteCard("card_irresolute");
		CardSelectorTiered = new ItemCardSelectorTiered("card_selector_tiered");
		for(Card card : Cards.allCards) {
			Item CurrentCard = new ItemCard("card_" + card.suit + "_" + card.type, card);
			Cards.allCardItems.add(CurrentCard);
		}
/*		CardClubAce = new ItemCard("card_club_ace", Cards.clubAce);
		CardClubTwo = new ItemCard("card_club_two", Cards.clubTwo);
		CardClubThree = new ItemCard("card_club_three", Cards.clubThree);
		CardClubFour = new ItemCard("card_club_four", Cards.clubFour);
		CardClubFive = new ItemCard("card_club_five", Cards.clubFive);
		CardClubSix = new ItemCard("card_club_six", Cards.clubSix);
		CardClubSeven = new ItemCard("card_club_seven", Cards.clubSeven);
		CardClubEight = new ItemCard("card_club_eight", Cards.clubEight);
		CardClubNine = new ItemCard("card_club_nine", Cards.clubNine);
		CardClubTen = new ItemCard("card_club_ten", Cards.clubTen);
		CardClubJack = new ItemCard("card_club_jack", Cards.clubJack);
		CardClubQueen = new ItemCard("card_club_queen", Cards.clubQueen);
		CardClubKing = new ItemCard("card_club_king", Cards.clubKing);
		CardDiamondAce = new ItemCard("card_diamond_ace", Cards.diamondAce);
		CardDiamondTwo = new ItemCard("card_diamond_two", Cards.diamondTwo);
		CardDiamondThree = new ItemCard("card_diamond_three", Cards.diamondThree);
		CardDiamondFour = new ItemCard("card_diamond_four", Cards.diamondFour);
		CardDiamondFive = new ItemCard("card_diamond_five", Cards.diamondFive);
		CardDiamondSix = new ItemCard("card_diamond_six", Cards.diamondSix);
		CardDiamondSeven = new ItemCard("card_diamond_seven", Cards.diamondSeven);
		CardDiamondEight = new ItemCard("card_diamond_eight", Cards.diamondEight);
		CardDiamondNine = new ItemCard("card_diamond_nine", Cards.diamondNine);
		CardDiamondTen = new ItemCard("card_diamond_ten", Cards.diamondTen);
		CardDiamondJack = new ItemCard("card_diamond_jack", Cards.diamondJack);
		CardDiamondQueen = new ItemCard("card_diamond_queen", Cards.diamondQueen);
		CardDiamondKing = new ItemCard("card_diamond_king", Cards.diamondKing);
		CardSpadeAce = new ItemCard("card_spade_ace", Cards.spadeAce);
		CardSpadeTwo = new ItemCard("card_spade_two", Cards.spadeTwo);
		CardSpadeThree = new ItemCard("card_spade_three", Cards.spadeThree);
		CardSpadeFour = new ItemCard("card_spade_four", Cards.spadeFour);
		CardSpadeFive = new ItemCard("card_spade_five", Cards.spadeFive);
		CardSpadeSix = new ItemCard("card_spade_six", Cards.spadeSix);
		CardSpadeSeven = new ItemCard("card_spade_seven", Cards.spadeSeven);
		CardSpadeEight = new ItemCard("card_spade_eight", Cards.spadeEight);
		CardSpadeNine = new ItemCard("card_spade_nine", Cards.spadeNine);
		CardSpadeTen = new ItemCard("card_spade_ten", Cards.spadeTen);
		CardSpadeJack = new ItemCard("card_spade_jack", Cards.spadeJack);
		CardSpadeQueen = new ItemCard("card_spade_queen", Cards.spadeQueen);
		CardSpadeKing = new ItemCard("card_spade_king", Cards.spadeKing);
		CardHeartAce = new ItemCard("card_heart_ace", Cards.heartAce);
		CardHeartTwo = new ItemCard("card_heart_two", Cards.heartTwo);
		CardHeartThree = new ItemCard("card_heart_three", Cards.heartThree);
		CardHeartFour = new ItemCard("card_heart_four", Cards.heartFour);
		CardHeartFive = new ItemCard("card_heart_five", Cards.heartFive);
		CardHeartSix = new ItemCard("card_heart_six", Cards.heartSix);
		CardHeartSeven = new ItemCard("card_heart_seven", Cards.heartSeven);
		CardHeartEight = new ItemCard("card_heart_eight", Cards.heartEight);
		CardHeartNine = new ItemCard("card_heart_nine", Cards.heartNine);
		CardHeartTen = new ItemCard("card_heart_ten", Cards.heartTen);
		CardHeartJack = new ItemCard("card_heart_jack", Cards.heartJack);
		CardHeartQueen = new ItemCard("card_heart_queen", Cards.heartQueen);
		CardHeartKing = new ItemCard("card_heart_king", Cards.heartKing);*/
		
		allItems.add(CardSelector);
		allItems.add(CardIrresolute);
		allItems.add(CardSelectorTiered);
/*		Cards.allCardItems.add(CardClubAce);
		Cards.allCardItems.add(CardClubTwo);
		Cards.allCardItems.add(CardClubThree);
		Cards.allCardItems.add(CardClubFour);
		Cards.allCardItems.add(CardClubFive);
		Cards.allCardItems.add(CardClubSix);
		Cards.allCardItems.add(CardClubSeven);
		Cards.allCardItems.add(CardClubEight);
		Cards.allCardItems.add(CardClubNine);
		Cards.allCardItems.add(CardClubTen);
		Cards.allCardItems.add(CardClubJack);
		Cards.allCardItems.add(CardClubQueen);
		Cards.allCardItems.add(CardClubKing);
		Cards.allCardItems.add(CardDiamondAce);
		Cards.allCardItems.add(CardDiamondTwo);
		Cards.allCardItems.add(CardDiamondThree);
		Cards.allCardItems.add(CardDiamondFour);
		Cards.allCardItems.add(CardDiamondFive);
		Cards.allCardItems.add(CardDiamondSix);
		Cards.allCardItems.add(CardDiamondSeven);
		Cards.allCardItems.add(CardDiamondEight);
		Cards.allCardItems.add(CardDiamondNine);
		Cards.allCardItems.add(CardDiamondTen);
		Cards.allCardItems.add(CardDiamondJack);
		Cards.allCardItems.add(CardDiamondQueen);
		Cards.allCardItems.add(CardDiamondKing);
		Cards.allCardItems.add(CardSpadeAce);
		Cards.allCardItems.add(CardSpadeTwo);
		Cards.allCardItems.add(CardSpadeThree);
		Cards.allCardItems.add(CardSpadeFour);
		Cards.allCardItems.add(CardSpadeFive);
		Cards.allCardItems.add(CardSpadeSix);
		Cards.allCardItems.add(CardSpadeSeven);
		Cards.allCardItems.add(CardSpadeEight);
		Cards.allCardItems.add(CardSpadeNine);
		Cards.allCardItems.add(CardSpadeTen);
		Cards.allCardItems.add(CardSpadeJack);
		Cards.allCardItems.add(CardSpadeQueen);
		Cards.allCardItems.add(CardSpadeKing);
		Cards.allCardItems.add(CardHeartAce);
		Cards.allCardItems.add(CardHeartTwo);
		Cards.allCardItems.add(CardHeartThree);
		Cards.allCardItems.add(CardHeartFour);
		Cards.allCardItems.add(CardHeartFive);
		Cards.allCardItems.add(CardHeartSix);
		Cards.allCardItems.add(CardHeartSeven);
		Cards.allCardItems.add(CardHeartEight);
		Cards.allCardItems.add(CardHeartNine);
		Cards.allCardItems.add(CardHeartTen);
		Cards.allCardItems.add(CardHeartJack);
		Cards.allCardItems.add(CardHeartQueen);
		Cards.allCardItems.add(CardHeartKing);*/
		
		for(int i = 0; i < Cards.allCardItems.size(); i++){
			allItems.add(Cards.allCardItems.get(i));
		}
		
	}
	
	public static void register(){
		 for(int i = 0; i < allItems.size(); i++){
			 ForgeRegistries.ITEMS.register(allItems.get(i));
		 }
	}
	
	public static void registerRenders(){
		 for(int i = 0; i < allItems.size(); i++){
			 registerRender(allItems.get(i));
		 }
	}
	
	public static void registerRender(Item item){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		if(item instanceof ItemCard) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 1, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
	}
}
