package nl.rug.oop.introduction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


abstract class Room extends Inspectable implements Interactable, Serializable {
    // Attributes
    private static final long serialVersionUID = 1L;
    private List<Door> doors = new ArrayList<>();
    private List<NPC> npcs = new ArrayList<>();


    // Constructor
    Room(String description) {
        super(description);
    }


    // Getters and Setters
    void addDoor(Door door) {
        if(door != null)
            this.doors.add(door);
    }

    void addNPC(NPC npc) {
        if(npc != null)
            this.npcs.add(npc);
    }


    // Other methods
    void inspect() {
        System.out.println("\nYou look around");
        System.out.print("You see: ");
        super.inspect();
    }

    private void inspectInspectables(List<? extends Inspectable> inspectables)
    {
        for(int i=0; i<inspectables.size(); i++) {
            System.out.print("  ("+ i + ") ");
            try {
                inspectables.get(i).inspect();
            } catch (NullPointerException e) {
                System.out.println("NULL item");
            }
        }
    }

    private void inspectDoors() {
        inspectInspectables(this.doors);
    }

    private void inspectNPCs() {
        inspectInspectables(this.npcs);
    }

    private void interactWithInteractables(List<? extends Interactable> interactables, Player player) {
        boolean quit = false;
        Scanner in = new Scanner(System.in); //Scanner for input
        int menuItem;

        do {
            System.out.println("Which one do you choose?  (-1: cancel)");
            System.out.print("-> ");
            try {
                menuItem = in.nextInt();
            } catch (Exception e) {
                System.out.println("Input must be a number");
                menuItem = -10000;
                in.nextLine();
            }

            if (menuItem == -1) {
                quit = true;
            } else if (menuItem > interactables.size() || menuItem < 0) {
                System.out.println("Invalid choice.");
            } else {
                try {
                    interactables.get(menuItem).interact(player);
                } catch (NullPointerException e) {
                    System.out.println("You cannot interact with a NULL item");
                }
                quit = true;
            }
        } while (!quit);
    }

    void interactWithDoors(Player player) {
        System.out.println("\nYou look around for doors.");
        if(this.doors.isEmpty()) {
            System.out.println("There are no doors...");
        } else {
            System.out.println("You see:");
            this.inspectDoors();
            this.interactWithInteractables(doors, player);
        }
    }

    void interactWithNPCs(Player player) {
        System.out.println("\nYou look around for signs of life.");
        if(this.npcs.isEmpty()) {
            System.out.println("There's nobody around. You're so lonely...");
        } else {
            System.out.println("You perceive:");
            this.inspectNPCs();
            this.interactWithInteractables(npcs, player);
        }
    }
}