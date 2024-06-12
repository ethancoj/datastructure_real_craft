package net.ethan.tempestmod.custom;

public class DiaryEntry {
    private String timestamp;
    private String description;

    public DiaryEntry(String timestamp, String description) {
        this.timestamp = timestamp;
        this.description = description;
    }

    @Override
    public String toString() {
        return timestamp + ": " + description;
    }
}