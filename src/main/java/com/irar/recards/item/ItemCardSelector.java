package com.irar.recards.item;

import java.util.ArrayList;

import com.irar.recards.card.Card;
import com.irar.recards.card.Cards;
import com.irar.recards.handlers.CreativeTabsHandler;
import com.irar.recards.proxy.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemCardSelector extends ItemGeneric{
	public ItemCardSelector(String name){
		super(name);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn){
		if(!worldIn.isRemote){
			if(CommonProxy.saveData.isPlayerInList(playerIn)){
				TextComponentString messagep1 = new TextComponentString("[ResoluteArkia]");
				TextComponentString messagep2 = new TextComponentString(" ...");
				messagep1.setStyle(new Style().setColor(TextFormatting.DARK_RED));
				messagep2.setStyle(new Style().setBold(true).setColor(TextFormatting.AQUA));
				messagep1.appendSibling(messagep2);
				playerIn.sendMessage(messagep1);
			}else{
					Card selectedCard = Cards.getRandomCard();
					TextComponentString messagep1 = new TextComponentString("[ResoluteArkia]");
					TextComponentString messagep2 = new TextComponentString(" Be glad, " + playerIn.getName() + "! The " + selectedCard.type.name + " of " + selectedCard.suit.name + " has seen fit to grant you its great power!");
					messagep1.setStyle(new Style().setColor(TextFormatting.DARK_RED));
					messagep2.setStyle(new Style().setBold(true).setColor(TextFormatting.AQUA));
					messagep1.appendSibling(messagep2);
					playerIn.sendMessage(messagep1);
					CommonProxy.saveData.addPlayer(playerIn, selectedCard);
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, ItemStack.EMPTY);
	}
	
}
