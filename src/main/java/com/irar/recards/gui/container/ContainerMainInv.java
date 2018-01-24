package com.irar.recards.gui.container;

import javax.annotation.Nullable;

import com.irar.recards.gui.inventory.CardInventory;
import com.irar.recards.gui.inventory.MainInv;
import com.irar.recards.gui.slot.SlotCard;
import com.irar.recards.gui.slot.SlotUnusable;
import com.irar.recards.proxy.ClientProxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerMainInv extends Container{

	public MainInv ci;
	public InventoryPlayer pi;
	
	public ContainerMainInv(InventoryPlayer playerInv, MainInv cardinv){
		this.ci = cardinv;
		this.pi = playerInv;
		this.updateSlots();
		if(playerInv.player.world.isRemote) {
			ClientProxy.playerInv = this;
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
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (fromSlot > 36)
            {
                if (!this.mergeItemStack(itemstack1, 0, 36, true))
                {
                    return ItemStack.EMPTY;
                }
            }/*
            else if (!this.mergeItemStack(itemstack1, 36, 36 + this.ci.getSizeInventory() - 1, false))
            {
                return ItemStack.EMPTY;
            }*/

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
	}

	public void updateSlots() {
		this.inventorySlots.clear();
		this.inventoryItemStacks.clear();
	    // Player Inventory, Slot 9-35, Slot IDs 9-35
	    for (int y = 0; y < 3; ++y) {
	        for (int x = 0; x < 9; ++x) {
	            this.addSlotToContainer(new Slot(pi, x + y * 9 + 9, 40 + x * 18, 102 + y * 18));
	        }
	    }

	    // Player Inventory, Slot 0-8, Slot IDs 36-44
	    for (int x = 0; x < 9; ++x) {
	        this.addSlotToContainer(new Slot(pi, x, 40 + x * 18, 160));
	    }
	    
		 // Tile Entity, Slot 0-8, Slot IDs 0-8
	    int invIndex = 0;
	    int invSize = ci.getSizeInventory();
	    for (int y = 0; y < 5; ++y) {
	    	boolean shouldContinue = true;
	        for (int x = 0; x < 13; ++x) {
	        	if(invIndex < invSize) {
	        		this.addSlotToContainer(new SlotCard(ci, x + y * 13, 8 + x * 18, 17 + y * 18));
	        		invIndex++;
	        	}else {
	        		shouldContinue = false;
	        		break;
	        	}
	        }
	        if(!shouldContinue) {
	        	break;
	        }
	    }
	}
	
}
