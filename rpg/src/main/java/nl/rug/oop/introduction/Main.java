import nl.rug.oop.introduction.*;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        // Initializing rooms
        Room room1 = new Room("a dark omnious room with a space themed door.");
        Room room2 = new Room("an icey cold room with a space themed door and another with a game of thrones reference.");
        Room room3 = new Room("wait a second, this isn't just any room - this is the Room! You see your somewhat typically average American man at the back of the room."); 
        Room room4 = new Room("but you don't. Feeling scared and frightened, you look around you. It's pitch black! You hear heavy breathing. You can't shake the feeling of a somewhat nostalgic deja vu."); 
        Room room5 = new Room("a beige texture covering the room's interior. There appears to be an overly optimistic Russian man at the back of the room.");
        Room room6 = new Room("a room where everything's gold! There's even a treasure chest continuously spewing rainbows from its sides! If you're truly materialistic then congrats you won! Leave the game to go spend your highly-valued imaginary gold!");
        // Initializing doors
        Door door1_1 = new Door("a door with an image of a blackhole imprinted on it.", room2);
        Door door2_1 = new Door("a door with the back of a blackhole imprinted on it.", room1); // go back
        Door door2_2 = new Door("a door with a beefy possesed fellow pushing at it, struggling to hold the door.", room3);
        Door door3_1 = new Door("a door with tens of blue zombie-like creatures pushing at it. This hardly intimidates you. You're already dead inside.", room2); // go back
        Door door3_2 = new Door("a door with the words YOURE TEARING ME APART LISA, written in blood.", room4);
        Door door3_3 = new Door("a door with a large troll face imprinted on it. It makes you cringe, reminding you of your memes obsession in the early 2010s.", room5);
        Door door3_4 = new Door("a door with a large crown painted in gold and glittered words reading PLS DO NOT DISTR0B.", room6);
        Door door4_1 = new Door("actually you don't. It's too dark to see but feeling the door's surface it feels particularly familiar.", room4);
        Door door5_1 = new Door("a door with the back of the troll face imprinted on it. It still makes you cringe, making you look back on when memes were worse.", room3); // go back
        Door door5_2 = new Door("a door looking similar to the gold one you saw earlier, with diamond blue words reading BACK OFF PLS N THX.", room6);
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
        // Initializing NPCs
        Adult hodor = new Adult("A beefy man repeatedly yelling Hodor. His eyes are pearl white.", "\"HODOR!\"");
        Child branStark = new Child("A little boy in a wheelchair. His eyes are also pearl white.", "He ignores you. You assume he might be mind-controlling the beefy man.");
        Child girl = new Child("A girl concerned about the door the beefy man is pushing. She seems exhausted.", "\"My brother died for the three eyed-raven, he did.\"");
        Adult tom = new Adult("A man who doesn't appear as American as he initially seemed. He has a water bottle in his hand.", "\"I DID NOT HIT HER, I DID NOOT! Oh.... hi Mark.\"");
        Adult trollMan = new Adult("A man in a classic beige suit. His smile is enchanting.", "He sings and dances to music that starts playing from the ceiling\n\"trolololololololololololololoolololo\"");
        Unknown cantSee = new Unknown("sound coming from the corner of the room, far right of you", "The breathing stops momentarily.\n\"We're trapped! We're trapped! You're a monster! The 2012 apocalypse is real! I can't escape! The door's all the same! The door's all the same!\"");
        // Adding NPCs to rooms
        room2.addNPC(hodor);
        room2.addNPC(branStark);
        room2.addNPC(girl);
        room3.addNPC(tom);
        room5.addNPC(trollMan);
        room4.addNPC(cantSee);
        // Initializing player and their starting point
        Player player = new Player(room1); // Placing player in room1

        // User Interface
        boolean quit = false;
        Scanner in = new Scanner(System.in); //Scanner for input
        int menuItem;

        do {
            System.out.println("\nWhat do you want to do?  (-1: leave game)");
            System.out.println("  (0) Spectate the environment");
            System.out.println("  (1) Look for doors");
            System.out.println("  (2) Look for living entities satisfying the seven vital functions");
            System.out.print("-> ");
            menuItem = in.nextInt();

            switch (menuItem) {
                case 0:
                    player.getCurrentRoom().inspect();
                    break;
                case 1:
                    player.getCurrentRoom().interactWithDoors(player);
                    break;
                case 2:
                    player.getCurrentRoom().interactWithNPCs(player);
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