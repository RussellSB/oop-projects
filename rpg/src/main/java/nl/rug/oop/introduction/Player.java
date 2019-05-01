package nl.rug.oop.introduction;

public class Player {

public static final int MAX_HEALTH = 100;

    // Attributes
    private Room currentRoom;
    private int health=MAX_HEALTH;

    // Constructor
    public Player(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    // Getters and Setters
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public int getHealth(){
        return this.health;
    }

    public void affectHealth(int points){
        if(this.health+points >= 100){
            this.health = MAX_HEALTH;
            System.out.println("Health maxed out! You're full of life and love.");
        }else{
            this.health = this.health+points;
        }
        
    }

    // Other methods
    public boolean stillAlive(){ 
        if(this.health<0){
            return false;
        }else{
            return true;
        }
    }
}