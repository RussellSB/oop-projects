package nl.rug.oop.introduction;

public class Adult extends NPC{

    public Adult(String description, String response) {
        super(description, response);
    }

    //Other methods
    public void interact(Player player) {
        if(this.getMetBefore()==true){
            System.out.println("Actually no. You feel overly-anxious that you might annoy them. The adult might be busy");

        }else{
            System.out.println(this.getResponse());
            this.setMetBeforeTrue();
        }
    }
}