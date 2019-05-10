package nl.rug.oop.introduction;

import java.io.Serializable;

public class Door extends Inspectable implements Interactable, Serializable {
    // Attributes
    private static final long serialVersionUID = 1L;
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