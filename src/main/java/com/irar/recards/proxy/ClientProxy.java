package com.irar.recards.proxy;

import com.irar.recards.gui.container.ContainerMainInv;
import com.irar.recards.handlers.ItemHandler;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{
	public static ContainerMainInv playerInv = null;
	@Override
	public void preInit(FMLPreInitializationEvent event){
		super.preInit(event);
		ItemHandler.registerRenders();
	}
	
	@Override
	public void init(FMLInitializationEvent event){
		super.init(event);
		ItemHandler.registerColors();
	}
}
