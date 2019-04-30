package nl.rug.oop.introduction;

abstract public class Inspectable {
    // Attributes
    private String description;


    // Constructors
    public Inspectable(String description) {
        this.description = description;
    }


    // Getters and setters
    public String getDescription() {
        return description;
    }


    // Other methods
    public void inspect() {
        System.out.println(this.getDescription());
    }
}
