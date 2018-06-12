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
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;


public class ItemHandler {
	public static ArrayList<Item> allItems = new ArrayList<Item>();
	public static Item CardSelector;
	public static Item CardIrresolute;
	public static Item CardSelectorTiered;
	
	public static void init(){
		CardSelector = new ItemCardSelector("card_selector");
		CardIrresolute = new ItemIrresoluteCard("card_irresolute");
		CardSelectorTiered = new ItemCardSelectorTiered("card_selector_tiered");
		Cards.init();
		for(Card card : Cards.allCards) {
			Item CurrentCard = new ItemCard("card_" + card.suit.id + "_" + card.type.id, card);
			Cards.allCardItems.add(CurrentCard);
		}
		
		allItems.add(CardSelector);
		allItems.add(CardIrresolute);
		allItems.add(CardSelectorTiered);
		
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
		if(item instanceof ItemCard) {
			ItemCard card = (ItemCard) item;
			ModelBakery.registerItemVariants(item, card.getCard().type.texture/*, card.getCard().type.texture*/);
			ModelLoader.setCustomMeshDefinition(item, card);//setCustomModelResourceLocation(item, 0, new ModelResourceLocation(card.getCard().type.texture, "inventory"));
//			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(card.getCard().type.texture, "inventory"));
//			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 1, new ModelResourceLocation(card.getCard().type.texture, "inventory"));
//			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(card, card);
		}else {
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
	}
	
	public static void registerColors() {
		for(Item item : allItems) {
			if(item instanceof ItemCard) {
				ItemCard card = (ItemCard) item;
				Minecraft.getMinecraft().getItemColors().registerItemColorHandler(card, card);
			}
		}
	}
}
