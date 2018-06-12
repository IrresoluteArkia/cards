package com.irar.recards.gui.inventory;

import java.util.ArrayList;

import com.irar.recards.card.Card;
import com.irar.recards.card.Cards;
import com.irar.recards.handlers.ItemHandler;
import com.irar.recards.item.ItemCardSelectorTiered;
import com.irar.recards.network.CardMessage;
import com.irar.recards.network.ReCardsPacketHandler;
import com.irar.recards.proxy.CommonProxy;
import com.irar.recards.item.ItemCard;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CardInventory implements IInventory{
    private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(13 * 4, ItemStack.EMPTY);
    private String customName;
    private int tier;
    private EntityPlayer player;
	public static boolean isClear = false;
    
    public CardInventory(int tier, EntityPlayer player){
    	isClear = false;
    	this.player = player;
    	this.tier = tier;
    	this.setCustomName(tier == 1 ? "Select A Card To Gain The Powers Of:" : "Select A Card To Upgrade:");
    	if(!player.world.isRemote){
    		int k = 0;
	    	for(int i = 0; i < 4; i++){
	    		ArrayList<Card> cards = Cards.cardsBySuit.get(i);
		    	for(int j = 0; j < 13; j++){
		    		Card currentCard = cards.get(j);
		    		ItemStack currentCardItem = ItemCard.getItemStackWithTierCardAndMetadata(currentCard, tier, 0);
		    		if(CommonProxy.saveData.hasCard(currentCard, player)){
			    		if(CommonProxy.saveData.hasCardInTier(currentCard, tier - 1, player)){
			    			this.setInventorySlotContents(k, currentCardItem);
			    		}
		    		}else if(tier == 1){
		    			this.setInventorySlotContents(k, currentCardItem);
		    		}
		    		k++;
		    	}
	    	}
    	}
    }

    public String getCustomName() {
        return this.customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }
   

	@Override
	public String getName() {
	    return this.hasCustomName() ? this.customName : "card_inventory";
	}
	
	@Override
	public boolean hasCustomName() {
        return this.customName != null && !this.customName.isEmpty();
	}

	@Override
	public int getSizeInventory() {
		return 13 * 4;
	}

	public static int getSizeInventory(int i) {
		return 13 * 4;
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
		sendMessage(itemstack);
        if (!itemstack.isEmpty())
        {
            this.markDirty();
        }

        return ItemStack.EMPTY;

	}
	
	@SideOnly(Side.CLIENT)
	private void sendMessage(ItemStack itemstack) {
		if(itemstack.getItem() instanceof ItemCard && !isClear){
			ReCardsPacketHandler.INSTANCE.sendToServer(new CardMessage(Cards.allCards.indexOf(((ItemCard) itemstack.getItem()).getCard())));
		}
		this.clear();
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
		this.isClear  = true;
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
