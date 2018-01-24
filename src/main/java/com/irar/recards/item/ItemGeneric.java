package com.irar.recards.item;

import com.irar.recards.handlers.CreativeTabsHandler;

import net.minecraft.item.Item;

public class ItemGeneric extends Item{
	public ItemGeneric(String name){
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabsHandler.CARDS);
	}

}
