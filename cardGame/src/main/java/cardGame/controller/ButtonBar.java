package cardGame.controller;

import cardGame.game.Snap;

import javax.swing.*;

/**
 * Panel with the buttons for the snap-class controllers.
 */
public class ButtonBar extends JMenuBar {

    /**
     * Create a new buttonpanel with all the necessary buttons.
     */
    public ButtonBar(Snap snap) {
        add(new InstructionButton());
        add(new DrawButton(snap));
        add(new RestartButton(snap));
        add(new SnapButton(snap));
    }

}
