package cardGame.controller;

import cardGame.game.Snap;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Represents an action made to restart the game. Although useless on a new
 * game, this action is always available.
 */
public class RestartAction extends AbstractAction {

    private Snap snap;

    /**
     * Creates a new action to restart the game.
     */
    RestartAction(Snap snap) {
        super("Restart game");
        this.snap = snap;
    }

    /**
     * Restarts the game.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        snap.restart();
    }

}
