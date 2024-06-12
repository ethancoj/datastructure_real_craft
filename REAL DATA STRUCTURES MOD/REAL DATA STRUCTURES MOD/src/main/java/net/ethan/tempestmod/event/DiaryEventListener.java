package net.ethan.tempestmod.event;

import net.ethan.tempestmod.custom.DiaryManager;
import net.ethan.tempestmod.custom.DiaryStorage;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;

public class DiaryEventListener {

    // Register the event listener
    public static void register(IEventBus eventBus) {
        eventBus.register(new DiaryEventListener());
    }

    // Listen for player advancements (achievements)
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onPlayerAdvancement(AdvancementEvent.AdvancementEarnEvent event) {
        Player player = event.getEntity();
        if (event.getAdvancement().getDisplay() != null) {
            String advancementName = event.getAdvancement().getDisplay().getTitle().getString();
            DiaryManager.getInstance().logEvent("Achievement unlocked: " + advancementName);
        }
    }

    // Listen for player logins and load diary
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        DiaryStorage.loadDiary(player);
        DiaryManager.getInstance().logEvent("Player logged in: " + player.getName().getString());
    }

    // Listen for player logouts and save diary
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        Player player = event.getEntity();
        DiaryStorage.saveDiary(player);
        DiaryManager.getInstance().logEvent("Player logged out: " + player.getName().getString());
    }
}