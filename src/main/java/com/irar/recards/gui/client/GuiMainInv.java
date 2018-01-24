package com.irar.recards.gui.client;

import com.irar.recards.gui.container.ContainerCardInventory;
import com.irar.recards.gui.container.ContainerMainInv;
import com.irar.recards.gui.inventory.CardInventory;
import com.irar.recards.gui.inventory.MainInv;
import com.irar.recards.gui.slot.SlotCard;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

public class GuiMainInv extends GuiContainer {

	private IInventory playerInv;
	private MainInv ci;

	public GuiMainInv(InventoryPlayer playerInv, MainInv ci) {
	    super(new ContainerMainInv(playerInv, ci));

	    this.playerInv = playerInv;
	    this.ci = ci;

	    this.xSize = 248;
	    this.ySize = 184;
	}

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("recards:textures/gui/container/card_viewer.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("recards:textures/gui/container/slot.png"));
        for(Slot slot : this.inventorySlots.inventorySlots) {
        	if(slot instanceof SlotCard) {
        		SlotCard cardslot = (SlotCard) slot;
                this.drawTexturedModalRect(this.guiLeft + cardslot.xPos - 1, this.guiTop + cardslot.yPos - 1, 0, 0, 18, 18);
        	}
        }
    }
    
    /**
     * Draws the screen and all the components in it.
     */
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.fontRenderer.drawString(this.ci.getDisplayName().getUnformattedText(), 8, 4, 4210752);
        this.fontRenderer.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }
    
}