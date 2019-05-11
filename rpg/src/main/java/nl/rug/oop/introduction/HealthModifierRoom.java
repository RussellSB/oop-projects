package nl.rug.oop.introduction;

class HealthModifierRoom extends Room {
    // Attributes
    private int affectHealthAmount;


    // Constructor
    HealthModifierRoom(String description, int affectHealthAmount) {
        super(description);
        this.affectHealthAmount = affectHealthAmount;
    }


    // Other methods
    @Override
    public void interact(Player player) {
        if (affectHealthAmount > 0)
            System.out.println("This room makes you feel safe. You feel your spirits lifting.");
        else
            System.out.println("This room makes you feel uneasy. You slightly lose your motivation to live.");

        player.affectHealth(affectHealthAmount);
    }
}
