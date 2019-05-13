package nl.rug.oop.introduction;

class Door extends Inspectable implements Interactable {
    // Attributes
    private Room behindRoom;


    // Constructor
    Door(String description, Room behindRoom) {
        super(description);
        this.behindRoom = behindRoom;
    }


    // Other methods
    @Override
    public void interact(Player player) {
        player.setCurrentRoom(this.behindRoom);
        this.behindRoom.interact(player);
    }
}