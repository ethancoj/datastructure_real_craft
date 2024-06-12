package net.ethan.tempestmod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.ethan.tempestmod.custom.DiaryManager;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;

import java.util.List;

public class AdventureDiaryScreen extends Screen {
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("tempestmod", "textures/gui/diary.png");
    private List<String> entries;
    private int scrollOffset = 0; // Keeps track of the scroll offset
    private final int lineHeight;
    private final int guiWidth = 248; // Width of the GUI area where text is printed
    private final int guiHeight = 166; // Height of the GUI area

    public AdventureDiaryScreen() {
        super(Component.translatable("screen.adventurer_diary"));
        DiaryManager diaryManager = DiaryManager.getInstance();
        entries = diaryManager.getEntries();
        lineHeight = 9; // Set a smaller line height for smaller text
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
        int x = (this.width - guiWidth) / 2;
        int y = (this.height - guiHeight) / 2;
        guiGraphics.blit(BACKGROUND_TEXTURE, x, y, 0, 0, guiWidth, guiHeight);

        // Render diary entries with scrolling and wrapping
        int entryY = y + 20; // Initial Y position for entries
        int visibleEntries = (guiHeight - 40) / lineHeight; // Number of visible entries

        for (int i = scrollOffset; i < entries.size() && entryY < y + guiHeight - 20; i++) {
            entryY += renderWrappedText(guiGraphics, entries.get(i), x + 10, entryY);
        }

        // Render the scrollbar
        renderScrollbar(guiGraphics, x + guiWidth - 10, y + 20, guiHeight - 40);
    }

    private int renderWrappedText(GuiGraphics guiGraphics, String text, int x, int y) {
        int maxWidth = guiWidth - 20;
        List<FormattedCharSequence> lines = this.font.split(Component.literal(text), maxWidth);
        for (FormattedCharSequence line : lines) {
            guiGraphics.drawString(this.font, line, x, y, 0xFFFFFF);
            y += lineHeight;
        }
        return lines.size() * lineHeight;
    }

    private void renderScrollbar(GuiGraphics guiGraphics, int x, int y, int height) {
        int linesVisible = height / lineHeight;
        int totalLines = entries.stream().mapToInt(entry -> countLines(entry, guiWidth - 20)).sum();
        int maxScroll = Math.max(0, totalLines - linesVisible);

        if (maxScroll > 0) {
            int scrollbarHeight = Math.max(10, (int)((float)linesVisible / totalLines * height));
            int scrollbarY = y + (int)((float)scrollOffset / maxScroll * (height - scrollbarHeight));
            guiGraphics.fill(x, scrollbarY, x + 8, scrollbarY + scrollbarHeight, 0xFFAAAAAA);
        }
    }

    private int countLines(String text, int maxWidth) {
        return this.font.split(Component.literal(text), maxWidth).size();
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        int linesVisible = (guiHeight - 40) / lineHeight;
        int totalLines = entries.stream().mapToInt(entry -> countLines(entry, guiWidth - 20)).sum();
        int maxScroll = Math.max(0, totalLines - linesVisible);
        scrollOffset = (int) Math.max(0, Math.min(scrollOffset - delta, maxScroll));
        return true;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        int linesVisible = (guiHeight - 40) / lineHeight;
        int totalLines = entries.stream().mapToInt(entry -> countLines(entry, guiWidth - 20)).sum();
        int maxScroll = Math.max(0, totalLines - linesVisible);

        if (keyCode == 264) { // Down arrow key
            scrollOffset = Math.min(scrollOffset + 1, maxScroll);
        } else if (keyCode == 265) { // Up arrow key
            scrollOffset = Math.max(scrollOffset - 1, 0);
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}