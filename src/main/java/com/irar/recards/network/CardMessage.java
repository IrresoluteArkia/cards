package com.irar.recards.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class CardMessage implements IMessage{

	public int toSend;
	
	public CardMessage(){}
	
	public CardMessage(int toSend) {
		System.out.println("Made it here");
		this.toSend = toSend;
	}

	@Override public void toBytes(ByteBuf buf) {
		// Writes the int into the buf
		buf.writeInt(toSend);
	}
	
	@Override public void fromBytes(ByteBuf buf) {
		// Reads the int back from the buf. Note that if you have multiple values, you must read in the same order you wrote.
		toSend = buf.readInt();
	}
}
