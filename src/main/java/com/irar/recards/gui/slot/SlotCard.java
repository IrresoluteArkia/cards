package com.irar.recards.gui.slot;

import com.irar.recards.item.ItemCard;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotCard extends Slot{

	public SlotCard(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack){
		if(stack.getItem() instanceof ItemCard) {
			return true;
		}
		return false;
	}
}
