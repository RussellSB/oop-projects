package nl.rug.oop.introduction;

public class Unknown extends NPCWithMemory {
    // Attributes
    private static final int affectHealthAmount = -100;


    // Constructor
    Unknown(String description, String response) {
        super(description, response);
    }


    // Other methods
    @Override
    public void interact(Player player) {
        if (this.metBefore()) {
            System.out.println("You fear the unknown but you remember that it also fears you too. You hesitate whether you should interact with it yet another time. You're careless though, you do it anyways.");
            System.out.println("You feel a burning sensation in your leg. You hear a scream. You feel your leg melting. You arms detach. It was more than just a flesh wound. The creature attacked you.");
            player.affectHealth(affectHealthAmount);
        } else {
            System.out.println("Though this entity's breathing frightens you, a philosophical thought concerns you. What if this creature is just as scared of you as you are of it?");
            this.meet();
            System.out.println(this.getResponse());
        }
    }

}