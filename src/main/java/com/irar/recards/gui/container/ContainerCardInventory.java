package com.irar.recards.gui.container;

import javax.annotation.Nullable;

import com.irar.recards.gui.inventory.CardInventory;
import com.irar.recards.gui.slot.SlotUnusable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCardInventory extends Container{

	private CardInventory ci;
	
	public ContainerCardInventory(InventoryPlayer playerInv, CardInventory tileEntityOreGen){
		this.ci = tileEntityOreGen;
		 // Tile Entity, Slot 0-8, Slot IDs 0-8
	    for (int y = 0; y < 4; ++y) {
	        for (int x = 0; x < 13; ++x) {
	            this.addSlotToContainer(new SlotUnusable(tileEntityOreGen, x + y * 13, 8 + x * 18, 17 + y * 18));
	        }
	    }

	    // Player Inventory, Slot 9-35, Slot IDs 9-35
	    for (int y = 0; y < 3; ++y) {
	        for (int x = 0; x < 9; ++x) {
	            this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 40 + x * 18, 102 + y * 18));
	        }
	    }

	    // Player Inventory, Slot 0-8, Slot IDs 36-44
	    for (int x = 0; x < 9; ++x) {
	        this.addSlotToContainer(new Slot(playerInv, x, 40 + x * 18, 160));
	    }
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.ci.isUsableByPlayer(playerIn);
	}

	@Nullable
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack())
        {
            return ItemStack.EMPTY;
        }

        return itemstack;
	}
	
}
