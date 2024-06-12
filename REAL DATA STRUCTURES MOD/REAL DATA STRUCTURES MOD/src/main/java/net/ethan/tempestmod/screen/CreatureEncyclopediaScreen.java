package net.ethan.tempestmod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.ethan.tempestmod.custom.CreatureEncyclopediaData;
import net.ethan.tempestmod.custom.CreatureInfo;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.glfw.GLFW;
import java.util.ArrayList;
import java.util.List;

public class CreatureEncyclopediaScreen extends Screen {
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("tempestmod", "textures/gui/encyclopedia.png");
    private EditBox searchField;
    private List<String> displayedText;
    private final CreatureEncyclopediaData encyclopedia;

    public CreatureEncyclopediaScreen() {
        super(Component.translatable("screen.creature_encyclopedia"));
        encyclopedia = new CreatureEncyclopediaData();
        displayedText = new ArrayList<>();
    }

    @Override
    protected void init() {
        super.init();
        this.searchField = new EditBox(this.font, this.width / 2 - 100, 40, 200, 20, Component.translatable("search"));
        this.addRenderableWidget(this.searchField);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);

        RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
        int x = (this.width - 248) / 2;
        int y = (this.height - 166) / 2;
        guiGraphics.blit(BACKGROUND_TEXTURE, x, y, 0, 0, 248, 166);

        this.searchField.render(guiGraphics, mouseX, mouseY, partialTicks);

        if (!displayedText.isEmpty()) {
            int lineY = 70; // Starting Y position for text
            for (String line : displayedText) {
                guiGraphics.drawString(this.font, line, this.width / 2 - 100, lineY, 0xFFFFFF);
                lineY += 10; // Move down for the next line
            }
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.searchField.isFocused() && (keyCode == GLFW.GLFW_KEY_ENTER || keyCode == GLFW.GLFW_KEY_KP_ENTER)) {
            String searchText = this.searchField.getValue();
            CreatureInfo info = encyclopedia.getCreatureInfo(searchText);
            displayedText.clear();
            if (info != null) {
                displayedText.add("Name: " + info.getName());
                displayedText.add("Habitat: " + info.getHabitat());
                displayedText.add("Behavior: " + info.getBehavior());
                displayedText.add("Drops: " + info.getDrops());
            } else {
                displayedText.add("No information available for: " + searchText);
            }
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void onClose() {
        super.onClose();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
