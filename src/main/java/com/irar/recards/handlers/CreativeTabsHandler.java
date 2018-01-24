package com.irar.recards.handlers;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabsHandler {
	public static CreativeTabs CARDS = new CreativeTabs("cards"){

		public ItemStack getTabIconItem() {
			return new ItemStack(ItemHandler.CardSelector);
		}
		
	};
}
