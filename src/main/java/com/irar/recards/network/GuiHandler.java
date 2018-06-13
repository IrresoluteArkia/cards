package com.irar.recards.network;

import com.irar.recards.gui.client.GuiCardInventory;
import com.irar.recards.gui.client.GuiMainInv;
import com.irar.recards.gui.container.ContainerCardInventory;
import com.irar.recards.gui.container.ContainerMainInv;
import com.irar.recards.gui.inventory.CardInventory;
import com.irar.recards.gui.inventory.MainInv;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	public static final int CARD_SELECTOR = 0;
	public static final int CARD_VIEWER = 0;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		System.out.println("Made it to server gui");
		if(ID == this.CARD_SELECTOR)
			return new ContainerCardInventory(player.inventory, new CardInventory(x, player, true));
		if(ID == this.CARD_VIEWER)
			return new ContainerMainInv(player.inventory, new MainInv(player));
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		System.out.println("Made it to client gui");
		if(ID == this.CARD_SELECTOR)
			return new GuiCardInventory(player.inventory, new CardInventory(x, player, false));
		if(ID == this.CARD_VIEWER)
			return new GuiMainInv(player.inventory, new MainInv(player));

		return null;
	}

}
