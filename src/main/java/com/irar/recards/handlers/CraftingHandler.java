package com.irar.recards.handlers;

import com.irar.recards.crafting.CardCraftingRecipe;
import com.irar.recards.item.ItemCardSelectorTiered;
import com.irar.recards.proxy.CommonProxy;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CraftingHandler {
	
	public static void init(){
		GameRegistry.addShapedRecipe(new ResourceLocation("recards:recipe1"), new ResourceLocation("recipes"), ItemCardSelectorTiered.getCardSelectorWithTier(1), new Object[]{"PPP", "PEP", "PPP", 'E', Items.EMERALD, 'P', Items.PAPER});
		GameRegistry.addShapedRecipe(new ResourceLocation("recards:recipe2"), new ResourceLocation("recipes"), new ItemStack(ItemHandler.CardIrresolute), new Object[]{"PPP", "PEP", "PPP", 'E', Items.DIAMOND, 'P', Items.PAPER});
		CommonProxy.recipeRegistry.register(new CardCraftingRecipe());
	}
	
}
