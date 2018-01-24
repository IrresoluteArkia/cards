package com.irar.recards.gui.inventory;

import java.util.ArrayList;
import java.util.List;

import com.irar.recards.card.Card;
import com.irar.recards.card.Cards;
import com.irar.recards.handlers.ItemHandler;
import com.irar.recards.item.ItemCardSelectorTiered;
import com.irar.recards.network.CardMessage;
import com.irar.recards.network.ReCardsPacketHandler;
import com.irar.recards.network.ViewerMessage;
import com.irar.recards.proxy.ClientProxy;
import com.irar.recards.proxy.CommonProxy;
import com.irar.recards.item.ItemCard;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class MainInv implements IInventory{
    private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);
    private String customName;
    private EntityPlayer player;
    
    public MainInv(EntityPlayer player){
    	this.player = player;
    	this.setCustomName("All Cards Gained");
    	if(!player.world.isRemote) {
    		this.updateSlots();
    		ReCardsPacketHandler.INSTANCE.sendTo(new ViewerMessage(this.inventory), (EntityPlayerMP) player);
    	}
    }
    
    public void updateSlots() {
    	ArrayList<ItemStack> cardStacks = new ArrayList<ItemStack>();
		ArrayList<Object[]> cardsWithTiers = CommonProxy.saveData.getCardsWithTierForPlayer(player);
		for(Object[] cardWithTier : cardsWithTiers) {
			Card card = (Card) cardWithTier[0];
			int tier = (int) cardWithTier[1];
			cardStacks.add(ItemCard.getItemStackWithTierCardAndMetadata(card, tier, 1));
		}
    	inventory = NonNullList.<ItemStack>withSize(cardStacks.size(), ItemStack.EMPTY);
    	for(int i = 0; i < cardStacks.size(); i++) {
    		inventory.set(i, cardStacks.get(i));
    	}
    }

    public void updateSlots(List<ItemStack> stacks) {
    	inventory = NonNullList.<ItemStack>withSize(stacks.size(), ItemStack.EMPTY);
    	for(int i = 0; i < stacks.size(); i++) {
    		inventory.set(i, stacks.get(i));
    	}
    	ClientProxy.playerInv.updateSlots();
    }

    public String getCustomName() {
        return this.customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }
   

	@Override
	public String getName() {
	    return this.hasCustomName() ? this.customName : "card_main_inventory";
	}
	
	@Override
	public boolean hasCustomName() {
        return this.customName != null && !this.customName.isEmpty();
	}

	@Override
	public int getSizeInventory() {
		return inventory.size();
	}

	@Override
    public boolean isEmpty()
    {
        for (ItemStack itemstack : this.inventory)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
    }

	@Override
	public ItemStack getStackInSlot(int index) {
	    if (index < 0 || index >= this.getSizeInventory())
	        return null;
	    return this.inventory.get(index);
	}
	
	@Override
	public ItemStack decrStackSize(int index, int count) {
        ItemStack itemstack = ItemStackHelper.getAndSplit(this.inventory, index, count);

        if (!itemstack.isEmpty())
        {
            this.markDirty();
        }

        return itemstack;

	}
	
	@Override
	public ItemStack removeStackFromSlot(int index) {
	    ItemStack stack = this.getStackInSlot(index);
    	System.out.println(index);
	    this.setInventorySlotContents(index, null);
	    return stack;
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
        this.inventory.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }

        this.markDirty();
	}

	public void setInventorySlotContents(int index, ItemStack stack, boolean unimportant) {
        this.inventory.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }

        this.markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return true;
	}
	
	@Override
	public void openInventory(EntityPlayer player) {
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return false;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		for (int i = 0; i < this.getSizeInventory(); i++)
	        this.setInventorySlotContents(i, ItemStack.EMPTY);
	}

	@Override
	public ITextComponent getDisplayName() {
        return (ITextComponent)(this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName(), new Object[0]));
	}

	@Override
	public void markDirty() {
		
	}

/*	@Deprecated
	public static CardInventory getInventoryFor(ItemStack stack) {
		if(stack.getItem() instanceof ItemCardSelectorTiered){
			System.out.println("Tier of inventory: " + ItemCardSelectorTiered.getTier(stack));
			return new CardInventory(ItemCardSelectorTiered.getTier(stack));
		}
		return new CardInventory(-1);
	}*/

}
