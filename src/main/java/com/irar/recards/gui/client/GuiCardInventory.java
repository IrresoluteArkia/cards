package com.irar.recards.gui.client;

import com.irar.recards.gui.container.ContainerCardInventory;
import com.irar.recards.gui.inventory.CardInventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiCardInventory extends GuiContainer {

	private IInventory playerInv;
	private CardInventory ci;

	public GuiCardInventory(InventoryPlayer playerInv, CardInventory ci) {
	    super(new ContainerCardInventory(playerInv, ci));

	    this.playerInv = playerInv;
	    this.ci = ci;

	    this.xSize = 248;
	    this.ySize = 184;
	}

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("recards:textures/gui/container/card_selector.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
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