package com.irar.recards.world;

import java.util.ArrayList;
import java.util.UUID;

import com.irar.recards.Ref;
import com.irar.recards.card.Card;
import com.irar.recards.card.Cards;
import com.irar.recards.proxy.CommonProxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

public class SaveDataHandler extends WorldSavedData {
	private static final String DATA_NAME = Ref.MODID + "_PLAYER_CARD_DATA";
	private ArrayList<Object[]> playerCardData = new ArrayList<Object[]>();
	
	// Required constructors
	public SaveDataHandler() {
		super(DATA_NAME);
	}
	
	public SaveDataHandler(String s) {
		super(s);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		playerCardData = new ArrayList<Object[]>();
		int index = 0;
		if(nbt.hasKey(DATA_NAME)){
			NBTTagCompound carddata = nbt.getCompoundTag(DATA_NAME);
			while(carddata.hasKey(DATA_NAME + "_" + index)){
				NBTTagCompound playercarddata = carddata.getCompoundTag(DATA_NAME + "_" + index);
				if(playercarddata.hasKey("CARD_LIST")){
					PlayerDataHelper.readFromNBT(playercarddata, playerCardData);
				}else{
					ArrayList<ArrayList<String>> finishedCardData = new ArrayList<ArrayList<String>>();
					ArrayList<String> finishedTierData = new ArrayList<String>(); 
					String playerUUID = playercarddata.getString("UUID");
					String playerCN = playercarddata.getString("CARD_NAME");
					finishedTierData.add(playerCN);
					finishedCardData.add(finishedTierData);
					playerCardData.add(new Object[]{playerUUID, finishedCardData});
					this.markDirty();
					
				}
				index++;
			}
		}
		
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		NBTTagCompound carddata = new NBTTagCompound();
		for(int i = 0; i < this.playerCardData.size(); i++){
			NBTTagCompound playercarddata = new NBTTagCompound();
			PlayerDataHelper.writeToNBT(playercarddata, playerCardData.get(i));
			carddata.setTag(DATA_NAME + "_" + i, playercarddata);
		}
		compound.setTag(DATA_NAME, carddata);
		
		return compound;
	}
	
	public static SaveDataHandler get(World world) {
		// The IS_GLOBAL constant is there for clarity, and should be simplified into the right branch.
		MapStorage storage = /*IS_GLOBAL ? */world.getMapStorage();// : world.getPerWorldStorage();
		SaveDataHandler instance = (SaveDataHandler) storage.getOrLoadData(SaveDataHandler.class, DATA_NAME);

		if (instance == null) {
			instance = new SaveDataHandler();
			storage.setData(DATA_NAME, instance);
		}
		return instance;
	}
	// WorldSavedData methods
	
	public boolean isPlayerInList(EntityPlayer player){
		boolean inList = false;
		for(int i = 0; i < this.playerCardData.size(); i++){
			String testUUID = (String) this.playerCardData.get(i)[0];
			String playerUUID = player.getUniqueID().toString();
			if(playerUUID.equals(testUUID)){
//				System.out.println("Found " + player.getName() + " in list");
				return true;
			}
		}
//		System.out.println("Could not find " + player.getName() + " in list");
		return inList;
	}
	
	@Deprecated
	public Card getCardForPlayer(EntityPlayer player){
		Card card = null;
		if(this.isPlayerInList(player)){
			for(int i = 0; i < this.playerCardData.size(); i++){
				String testUUID = (String) this.playerCardData.get(i)[0];
				String playerUUID = player.getUniqueID().toString();
				if(playerUUID.equals(testUUID)){
					card = Cards.getCardFromId(((ArrayList<ArrayList<String>>) this.playerCardData.get(i)[1]).get(0).get(0));
				}
			}
		}
		return card;
	}
	
	public ArrayList<Object[]> getPlayerSaveData(){
		return this.playerCardData;
	}
	
	public void addPlayer(EntityPlayer playerIn, Card selectedCard){
		ArrayList<ArrayList<String>> tierList = new ArrayList<ArrayList<String>>();
		ArrayList<String> tierData = new ArrayList<String>();
		tierData.add(selectedCard.id);
		tierList.add(tierData);
		Object[] cardData = new Object[]{playerIn.getUniqueID().toString(), tierList};
		playerCardData.add(cardData);
		this.markDirty();
		if(playerIn instanceof EntityPlayerMP) {
			EntityPlayerMP playermp = (EntityPlayerMP) playerIn;
			CommonProxy.tickMap.put(playermp, -10);
		}
	}

