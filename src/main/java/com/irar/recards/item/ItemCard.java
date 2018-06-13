package com.irar.recards.item;

import java.util.List;

import javax.annotation.Nullable;

import com.irar.recards.card.Card;
import com.irar.recards.card.Cards;
import com.irar.recards.handlers.CreativeTabsHandler;
import com.irar.recards.handlers.ItemHandler;
import com.irar.recards.proxy.CommonProxy;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ItemCard  extends ItemGeneric implements ItemMeshDefinition, IItemColor{
	
	private Card card;
	
	public ItemCard(String name, Card card){
		super(name);
		this.card = card;
		this.setHasSubtypes(true);
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		Card card = ((ItemCard) stack.getItem()).getCard();
		if(card != null) {
			String suit = card.suit.name;
			String type = card.type.name;
			return "The " + type + " of " + suit;
		}
		return "";
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn){
		ItemStack stack = playerIn.getHeldItem(handIn);
		if(!worldIn.isRemote){
			if(!CommonProxy.saveData.isPlayerInList(playerIn)){
				Card selectedCard = ((ItemCard) stack.getItem()).card;
				CommonProxy.saveData.addPlayer(playerIn, selectedCard);
				int tier = ItemCard.getTierFromItemStack(stack);
				for(int i = 0; i < tier - 1; i++) {
					CommonProxy.saveData.upgradeCard(selectedCard, playerIn);
				}
/*				TextComponentString messagep1 = new TextComponentString("[ResoluteArkia]");
				TextComponentString messagep2 = new TextComponentString(" ...");
				messagep1.setStyle(new Style().setColor(TextFormatting.DARK_RED));
				messagep2.setStyle(new Style().setBold(true).setColor(TextFormatting.AQUA));
				messagep1.appendSibling(messagep2);
				playerIn.sendMessage(messagep1);*/
				stack.shrink(1);
			}else{
				boolean consumed = false;
				Card selectedCard = ((ItemCard) stack.getItem()).card;
				int currentTier = CommonProxy.saveData.getTierForCard(selectedCard, playerIn) + 1;
				int tier = ItemCard.getTierFromItemStack(stack);
				while(currentTier < tier) {
					CommonProxy.saveData.upgradeCard(selectedCard, playerIn);
					currentTier++;
					consumed = true;
				}
				if(consumed) {
					stack.shrink(1);
				}
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn){
		
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey("TIER")){
			int tier = stack.getTagCompound().getInteger("TIER");
			if(stack.getMetadata() == 0) {
				tooltip.add(tier == 1 ? "Unlock The Power Of This Card" : "Upgrade Card To Level " + tier);
			}else if(stack.getMetadata() == 1) {
				tooltip.add("Level " + tier);
			}
			for(int i = 0; i < card.potionEffects.size(); i++) {
				PotionEffect effect = card.potionEffects.get(i);
				tooltip.add(I18n.translateToLocal(effect.getEffectName()) + " " + Math.min(effect.getAmplifier() + tier, card.maxAmps[i] + 1));
			}
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	public Card getCard(){
		return this.card;
	}
	
	public static int getTierFromItemStack(ItemStack stack) {
		if(stack.hasTagCompound()) {
			NBTTagCompound tag = stack.getTagCompound();
			if(tag.hasKey("TIER")) {
				return tag.getInteger("TIER");
			}
		}
		return 0;
	}

	public ItemStack getItemStackWithTier(int tier) {
		ItemStack itemstack = new ItemStack(this, 1);
		itemstack.setTagCompound(new NBTTagCompound());
		itemstack.getTagCompound().setInteger("TIER", tier);
		return itemstack;
	}
	
	public static ItemStack getItemStackWithTierCardAndMetadata(Card card, int tier, int meta) {
		ItemCard target = null;
		for(Item item : ItemHandler.allItems) {
			if(item instanceof ItemCard) {
				ItemCard carditem = (ItemCard) item;
				if(carditem.getCard().equals(card)) {
					target = carditem;
				}
			}
		}
		
		if(target != null) {
			ItemStack stack = target.getItemStackWithTier(tier);
			stack.setItemDamage(meta);
			return stack;
		}
		return ItemStack.EMPTY;
	}

	@Override
	public ModelResourceLocation getModelLocation(ItemStack stack) {
		ModelResourceLocation loc = new ModelResourceLocation(((ItemCard) stack.getItem()).card.type.texture, "inventory");
		return loc;
	}

	@Override
	public int getColorFromItemstack(ItemStack stack, int tintIndex) {
        return tintIndex > 0 ? -1 : getColor(stack);
	}

	public static int getColor(ItemStack stack) {
		return ((ItemCard) stack.getItem()).card.suit.color;
	}}
