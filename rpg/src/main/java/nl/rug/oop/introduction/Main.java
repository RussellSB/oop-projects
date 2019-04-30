import nl.rug.oop.introduction.*;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        Room room1 = new Room("room with 2 doors"); // Creating red room
        Room room2 = new Room("room with only 1 door"); // Creating red room
        Door redDoor = new Door("red door", room2);
        Door hoDoor = new Door("hold the door", room2);
        Door xDoor = new Door("equa door", room1);
        room1.addDoor(redDoor);
        room1.addDoor(null); // Should be ignored
        room1.addDoor(hoDoor);
        room2.addDoor(xDoor);
        Player player = new Player(room1); // Placing player in room1


        boolean quit = false;
        Scanner in = new Scanner(System.in); //Scanner for input
        int menuItem;



        do {
            System.out.println("What do you want to do?");
            System.out.println("  (0) Look around");
            System.out.println("  (1) Look for a way out");

            System.out.print("-> ");
            menuItem = in.nextInt();

            switch (menuItem) {
                case 0:
                    System.out.print("You see: ");
                    player.getCurrentRoom().inspect();
                    break;
                case 1:
                    player.getCurrentRoom().interactWithDoors(player);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (!quit);

        System.out.println("Bye-bye!");
    }
}