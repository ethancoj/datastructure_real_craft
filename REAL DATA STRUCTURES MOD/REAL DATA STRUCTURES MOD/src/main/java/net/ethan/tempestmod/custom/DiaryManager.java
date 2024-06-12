package net.ethan.tempestmod.custom;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DiaryManager {
    private static final DiaryManager instance = new DiaryManager();
    private LinkedHashMap<String, DiaryEntry> entries;

    private DiaryManager() {
        entries = new LinkedHashMap<>();
    }

    public static DiaryManager getInstance() {
        return instance;
    }

    public void logEvent(String description) {
        String formattedTimestamp = getFormattedTimestamp();
        entries.put(formattedTimestamp, new DiaryEntry(formattedTimestamp, description));
    }

    public List<String> getEntries() {
        return entries.values().stream()
                .map(DiaryEntry::toString)
                .collect(Collectors.toList());
    }

    private String getFormattedTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}