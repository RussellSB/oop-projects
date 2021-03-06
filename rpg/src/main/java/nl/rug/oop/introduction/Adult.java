package nl.rug.oop.introduction;

class Adult extends NPCWithMemory {
    // Attributes
    private static final int affectHealthAmount = -20;


    // Constructor
    Adult(String description, String response) {
        super(description, response);
    }


    // Other methods
    @Override
    public void interact(Player player) {
        if (this.metBefore()) {
            System.out.println("Actually no. You feel overly-anxious that you might annoy them. You recount that adults lead busy lives.");
            System.out.println("You slap yourself out of indecisiveness.");
            player.affectHealth(affectHealthAmount);
        } else {
            System.out.println(this.getResponse());
            this.meet();
        }
    }
}