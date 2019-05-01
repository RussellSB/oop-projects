package nl.rug.oop.introduction;

public class Adult extends NPC{

    public Adult(String description, String response) {
        super(description, response);
    }

    //Other methods
    public void interact(Player player) {
        if(this.getMetBefore()==true){
            System.out.println("Actually no. You feel overly-anxious that you might annoy them. You recount that adults lead busy lives.");

        }else{
            System.out.println(this.getResponse());
            this.setMetBeforeTrue();
        }
    }
}