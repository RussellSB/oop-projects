import nl.rug.oop.introduction.*;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        // Initializing rooms
        Room room1 = new Room("a dark omnious room with a space themed door.");
        Room room2 = new Room("an icey cold room with a space themed door and another with a game of thrones reference.");
        Room room3 = new Room("wait a second, this isn't just any room - this is the Room! You see your somewhat typically average American man."); 
        Room room4 = new Room("but you don't. Feeling scared and frightened, you look around you. It's pitch black! You hear heavy breathing. You can't shake the feeling of a somewhat nostalgic deja vu."); 
        Room room5 = new Room("a beige texture covering the room's interior. There appears to be an overly optimistic Russian man at the back of the room.");
        Room room6 = new Room("ea room where everything's gold! There's even a treasure chest continuously spewing confetti from its sides! If you're truly materialistic then congrats you won! If not then it's the journey you enjoyed along the way.");
        // Initializing doors
        Door door1_1 = new Door("a door with an image of a blackhole imprinted on it.", room2);
        Door door2_1 = new Door("a door with the back of a blackhole imprinted on it.", room1);
        Door door2_2 = new Door("a door with a beefy possesed fellow pushing at it, struggling to hold the door.", room3);
        Door door3_1 = new Door("a door with tens of blue zombie-like creatures pushing at it. This hardly intimidates you. You're already dead inside.", room2);
        Door door3_2 = new Door("a door with the words YOURE TEARING ME APART LISA, written in blood.", room4);
        Door door3_3 = new Door("a door with a large troll face imprinted on it. It makes you cringe, reminding you of your obsession with the early 2010s memes.", room5);
        Door door3_4 = new Door("a door with a large crown painted in gold and glittered words reading PLS DO NOT DISTR0B.", room6);
        Door door4_1 = new Door("it's too dark to see but feeling the door's surface it feels particularly familiar.", room4);
        Door door5_1 = new Door("a door with the back of the troll face imprinted on it. It makes you cringe, looking back on when memes were worse.", room3);
        Door door5_2 = new Door("a door looking similar to what you saw earlier, with diamond blue words reading BACK OFF PLS N THX.", room6);
        // Adding doors
        room1.addDoor(door1_1);
        room2.addDoor(door2_1);
        room2.addDoor(door2_2);
        room3.addDoor(door3_1);
        room3.addDoor(door3_2);
        room3.addDoor(door3_3);
        room3.addDoor(door3_4);
        room4.addDoor(door4_1);
        room5.addDoor(door5_1);
        room5.addDoor(door5_2);

        // Initializing player and their starting point
        Player player = new Player(room1); // Placing player in room1

        // User Interface
        boolean quit = false;
        Scanner in = new Scanner(System.in); //Scanner for input
        int menuItem;

        do {
            System.out.println("\nWhat do you want to do?  (-1: leave game)");
            System.out.println("  (0) Look around");
            System.out.println("  (1) Look for a way out");
            System.out.print("-> ");
            menuItem = in.nextInt();

            switch (menuItem) {
                case 0:
                    player.getCurrentRoom().inspect();
                    break;
                case 1:
                    player.getCurrentRoom().interactWithDoors(player);
                    break;
                case -1:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (!quit);

        System.out.println("Bye-bye!");
    }
}