package com.irar.recards.crafting;

import com.irar.recards.handlers.ItemHandler;
import com.irar.recards.item.ItemCardSelectorTiered;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class CardCraftingRecipe implements IRecipe{

	private ResourceLocation name = new ResourceLocation("card_crafting_recipe");
	
	@Override
	public IRecipe setRegistryName(ResourceLocation name) {
		this.name = name;
		return this;
	}

	@Override
	public ResourceLocation getRegistryName() {
		return this.name;
	}

	@Override
	public Class<IRecipe> getRegistryType() {
		return IRecipe.class;
	}

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		boolean matches = true;
		for(int i = 0; i < inv.getSizeInventory(); i++){
			if(!(inv.getStackInSlot(i).getItem() instanceof ItemCardSelectorTiered)){
				return false;
			}
		}
		int targetTier = ItemCardSelectorTiered.getTier(inv.getStackInSlot(0));
		for(int i = 1; i < inv.getSizeInventory(); i++){
			if(!(ItemCardSelectorTiered.getTier(inv.getStackInSlot(i)) == targetTier)){
				matches = false;
			}
		}
		return matches;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		for(int i = 0; i < inv.getSizeInventory(); i++){
			if(!(inv.getStackInSlot(i).getItem() instanceof ItemCardSelectorTiered)){
				return ItemStack.EMPTY;
			}
		}
		return ItemCardSelectorTiered.getCardSelectorWithTier(ItemCardSelectorTiered.getTier(inv.getStackInSlot(0)) + 1);
	}

	@Override
	public boolean canFit(int width, int height) {
		if(width == 2 && height == 2){
			return true;
		}
		return false;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return new ItemStack(ItemHandler.CardSelectorTiered);
	}

}
