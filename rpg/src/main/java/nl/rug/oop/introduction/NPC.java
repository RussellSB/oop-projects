package nl.rug.oop.introduction;

abstract class NPC extends Inspectable implements Interactable {
    // Attributes
    private String response; // String to be returned when the player interacts with NPC


    // Constructor
    NPC(String description, String response) {
        super(description);
        this.response = response;
    }


    // Getters and Setters
    String getResponse(){
        return this.response;
    }
}