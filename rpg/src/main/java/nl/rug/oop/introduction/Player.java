package nl.rug.oop.introduction;

public class Player {
    // Attributes
    private static final int MAX_HEALTH = 100;
    private static final int MIN_HEALTH = 0;
    private Room currentRoom;
    private int hp=MAX_HEALTH;


    // Constructor
    public Player(Room currentRoom) {
        this.currentRoom = currentRoom;
    }


    // Getters and Setters
    Room getCurrentRoom() {
        return currentRoom;
    }

    void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    int getHealth() {
        return this.hp;
    }


    // Other methods
    void affectHealth(int amount) {
        if(this.hp+amount >= MAX_HEALTH){
            this.hp = MAX_HEALTH;
            System.out.println("Health maxed out! You're full of life and love.");
        } else {
            this.hp = this.hp + amount;
            if(amount > 0)
                System.out.println("You feel your body tingle. You're healed! HP +" + amount);
            else
                System.out.println("You feel your body crumble. You're damaged! HP " + amount);
        }
    }

    boolean stillAlive() {
        return (this.hp >= MIN_HEALTH);
    }
}