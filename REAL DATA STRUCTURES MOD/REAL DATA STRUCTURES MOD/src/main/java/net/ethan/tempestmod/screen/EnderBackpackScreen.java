package net.ethan.tempestmod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.ethan.tempestmod.container.EnderBackpackContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class EnderBackpackScreen extends AbstractContainerScreen<EnderBackpackContainer> {
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation("tempestmod", "textures/gui/container/ender_backpack.png");

    public EnderBackpackScreen(EnderBackpackContainer container, Inventory inv, Component title) {
        super(container, inv, title);
        this.imageWidth = 176;

        // Dynamically calculate the height based on the number of slots
        int slotCount = container.getInventory().getSlots();
        int rows = (int) Math.ceil(slotCount / 9.0);
        this.imageHeight = 114 + rows * 18; // Adjust height based on slots
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        // Draw the GUI texture
        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        // Render the title label
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752);
        // Render the inventory label
        guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 4210752);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
