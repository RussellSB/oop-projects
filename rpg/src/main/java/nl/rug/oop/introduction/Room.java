package nl.rug.oop.introduction;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


abstract class Room extends Inspectable implements Interactable {
    // Attributes
    private List<Door> doors = new ArrayList<>();
    private List<NPC> npcs = new ArrayList<>();


    // Constructor
    Room(String description) {
        super(description);
    }


    // Getters and Setters
    void addDoor(Door door) {
        if (door != null)
            this.doors.add(door);
    }

    void addNPC(NPC npc) {
        if (npc != null)
            this.npcs.add(npc);
    }


    // Other methods
    @Override
    void inspect() {
        System.out.println("\nYou look around");
        System.out.print("You see: ");
        super.inspect();
    }

    private void inspectInspectables(List<? extends Inspectable> inspectables) {
        for (int i = 0; i < inspectables.size(); i++) {
            System.out.print("  (" + i + ") ");
            try {
                inspectables.get(i).inspect();
            } catch (NullPointerException e) {
                System.out.println("** NULL item **");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("** Error 404: Item not found **");
            } catch (Exception e) {
                System.out.println("** A weird error occurred: " + e.toString() + " **");
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
        Scanner in = new Scanner(System.in); // Scanner for input
        int selectedMenuItem;

        System.out.println("Which one do you choose?  (-1: cancel)");
        System.out.print("-> ");

        try {
            selectedMenuItem = in.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("** Input must be a number **");
            selectedMenuItem = -1; // Cancel
            in.nextLine(); // Discards the rest of the input
        } catch (Exception e) {
            System.out.println("** A weird error occurred: " + e.toString() + " **");
            selectedMenuItem = -1; // Cancel
            in.nextLine(); // Discards the rest of the input
        }

        if (selectedMenuItem != -1) {
            try {
                interactables.get(selectedMenuItem).interact(player);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("** Error 404: Item not found **");
            } catch (NullPointerException e) {
                System.out.println("** You cannot interact with a NULL item **");
            } catch (Exception e) {
                System.out.println("** A weird error occurred: " + e.toString() + " **");
            }
        }
    }

    void interactWithDoors(Player player) {
        System.out.println("\nYou look around for doors.");
        if (this.doors.isEmpty()) {
            System.out.println("There are no doors...");
        } else {
            System.out.println("You see:");
            this.inspectDoors();
            this.interactWithInteractables(doors, player);
        }
    }

    void interactWithNPCs(Player player) {
        System.out.println("\nYou look around for signs of life.");
        if (this.npcs.isEmpty()) {
            System.out.println("There's nobody around. You're so lonely...");
        } else {
            System.out.println("You perceive:");
            this.inspectNPCs();
            this.interactWithInteractables(npcs, player);
        }
    }
}