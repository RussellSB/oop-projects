package nl.rug.oop.introduction;

import java.util.ArrayList;

public class BadRoom extends Room implements HealthModifier {

    public static final int DECREASE_HEALTH = -10;

    // Constructors
    public BadRoom(String description) {
        super(description);
    }

    public BadRoom(String description, ArrayList<Door> doors) {
        super(description, doors);
    }

    public BadRoom(String description, ArrayList<Door> doors, ArrayList<NPC> npcs) {
        super(description, doors, npcs);
    }


    // Other methods
    @Override
    public void interact(Player player) {
        affectHealth(player, DECREASE_HEALTH);
        System.out.println("This room makes you feel uneasy. You slightly lose your motivation to live.");
    }

    @Override
    public void affectHealth(Player player, int amount) {
        player.affectHealth(amount);
    }
}