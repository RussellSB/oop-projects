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
        //TODO: Print something here
        player.setCurrentRoom(destinationRoom);
    }
}
