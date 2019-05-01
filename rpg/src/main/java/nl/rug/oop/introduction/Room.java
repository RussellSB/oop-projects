package nl.rug.oop.introduction;

import java.util.*;


public class Room extends Inspectable {
    // Attributes;
    private ArrayList<Door> doors = new ArrayList<>();
    private ArrayList<NPC> npcs = new ArrayList<>();


    // Constructor
    public Room(String description) {
        super(description);
    }

    public Room(String description, ArrayList<Door> doors) {
        super(description);
        this.doors = doors;
    }

    public Room(String description, ArrayList<Door> doors, ArrayList<NPC> npcs) {
        super(description);
        this.doors = doors;
        this.npcs = npcs;
    }


    //Getters and Setters


    // Other methods
    public void inspect() {
        System.out.println("\nYou look around");
        System.out.print("You see: ");
        super.inspect();
    }

    public void addDoor(Door door) {
        if(door != null)
            this.doors.add(door);
    }

    public void inspectDoors() {
        for(int i=0; i<doors.size(); i++) {
            System.out.print("  ("+ i + ") ");
            doors.get(i).inspect();
        }
    }

    public void interactWithDoors(Player player) {
        System.out.println("\nYou look around for doors.");
        if(this.doors.isEmpty()) {
            System.out.println("There are no doors...");
        } else {
            System.out.println("You see:");
            this.inspectDoors();

            boolean quit = false;
            Scanner in = new Scanner(System.in); //Scanner for input
            int menuItem;

            do {
                System.out.println("Which door do you take?  (-1: stay here)");
                System.out.print("-> ");
                menuItem = in.nextInt();

                if (menuItem == -1) {
                    quit = true;
                } else if (menuItem > doors.size() || menuItem < 0) {
                    System.out.println("Invalid choice.");
                } else {
                    doors.get(menuItem).interact(player);
                    quit = true;
                }
            } while (!quit);
        }
    }

    public void addNPC(NPC npc) {
        if(npc != null)
            this.npcs.add(npc);
    }

    public void inspectNPCs() {
        for(int i=0; i<npcs.size(); i++) {
            System.out.print("  ("+ i + ") ");
            npcs.get(i).inspect();
        }
    }

    public void interactWithNPCs(Player player) {
        System.out.println("\nYou look around for signs of life.");
        if(this.npcs.isEmpty()) {
            System.out.println("There's nobody around. You're so lonely...");
        } else {
            System.out.println("You percieve:");
            this.inspectNPCs();

            boolean quit = false;
            Scanner in = new Scanner(System.in); //Scanner for input
            int menuItem;

            do {
                System.out.println("Interact with who?  (-1: be antisocial)");
                System.out.print("-> ");
                menuItem = in.nextInt();

                if (menuItem == -1) {
                    quit = true;
                } else if (menuItem > npcs.size() || menuItem < 0) {
                    System.out.println("Invalid choice.");
                } else {
                    npcs.get(menuItem).interact(player);
                    quit = true;
                }
            } while (!quit);
        }
    }
    
}