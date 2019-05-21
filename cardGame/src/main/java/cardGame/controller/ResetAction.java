package cardGame.controller;

import cardGame.game.Snap;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Represents an action made to shuffle all cards back into the deck. Although
 * useless on an empty pile, this action is always available.
 */
public class ResetAction extends AbstractAction {

    private Snap snap;

    /**
     * Creates a new action to shuffle all cards back into the deck
     */
    ResetAction(Snap snap) {
        super("Restart game [ALT+R]");
        this.snap = snap;
    }

    /**
     * Resets the game
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        snap.reset();
    }

}