	public ArrayList<Object[]> getCardsWithTierForPlayer(EntityPlayer player) {
		ArrayList<Object[]> cardAndTiers = new ArrayList<Object[]>();
		if(this.isPlayerInList(player)){
			for(int i = 0; i < this.playerCardData.size(); i++){
				String testUUID = (String) this.playerCardData.get(i)[0];
				String playerUUID = player.getUniqueID().toString();
				if(playerUUID.equals(testUUID)){
					if(this.playerCardData.get(i)[1] instanceof ArrayList<?>){
						for(int j = 0; j < ((ArrayList<ArrayList<String>>) this.playerCardData.get(i)[1]).size(); j++){
							ArrayList<String> tierData = ((ArrayList<ArrayList<String>>) this.playerCardData.get(i)[1]).get(j);
							for(int k = 0; k < tierData.size(); k++){
								String cardId = tierData.get(k);
								cardAndTiers.add(new Object[]{Cards.getCardFromId(cardId), j + 1});
							}
						}
					}else{
						System.out.println("Ran into an unknown error; try reloading the world"); 
					}
				}
			}
		}
		return cardAndTiers;
	}

	public boolean hasCardInTier(Card currentCard, int tier, EntityPlayer player) {
		
		ArrayList<Object[]> playerCards = this.getCardsWithTierForPlayer(player);
		for(int i = 0; i < playerCards.size(); i++){
			if(((Card) playerCards.get(i)[0]).equals(currentCard) && ((int) playerCards.get(i)[1]) == tier){
				return true;
			}
		}
		
		return false;
	}

	public boolean hasCard(Card currentCard, EntityPlayer player) {
		ArrayList<Object[]> playerCards = this.getCardsWithTierForPlayer(player);
		for(int i = 0; i < playerCards.size(); i++){
			if(((Card) playerCards.get(i)[0]).equals(currentCard)){
				return true;
			}
		}
		
		return false;
	}
	
	public int getTierForCard(Card card, EntityPlayer player){
		ArrayList<Object[]> playerCards = this.getCardsWithTierForPlayer(player);
		for(int i = 0; i < playerCards.size(); i++){
			if(((Card) playerCards.get(i)[0]).equals(card)){
				return (int) playerCards.get(i)[1];
			}
		}
		return -1;
	}

	public void upgradeCard(Card card, EntityPlayer player) {
		if(!player.world.isRemote){
			int tier = this.getTierForCard(card, player);
			if(tier == -1){
				this.setCardInTier(card, 1, player);
			}else{
				this.removeCard(card, player);
				this.setCardInTier(card, tier + 1, player);
			}
			this.markDirty();
			if(player instanceof EntityPlayerMP) {
				EntityPlayerMP playermp = (EntityPlayerMP) player;
				CommonProxy.tickMap.put(playermp, -10);
			}
		}
	}

	private void setCardInTier(Card card, int tier, EntityPlayer player) {
		ArrayList<ArrayList<String>> playerData = (ArrayList<ArrayList<String>>) this.playerCardData.get(this.getDataIndexForPlayer(player))[1];
		while(playerData.size() < tier){
			playerData.add(new ArrayList<String>());
		}
		playerData.get(tier - 1).add(card.id);
	}

	private void removeCard(Card card, EntityPlayer player) {
		ArrayList<Object[]> playerCards = this.getCardsWithTierForPlayer(player);
		for(int i = 0; i < playerCards.size(); i++){
			if(((Card) playerCards.get(i)[0]).equals(card)){
				int tier = (int) playerCards.get(i)[1];
				((ArrayList<ArrayList<String>>) this.playerCardData.get(this.getDataIndexForPlayer(player))[1]).get(tier - 1).remove(card.id);
			}
		}
	}

	private int getDataIndexForPlayer(EntityPlayer player) {
		for(int i = 0; i < this.playerCardData.size(); i++){
			String testUUID = (String) this.playerCardData.get(i)[0];
			String playerUUID = player.getUniqueID().toString();
			if(playerUUID.equals(testUUID)){
				return i;
			}
		}
		return -1;
	}

	public void removePlayer(EntityPlayer playerIn) {
		
		for(int i = 0; i < playerCardData.size(); i++){
			String testUUID = (String) this.playerCardData.get(i)[0];
			String playerUUID = playerIn.getUniqueID().toString();
			if(playerUUID.equals(testUUID)){
				playerCardData.remove(i);
			}
		}
		
	}
	
}