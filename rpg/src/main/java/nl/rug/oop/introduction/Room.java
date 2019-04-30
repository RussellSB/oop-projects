package nl.rug.oop.introduction;

import java.util.*;


public class Room extends Inspectable {
    // Attributes;
    private ArrayList<Door> doors = new ArrayList<>();


    // Constructor
    public Room(String description) {
        super(description);
    }

    public Room(String description, ArrayList<Door> doors) {
        super(description);
        this.doors = doors;
    }


    //Getters and Setters


    // Other methods
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
        System.out.println("You look around for doors. You see: ");
        this.inspectDoors();

        boolean quit = false;
        Scanner in = new Scanner(System.in); //Scanner for input
        int menuItem;

        do {
            System.out.println("Which door do you take?  (-1: stay here)");
            System.out.print("-> ");
            menuItem = in.nextInt();

            if(menuItem == -1) {
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