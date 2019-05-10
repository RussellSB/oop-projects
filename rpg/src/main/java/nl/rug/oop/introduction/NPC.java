package nl.rug.oop.introduction;

abstract class NPC extends Inspectable implements Interactable {
    // Attributes
    private String response; // variable string to be returned when the user approaches NPC


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