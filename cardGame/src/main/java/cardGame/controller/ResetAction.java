package cardGame.controller;

import cardGame.game.Snap;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Represents an action made to shuffle all cards back into the deck. Although
 * useless on an empty discard pile, this action is always available.
 */
public class ResetAction extends AbstractAction {

    private Snap snap;

    /**
     * Creates a new action to shuffle all cards back into the deck
     */
    public ResetAction(Snap snap) {
        super("Shuffle");
        this.snap = snap;
    }

    /**
     * Draws a card
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        snap.reset();
    }
    
}
