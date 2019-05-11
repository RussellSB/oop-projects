package nl.rug.oop.introduction;

class HeavyDoor extends Door {
    // Attributes
    private static final int MIN_PUSHES = 3;
    private int n_pushes = 0;


    // Constructor
    HeavyDoor(String description, Room behindRoom) {
        super(description, behindRoom);
    }


    // Other methods
    @Override
    public void interact(Player player) {
        this.n_pushes++;
        if (this.n_pushes >= MIN_PUSHES) {
            System.out.println("You pull with all your might. You slip through, slightly taking damage as the door closes behind you on your foot.\nOuch\nYou pull your foot out, aggravated.");
            player.affectHealth(-10);
            super.interact(player);
        } else {
            System.out.println("You pull with all your might. It opens by an inch but your arms now ache more. You're fussy and lose a little motivation to live\nYou could keep trying though.");
            player.affectHealth(-5);
        }
    }
}
