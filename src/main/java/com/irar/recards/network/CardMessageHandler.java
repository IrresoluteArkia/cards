package com.irar.recards.network;

import com.irar.recards.card.Cards;
import com.irar.recards.item.ItemCard;
import com.irar.recards.proxy.CommonProxy;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

// The params of the IMessageHandler are <REQ, REPLY>
// This means that the first param is the packet you are receiving, and the second is the packet you are returning.
// The returned packet can be used as a "response" from a sent packet.
public class CardMessageHandler implements IMessageHandler<CardMessage, IMessage> {
	// Do note that the default constructor is required, but implicitly defined in this case
	
	@Override public IMessage onMessage(CardMessage message, MessageContext ctx) {
		// This is the player the packet was sent to the server from
		EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
		// The value that was sent
		int amount = message.toSend;
		// Execute the action on the main server thread by adding it as a scheduled task
		serverPlayer.getServerWorld().addScheduledTask(() -> {
			CommonProxy.saveData.upgradeCard(Cards.allCards.get(amount), serverPlayer);
		});
		// No response packet
		return null;
	}
}