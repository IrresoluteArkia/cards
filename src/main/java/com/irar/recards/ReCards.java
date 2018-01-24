package com.irar.recards;

import com.irar.recards.proxy.IProxy;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = Ref.MODID, version = Ref.VERSION)

public class ReCards {
	@SidedProxy(clientSide=Ref.CLIENT_PROXY, serverSide=Ref.SERVER_PROXY)
	public static IProxy proxy;
	
	@Instance("recards")
    public static ReCards instance = new ReCards();
	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
    	proxy.preInit(event);
    }
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.init(event);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
    	proxy.postInit(event);
    }
}
