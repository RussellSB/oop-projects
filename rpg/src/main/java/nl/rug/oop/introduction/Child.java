package nl.rug.oop.introduction;

class Child extends NPC {
    // Constructor
    Child(String description, String response) {
        super(description, response);
    }


    // Other methods
    @Override
    public void interact(Player player) {
        System.out.println("The child seems to be forgetful...");
        System.out.println(this.getResponse());
    }
}