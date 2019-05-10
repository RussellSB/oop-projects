package nl.rug.oop.introduction;

public class HeavyDoor extends Door {
    // Attributes
    public static final int MIN_PUSHES = 3;
    private int n_pushes = 0;


    // Constructor
    public HeavyDoor(String description, Room behindRoom) {
        super(description, behindRoom);
    }


    // Getters
    public int getN_pushes() {
        return n_pushes;
    }


    // Other methods
    public void increaseNpushes() {
        this.n_pushes ++;
    }

    @Override
    public void interact(Player player) {
        increaseNpushes();
        if(getN_pushes() >= MIN_PUSHES) {
            System.out.println("You pull with all your might. You slip through, slightly taking damage as the door closes behind you on your foot.\nOuch\nYou pull your foot out, aggrevated.");
            affectHealth(player, -10);
            super.interact(player);
        } else {
            System.out.println("You pull with all your might. It opens by an inch but your arms now ache more. You're fussy and lose a little motivation to live\nYou could keep trying though.");
            affectHealth(player, -5);
        }
    }

    public void affectHealth(Player player, int amount) {
        player.affectHealth(amount);
    }
}
