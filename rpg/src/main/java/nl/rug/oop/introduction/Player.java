package nl.rug.oop.introduction;

public class Player {
    // Attributes
    private Room currentRoom;

    // Constructor
    public Player(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    // Getters and Setters
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }


    // Other methods
}