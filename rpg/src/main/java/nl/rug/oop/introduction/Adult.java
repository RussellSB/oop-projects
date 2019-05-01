package nl.rug.oop.introduction;

public class Adult extends NPCwithMemory implements HealthModifier {

    public Adult(String description, String response) {
        super(description, response);
    }

    @Override
    public void affectHealth(Player player) {
        player.affectHealth(-20);
    }

    //Other methods
    public void interact(Player player) {
        if(this.getMetBefore()==true) {
            System.out.println("Actually no. You feel overly-anxious that you might annoy them. You recount that adults lead busy lives.");
            System.out.println("You slap yourself out of indeciciveness.");
            affectHealth(player);
        } else {
            System.out.println(this.getResponse());
            this.setMetBeforeTrue();
        }
    }
}