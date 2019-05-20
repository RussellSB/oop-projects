package cardGame.controller;

import cardGame.game.Snap;

import javax.swing.JMenuBar;

/**
 * Panel with the buttons for the draw-class controllers
 */
public class ButtonBar extends JMenuBar {

    /**
     * Create a new buttonpanel with all the necessary buttons
     */
    public ButtonBar(Snap snap) {
        add(new DrawButton(snap));
        add(new ResetButton(snap));
        add(new SnapButton(snap));
    }

}
