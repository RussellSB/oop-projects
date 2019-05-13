package nl.rug.oop.introduction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


class GameSession implements Serializable {
    // Attributes
    private static final long serialVersionUID = 1L;
    private Player player;
    private List<Room> rooms = new ArrayList<>();


    // Getters and Setters
    Player getPlayer(){
        return this.player;
    }


    // Other Methods
    void initWorld1() {
        // Initializing rooms
        Room room1 = new HealthModifierRoom("a dark ominous room with a space themed door.", -10);
        Room room2 = new HealthModifierRoom("an icey cold room with a space themed door and another with a game of thrones reference.", -10);
        Room room3 = new HealthModifierRoom("wait a second, this isn't just any room - this is the Room! You see your somewhat typically average American man at the back of the room.", +10);
        Room room4 = new HealthModifierRoom("but you don't. Feeling scared and frightened, you look around you. It's pitch black! You hear heavy breathing. You can't shake the feeling of a somewhat nostalgic deja vu.", -10);
        Room room5 = new HealthModifierRoom("a beige texture covering the room's interior. There appears to be an overly optimistic Russian man at the back of the room.", +10);
        Room room6 = new HealthModifierRoom("a room where everything's gold! There's even a treasure chest continuously spewing rainbows from its sides! If you're truly materialistic then congrats you won! Leave the game to go spend your highly-valued imaginary gold!", +10);
        Room room7 = new PortalRoom("a room with a portal. The player can't see this because he's too busy getting teleported to inspect", room1);

        // Initializing doors
        Door door1_1 = new Door("a door with an image of a blackhole imprinted on it.", room2);
        Door door2_1 = new Door("a door with the back of a blackhole imprinted on it.", room1); // go back
        Door door2_2 = new HeavyDoor("a door with a beefy possessed fellow pushing at it, struggling to hold the door.", room3);
        Door door3_1 = new HeavyDoor("a door with tens of blue zombie-like creatures pushing at it. This hardly intimidates you. You're already dead inside.", room2); // go back
        Door door3_2 = new Door("a door with the words YOURE TEARING ME APART LISA, written in blood.", room4);
        Door door3_3 = new Door("a door with a large troll face imprinted on it. It makes you cringe, reminding you of your memes obsession in the early 2010s.", room5);
        Door door3_4 = new Door("a door with a large crown painted in gold and glittered words reading PLS DO NOT DISTR0B.", room6);
        Door door4_1 = new Door("actually you don't. It's too dark to see but feeling the door's surface it feels particularly familiar.", room4);
        Door door5_1 = new Door("a door with the back of the troll face imprinted on it. It still makes you cringe, making you look back on when memes were worse.", room3); // go back
        Door door5_2 = new Door("a door looking similar to the gold one you saw earlier, with diamond blue words reading BACK OFF PLS N THX.", room6);
        Door door5_3 = new Door("a door with a hand drawn cake on it. Underneath it you read the words \"THE CAKE IS A LIE\"", room7);

        // Adding doors to rooms
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
        room5.addDoor(door5_3);

        // Initializing NPCs with their descriptions and responses
        Adult hodor = new Adult("A beefy man repeatedly yelling Hodor. His eyes are pearl white.", "\"HODOR!\"");
        Child branStark = new Child("A little boy in a wheelchair. His eyes are also pearl white.", "He ignores you. You assume he might be mind-controlling the beefy man.");
        Child girl = new Child("A girl concerned about the door the beefy man is pushing. She seems exhausted.", "\"My brother died for the three eyed-raven, he did.\"");
        Adult tom = new Adult("A man who doesn't appear as American as he initially seemed. He has a water bottle in his hand.", "\"I did not hit her, I did not! Oh.... hi Mark.\"");
        Adult trollMan = new Adult("A man in a classic beige suit. His smile is enchanting.", "He sings and dances to music that starts playing from the ceiling\n\"trolololololololololololololoolololo\"");
        Unknown cantSee = new Unknown("sound coming from the corner of the room, far right of you", "The breathing stops momentarily.\n\"We're trapped! We're trapped! You're a monster! The 2012 apocalypse is real! I can't escape! The door's all the same! The door's all the same!\"");

        // Adding NPCs to rooms
        room2.addNPC(hodor);
        room2.addNPC(branStark);
        room2.addNPC(girl);
        room3.addNPC(tom);
        room5.addNPC(trollMan);
        room4.addNPC(cantSee);

        // Moving Rooms to the ArrayList
        this.rooms.add(room1);
        this.rooms.add(room2);
        this.rooms.add(room3);
        this.rooms.add(room4);
        this.rooms.add(room5);
        this.rooms.add(room6);
        this.rooms.add(room7);

        // Initializing player with its starting room
        this.player = new Player(this.rooms.get(0)); // Placing player in room1
    }
}
