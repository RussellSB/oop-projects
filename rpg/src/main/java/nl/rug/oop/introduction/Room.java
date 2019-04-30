package nl.rug.oop.introduction;

public class Room{

    //Attributes
    private String color;
    private String description;

    //Constructor
    public Room(String color){
        this.color = color;
    }
    //Getters and Setters
    public void setColor(String color){
        this.color = color;
    }
    public String getColor(){
        return this.color;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    //Other methods
    public void inspected(String description){
        System.out.println(description);
    }
    
}