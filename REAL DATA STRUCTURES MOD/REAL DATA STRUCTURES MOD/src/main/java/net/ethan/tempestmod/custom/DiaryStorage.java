package net.ethan.tempestmod.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.world.entity.player.Player;

public class DiaryStorage {
    private static final String DIARY_TAG = "AdventurerDiary";

    public static void saveDiary(Player player) {
        CompoundTag playerData = player.getPersistentData();
        ListTag diaryEntries = new ListTag();

        for (String entry : DiaryManager.getInstance().getEntries()) {
            diaryEntries.add(StringTag.valueOf(entry.toString()));
        }

        playerData.put(DIARY_TAG, diaryEntries);
    }

    public static void loadDiary(Player player) {
        CompoundTag playerData = player.getPersistentData();
        if (playerData.contains(DIARY_TAG)) {
            ListTag diaryEntries = playerData.getList(DIARY_TAG, 8); // 8 is the ID for StringTag
            DiaryManager diaryManager = DiaryManager.getInstance();
            diaryEntries.forEach(entry -> diaryManager.logEvent(entry.getAsString()));
        }
    }
}