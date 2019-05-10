package nl.rug.oop.introduction;

public class PortalRoom extends Room {
    // Attributes
    private Room destinationRoom;


    // Constructor
    PortalRoom(String description, Room destinationRoom) {
        super(description);
        this.destinationRoom = destinationRoom;
    }


    // Other methods
    @Override
    public void interact(Player player) {
        System.out.println("a room with an excessive amount of green particles in the middle.\n Could it be? A portal?\nA loud bang goes off. \n You feel uncomfortable as your ears ring.\nIt's growing!\n You try run away but the door you came in from has disappeared!\n You scream as the portal swallows you and the room you stand in.\nEverything goes dark until....\n...\n..\n.\nYou open your eyes.");
        player.setCurrentRoom(destinationRoom);
    }
}
