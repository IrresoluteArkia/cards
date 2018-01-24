package com.irar.recards.network;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ViewerMessage implements IMessage{

	public NBTTagCompound toSend;
	public List<ItemStack> stacks;
	
	public ViewerMessage(){}
	
	public ViewerMessage(NonNullList<ItemStack> stacks) {
		System.out.println("Made it here");
		this.stacks = stacks;
	}

	@Override public void toBytes(ByteBuf buf) {
		PacketBuffer buffer = new PacketBuffer(buf);
		toSend = new NBTTagCompound();
		int i = 0;
		for(ItemStack stack : stacks) {
			if(!stack.isEmpty()) {
				toSend.setTag("" + i, stack.serializeNBT());
			}
			i++;
		}
		buffer.writeCompoundTag(toSend);
	}
	
	@Override public void fromBytes(ByteBuf buf) {
		PacketBuffer buffer = new PacketBuffer(buf);
		stacks = new ArrayList<ItemStack>();
		// Reads the int back from the buf. Note that if you have multiple values, you must read in the same order you wrote.
		try {
			toSend = buffer.readCompoundTag();
			int i = 0;
			while(toSend.hasKey("" + i)) {
				NBTTagCompound stacktag = (NBTTagCompound) toSend.getTag("" + i);
				stacks.add(new ItemStack(stacktag));
				i++;
			}
		}catch(Exception e) {
			
		}
	}
}
