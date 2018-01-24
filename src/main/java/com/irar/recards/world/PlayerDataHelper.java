package com.irar.recards.world;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;

public class PlayerDataHelper {

	public static void readFromNBT(NBTTagCompound nbt, ArrayList<Object[]> playerCardData) {
		String playerUUID = nbt.getString("UUID");
		NBTTagCompound cardData = nbt.getCompoundTag("CARD_LIST");
		ArrayList<ArrayList<String>> finishedCardData = new ArrayList<ArrayList<String>>();
		int tier = 1;
//		System.out.println("READING " + "A PLAYER" + "; IF THIS KEEPS GOING FOR TOO LONG, THERE'S PROBABLY A PROBLEM");
		while(cardData.hasKey("TIER_" + tier)){
			ArrayList<String> finishedTierData = new ArrayList<String>(); 
			NBTTagCompound tierData = cardData.getCompoundTag("TIER_" + tier);
			int entry = 1;
//			System.out.println("READING " + "TIER_" + tier + "; IF THIS KEEPS GOING FOR TOO LONG, THERE'S PROBABLY A PROBLEM");
			while(tierData.hasKey("ENTRY_" + entry)){
				finishedTierData.add(tierData.getString("ENTRY_" + entry));
				entry++;
				System.out.println("READING " + "ENTRY_" + entry + "; IF THIS KEEPS GOING FOR TOO LONG, THERE'S PROBABLY A PROBLEM");
			}
			finishedCardData.add(finishedTierData);
			tier++;
//			System.out.println("ADDED 1 TO TIER SEARCH");
		}
		playerCardData.add(new Object[]{playerUUID, finishedCardData});
	}

	public static void writeToNBT(NBTTagCompound nbt, Object[] playerCardData) {
		nbt.setString("UUID", (String) playerCardData[0]);
		NBTTagCompound finishedCardData = new NBTTagCompound();
		ArrayList<ArrayList<String>> cardData = (ArrayList<ArrayList<String>>) playerCardData[1];
		for(int i = 0; i < cardData.size(); i++){
			ArrayList<String> tierData = cardData.get(i);
			NBTTagCompound finishedTierData = new NBTTagCompound();
			for(int j = 0; j < tierData.size(); j++){
				finishedTierData.setString("ENTRY_" + (j + 1), tierData.get(j));
			}
			finishedCardData.setTag("TIER_" + (i + 1), finishedTierData);
		}
		nbt.setTag("CARD_LIST", finishedCardData);
	}

}
