package nl.rug.oop.introduction;

public abstract class NPCwithMemory extends NPC {
    // Attributes
    private boolean metBefore = false;

    public NPCwithMemory(String description, String response) {
        super(description, response);
    }

    public void setMetBeforeTrue() {
        this.metBefore = true;
    }

    public boolean getMetBefore() {
        return this.metBefore;
    }
}
