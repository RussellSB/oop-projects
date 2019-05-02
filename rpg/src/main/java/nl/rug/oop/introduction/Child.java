package nl.rug.oop.introduction;

public class Child extends NPC {

    public Child(String description, String response) {
        super(description, response);
    }

    //Other methods
    public void interact(Player player) {
        System.out.println("The child seems to be forgetful...");
        System.out.println(this.getResponse());
    }
}