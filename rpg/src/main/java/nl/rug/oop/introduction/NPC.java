package nl.rug.oop.introduction;

public abstract class NPC extends Inspectable implements Interactable{
    // Attributes
    private boolean metBefore = false;
    private String response; // variable string to be returned when the user approaches NPC

    // Constructor
    public NPC(String description, String response) {
        super(description);
        this.response = response;
    }

    //Getters and Setters
    public void setMetBeforeTrue() {
        this.metBefore = true;
    }
    public boolean getMetBefore() {
        return this.metBefore;
    }
    public String getResponse(){
        return this.response;
    }
    public void setResponse(String response) {
        this.response = response;
    }

    // Other methods

}