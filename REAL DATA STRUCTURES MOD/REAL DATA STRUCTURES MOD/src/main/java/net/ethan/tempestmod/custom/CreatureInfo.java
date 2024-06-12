package net.ethan.tempestmod.custom;

/**
 * A class to hold information about Minecraft creatures.
 */
public class CreatureInfo {
    private String name;        // The name of the creature
    private String habitat;     // The usual habitat of the creature
    private String behavior;    // Description of the creature's behavior
    private String drops;       // What items the creature drops when defeated

    /**
     * Constructor to initialize the creature information.
     * @param name the name of the creature
     * @param habitat the habitat of the creature
     * @param behavior the behavior of the creature
     * @param drops the items dropped by the creature
     */
    public CreatureInfo(String name, String habitat, String behavior, String drops) {
        this.name = name;
        this.habitat = habitat;
        this.behavior = behavior;
        this.drops = drops;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getHabitat() {
        return habitat;
    }

    public String getBehavior() {
        return behavior;
    }

    public String getDrops() {
        return drops;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public void setDrops(String drops) {
        this.drops = drops;
    }

    /**
     * Provides a string representation of the creature information.
     * @return a string describing the creature.
     */
    @Override
    public String toString() {
        return "CreatureInfo{" +
                "name='" + name + '\'' +
                ", habitat='" + habitat + '\'' +
                ", behavior='" + behavior + '\'' +
                ", drops='" + drops + '\'' +
                '}';
    }
}
