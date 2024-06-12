package net.ethan.tempestmod.custom;

import java.util.Hashtable;

/**
 * Manages creature information, allowing for quick retrieval using creature names.
 */
public class CreatureEncyclopediaData {
    private Hashtable<String, CreatureInfo> creatures;

    /**
     * Constructor initializes the hashtable and loads creature data.
     */
    public CreatureEncyclopediaData() {
        creatures = new Hashtable<>();
        loadCreatureData();
    }

    /**
     * Populates the hashtable with sample creature data.
     */
    private void loadCreatureData() {
        creatures.put("Zombie", new CreatureInfo("Zombie", "Overworld", "Hostile at night and in dark areas.", "Rotten Flesh"));
        creatures.put("Skeleton", new CreatureInfo("Skeleton", "Overworld", "Hostile, uses a bow.", "Bones, Arrows"));
        creatures.put("Creeper", new CreatureInfo("Creeper", "Overworld", "Hostile, explodes on proximity.", "Gunpowder"));
        // Add more creatures as needed
    }

    /**
     * Retrieves creature information based on the creature's name.
     * @param name the name of the creature to retrieve info for
     * @return the CreatureInfo associated with the name, or null if not found
     */
    public CreatureInfo getCreatureInfo(String name) {
        return creatures.get(name);
    }
}
