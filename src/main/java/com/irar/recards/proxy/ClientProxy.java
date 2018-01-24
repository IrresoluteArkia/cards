package com.irar.recards.proxy;

import com.irar.recards.gui.container.ContainerMainInv;
import com.irar.recards.handlers.ItemHandler;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy{
	public static ContainerMainInv playerInv = null;
	public void init(FMLInitializationEvent event){
		ItemHandler.registerRenders();
	}
}
