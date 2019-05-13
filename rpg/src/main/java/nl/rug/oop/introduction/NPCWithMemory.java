package nl.rug.oop.introduction;

abstract class NPCWithMemory extends NPC {
    // Attributes
    private boolean metBefore = false;


    // Constructor
    NPCWithMemory(String description, String response) {
        super(description, response);
    }


    // Getters and Setters
    void meet() {
        this.metBefore = true;
    }

    boolean metBefore() {
        return this.metBefore;
    }
}
