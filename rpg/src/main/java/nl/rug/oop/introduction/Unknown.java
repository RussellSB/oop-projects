package nl.rug.oop.introduction;

public class Unknown extends NPC{

    public Unknown(String description, String response) {
        super(description, response);
    }

    //Other methods
    public void interact(Player player) {
        if(this.getMetBefore()==true){
            System.out.println("You fear the unknown but you remember that it also fears you too. You hesitate whether you should interact with it yet another time. You're careless though, you do it anyways.");
            System.out.println("You feel a burning sensation in your leg. You hear a scream. You feel your leg melting. You arms detach. It was more than just a flesh wound. The creature atacked you.");
            player.affectHealth(-100);
        }else{
            System.out.println("Though this entitity's breathing frightens you, a philosophical thought concerns you. What if this creature is just as scared of you as you are of it?");
            this.setMetBeforeTrue();
            System.out.println(this.getResponse());
        }
    }

}