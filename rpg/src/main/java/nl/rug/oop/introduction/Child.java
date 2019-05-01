package nl.rug.oop.introduction;

public class Child extends NPC{

    public Child(String description, String response) {
        super(description, response);
    }

    //Other methods
    public void interact(Player player) {
        if(this.getMetBefore()==true){
            System.out.println("You feel comfortable interacting again. Though you remind yourself that kids are easily entertained by repetitive behaviour");
        }else{
            this.setMetBeforeTrue();
        }
        System.out.println(this.getResponse());
    }
}