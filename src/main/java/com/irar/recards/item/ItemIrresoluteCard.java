package com.irar.recards.item;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.irar.recards.card.Card;
import com.irar.recards.card.Cards;
import com.irar.recards.proxy.CommonProxy;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemIrresoluteCard extends ItemGeneric{

	public ItemIrresoluteCard(String name) {
		super(name);
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn){
		ItemStack stack = playerIn.getHeldItem(handIn).copy();
		if(!worldIn.isRemote){
			if(!CommonProxy.saveData.isPlayerInList(playerIn)){
				TextComponentString messagep1 = new TextComponentString("[IrresoluteArkia]");
				TextComponentString messagep2 = new TextComponentString(" ...");
				messagep1.setStyle(new Style().setColor(TextFormatting.DARK_PURPLE));
				messagep2.setStyle(new Style().setBold(true).setColor(TextFormatting.AQUA));
				messagep1.appendSibling(messagep2);
				playerIn.sendMessage(messagep1);
			}else{
					TextComponentString messagep1 = new TextComponentString("[IrresoluteArkia]");
					TextComponentString messagep2 = new TextComponentString(" There, I have freed you from the power of all those resolute card abominations!");
					messagep1.setStyle(new Style().setColor(TextFormatting.DARK_PURPLE));
					messagep2.setStyle(new Style().setBold(true).setColor(TextFormatting.AQUA));
					messagep1.appendSibling(messagep2);
					playerIn.sendMessage(messagep1);
            		ArrayList<Object[]> playerCards = CommonProxy.saveData.getCardsWithTierForPlayer(playerIn);
            		for(int i = 0; i < playerCards.size(); i++){
            			Object[] cardAndTier = playerCards.get(i);
            			Card playerCard = ((Card) cardAndTier[0]);
            			int tier = (int) cardAndTier[1];
            			ItemStack cardItem = ItemCard.getItemStackWithTierCardAndMetadata(playerCard, tier, 1);
            			worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, cardItem));
            		}
					CommonProxy.saveData.removePlayer(playerIn);
					playerIn.curePotionEffects(new ItemStack(Items.MILK_BUCKET));
					if(stack.getCount() == 1){
						stack = ItemStack.EMPTY;
					}else{
						stack.setCount(stack.getCount() - 1);
					}
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn){
		
		tooltip.add("Warning: Will Convert Your Resolute Powers Back Into Card Form");
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

}
