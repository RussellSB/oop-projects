package nl.rug.oop.introduction;

import java.util.*;

public class GoodRoom extends Room{

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

    @Override //gives you health cause its a good room!
    public void actRoom(){
        System.out.println("This room makes you feel safe. You feel your spirits lifting.");
        this.getPlayer().affectHealth(10);
    }

}