package nl.rug.oop.introduction;

import java.util.*;


public class BadRoom extends Room {

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

    @Override //gives you health cause its a good room!
    public void actRoom(){
        System.out.println("This room makes you feel uneasy. Your motivation to live drops.");
        this.getPlayer().affectHealth(-10);
    }

}