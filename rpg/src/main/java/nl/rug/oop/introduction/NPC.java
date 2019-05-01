package nl.rug.oop.introduction;

public abstract class NPC extends Inspectable implements Interactable {
    // Attributes
    private boolean metBefore = false;
    private Room currentRoom; // the current room or, atleast what the NPC beleives is their current room
    private String response; // variable string to be returned when the user approaches NPC

    // Constructor
    public NPC(String description, String response, Room currentRoom) {
        super(description);
        this.currentRoom = currentRoom;
        this.response = response;
    }

    //Getters and Setters
    public boolean getMetBefore() {
        return this.metBefore;
    }
    public Room getCurrentRoom() {
        return currentRoom;
    }
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
    public String getResponse(){
        return this.response;
    }
    public void setResponse(String response) {
        this.response = response;
    }

    // Other methods
    @Override
    public void interact(Player player) {
        if(this.metBefore==true){
            System.out.println("Actually no. You feel overly-anxious that you might annoy them.");
            return;
        }else{
            System.out.println(response);
            this.metBefore = true;
        }
    }

}