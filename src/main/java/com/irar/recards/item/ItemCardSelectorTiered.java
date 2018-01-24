package com.irar.recards.item;

import java.util.List;

import javax.annotation.Nullable;

import com.irar.recards.ReCards;
import com.irar.recards.card.Card;
import com.irar.recards.card.Cards;
import com.irar.recards.handlers.ItemHandler;
import com.irar.recards.network.GuiHandler;
import com.irar.recards.proxy.CommonProxy;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCardSelectorTiered extends ItemGeneric{

	public ItemCardSelectorTiered(String name) {
		super(name);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn){
		ItemStack stack = playerIn.getHeldItem(handIn).copy();
		if(!worldIn.isRemote){
			if(CommonProxy.saveData.isPlayerInList(playerIn)){
//				System.out.println("Opening gui for tier: " + getTier(playerIn.getHeldItem(handIn)) + "; from item: " + playerIn.getHeldItem(handIn).getDisplayName());
				playerIn.openGui(ReCards.instance, GuiHandler.CARD_SELECTOR, worldIn, getTier(stack), 0, 0);
				if(stack.getCount() == 1){
					stack = ItemStack.EMPTY;
				}else{
					stack.setCount(stack.getCount() - 1);
				}
			}else{
				TextComponentString messagep1 = new TextComponentString("[ResoluteArkia]");
				TextComponentString messagep2 = new TextComponentString(" ...");
				messagep1.setStyle(new Style().setColor(TextFormatting.DARK_RED));
				messagep2.setStyle(new Style().setBold(true).setColor(TextFormatting.AQUA));
				messagep1.appendSibling(messagep2);
				playerIn.sendMessage(messagep1);
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}
	
	public static ItemStack getCardSelectorWithTier(int tier){
		ItemStack itemstack = new ItemStack(ItemHandler.CardSelectorTiered, 1);
		itemstack.setTagCompound(new NBTTagCompound());
		itemstack.getTagCompound().setInteger("TIER", tier);
		System.out.println("Adding tier to itemstack");
		return itemstack;
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn){
		
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey("TIER")){
			int tier = stack.getTagCompound().getInteger("TIER");
			tooltip.add("Tier " + tier);
		}else{
			tooltip.add("Invalid Tier: please get this item through proper methods e.g. the crafting recipe");
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	public static int getTier(ItemStack stack) {
		int tier = -1;
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey("TIER")){
			tier = stack.getTagCompound().getInteger("TIER");
		}
		return tier;
	}
	
}
