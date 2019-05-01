package nl.rug.oop.introduction;

public class Player {

public static final int MAX_HEALTH = 100;
public static final int MIN_HEALTH = 0;

    // Attributes
    private Room currentRoom;
    private int hp=MAX_HEALTH;

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
        return this.hp;
    }

    public void affectHealth(int points){
        if(this.hp+points >= MAX_HEALTH){
            this.hp = MAX_HEALTH;
            System.out.println("Health maxed out! You're full of life and love.");
        }else{
            this.hp = this.hp+points;
        }
    }

    // Other methods
    public boolean stillAlive(){ 
        if(this.hp<=MIN_HEALTH){
            return false;
        }else{
            return true;
        }
    }
}