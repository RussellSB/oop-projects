package nl.rug.oop.introduction;

public class Door extends Inspectable implements Interactable {
    // Attributes
    private Room behindRoom;


    // Constructor
    public Door(String description, Room behindRoom) {
        super(description);
        this.behindRoom = behindRoom;
    }


    //Getters and Setters
    public Room getBehindRoom() {
        return this.behindRoom;
    }


    // Other methods
    @Override
    public void interact(Player player) {
        player.setCurrentRoom(this.getBehindRoom());
    }

}