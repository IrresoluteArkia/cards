package com.irar.recards.proxy;

import java.util.ArrayList;
import java.util.HashMap;

import com.irar.recards.ReCards;
import com.irar.recards.card.Card;
import com.irar.recards.handlers.CraftingHandler;
import com.irar.recards.handlers.ItemHandler;
import com.irar.recards.network.CardMessage;
import com.irar.recards.network.CardMessageHandler;
import com.irar.recards.network.GuiHandler;
import com.irar.recards.network.ReCardsPacketHandler;
import com.irar.recards.network.ViewerMessage;
import com.irar.recards.network.ViewerMessageHandler;
import com.irar.recards.world.SaveDataHandler;

import net.minecraft.client.main.Main;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class CommonProxy implements IProxy{
	
	public static SaveDataHandler saveData;
	public static IForgeRegistry<IRecipe> recipeRegistry;
	public static HashMap<EntityPlayerMP, Integer> tickMap = new HashMap<EntityPlayerMP, Integer>();

	@Override
	public void preInit(FMLPreInitializationEvent event) {
        final MainEventHandler handler = new MainEventHandler();
        MinecraftForge.EVENT_BUS.register((Object)handler);
        FMLCommonHandler.instance().bus().register((Object)handler);
		ItemHandler.init();
		ItemHandler.register();

		NetworkRegistry.INSTANCE.registerGuiHandler(ReCards.instance, new GuiHandler());
		
		ReCardsPacketHandler.INSTANCE.registerMessage(CardMessageHandler.class, CardMessage.class, 0, Side.SERVER);
		ReCardsPacketHandler.INSTANCE.registerMessage(ViewerMessageHandler.class, ViewerMessage.class, 1, Side.CLIENT);
	}

	@Override
	public void init(FMLInitializationEvent event) {
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		CraftingHandler.init();
	}
	
	public static class MainEventHandler{
		
		@SubscribeEvent
		public void Register(RegistryEvent.Register<IRecipe> event){
			recipeRegistry = event.getRegistry();
		}
		
		@SubscribeEvent
		public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event){
			if(!event.player.world.isRemote){
				saveData = SaveDataHandler.get(event.player.world);
				if(saveData.isPlayerInList(event.player)){
//					Card playerCard = saveData.getCardForPlayer(event.player);
//					if(!(playerCard == null)){
						TextComponentString messagep1 = new TextComponentString("[ResoluteArkia]");
						TextComponentString messagep2 = new TextComponentString(" Welcome back " + event.player.getName() + ", the power of your cards has been waiting for you.");
						messagep1.setStyle(new Style().setColor(TextFormatting.DARK_RED));
						messagep2.setStyle(new Style().setBold(true).setColor(TextFormatting.AQUA));
						messagep1.appendSibling(messagep2);
						event.player.sendMessage(messagep1);
						
//					}else{
//						System.out.println("Error: player's card was null for some reason?");
//					}
				}else{
					TextComponentString messagep1 = new TextComponentString("[ResoluteArkia]");
					TextComponentString messagep2 = new TextComponentString(" Hello " + event.player.getName() + ", welcome to my world; here is a card to get you started.");
					messagep1.setStyle(new Style().setColor(TextFormatting.DARK_RED));
					messagep2.setStyle(new Style().setBold(true).setColor(TextFormatting.AQUA));
					messagep1.appendSibling(messagep2);
					event.player.sendMessage(messagep1);
					event.player.world.spawnEntity(new EntityItem(event.player.world, event.player.posX, event.player.posY, event.player.posZ, new ItemStack(ItemHandler.CardSelector)));
				}
			}
		}
        @SubscribeEvent
        public void entTick(final LivingEvent.LivingUpdateEvent event) {
            if (event.getEntity().world.isRemote) {
                return;
            }
            if(event.getEntity() instanceof EntityPlayerMP){
            	EntityPlayerMP entityPlayer = (EntityPlayerMP)event.getEntity();
            	int tick = 0;
            	if(tickMap.containsKey(entityPlayer)) {
            		tick = tickMap.get(entityPlayer);
            		tick++;
            	}
            	tickMap.put(entityPlayer, tick);
//            	System.out.println(entityPlayer.getUniqueID().toString());
            	if(saveData.isPlayerInList(entityPlayer) && tick % 600 == 0){
            		float health = entityPlayer.getHealth();
            		ArrayList<Object[]> playerCards = saveData.getCardsWithTierForPlayer(entityPlayer);
            		for(int i = 0; i < playerCards.size(); i++){
            			Object[] cardAndTier = playerCards.get(i);
            			Card playerCard = ((Card) cardAndTier[0]);
            			if(playerCard == null) {
            				continue;
            			}
                		for(int j = 0; j < playerCard.potionEffects.size(); j++){
                			int amp = (int) cardAndTier[1];
                			PotionEffect effect = playerCard.potionEffects.get(j);
                			entityPlayer.addPotionEffect(new PotionEffect(effect.getPotion(), (60 * 20) - 1, Math.min(effect.getAmplifier() + amp - 1, playerCard.maxAmps[j]), false, false));
                		}
            		}
            		float healAmount = health - entityPlayer.getHealth();
            		entityPlayer.heal(healAmount);
            	}
            }
        }
        @SubscribeEvent
        public void playerSpawn(PlayerEvent.PlayerRespawnEvent event) {
            if (event.player.world.isRemote) {
                return;
            }
        	EntityPlayerMP entityPlayer = (EntityPlayerMP)event.player;
        	if(saveData.isPlayerInList(entityPlayer)){
        		ArrayList<Object[]> playerCards = saveData.getCardsWithTierForPlayer(entityPlayer);
        		for(int i = 0; i < playerCards.size(); i++){
        			Object[] cardAndTier = playerCards.get(i);
            		for(int j = 0; j < ((Card) cardAndTier[0]).potionEffects.size(); j++){
            			Card playerCard = ((Card) cardAndTier[0]);
            			int amp = (int) cardAndTier[1];
            			PotionEffect effect = playerCard.potionEffects.get(j);
            			entityPlayer.addPotionEffect(new PotionEffect(effect.getPotion(), (60 * 20) - 1, Math.min(effect.getAmplifier() + amp - 1, playerCard.maxAmps[j]), false, false));
            		}
        		}
        	}
        }

	}

}
