package com.irar.recards.gui.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotUnusable extends Slot{

	public SlotUnusable(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack){
		return false;
	}
}
