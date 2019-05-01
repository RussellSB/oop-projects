package nl.rug.oop.introduction;

import java.util.*;

public class GoodRoom extends Room implements HealthModifier {

    public static final int INCREASE_HEALTH = 10;

    // Constructors
    public GoodRoom(String description) {
        super(description);
    }

    public GoodRoom(String description, ArrayList<Door> doors) {
        super(description, doors);
    }

    public GoodRoom(String description, ArrayList<Door> doors, ArrayList<NPC> npcs) {
        super(description, doors, npcs);
    }


    // Other methods
    @Override
    public void interact(Player player) {
        affectHealth(player, INCREASE_HEALTH);
        System.out.println("This room makes you feel safe. You feel your spirits lifting.");
    }

    @Override
    public void affectHealth(Player player, int amount) {
        player.affectHealth(amount);
    }
}