package cardGame.controller;

import cardGame.game.Snap;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents an action made to draw a card.
 */
public class DrawAction extends AbstractAction implements Observer {

    private Snap snap;

    /**
     * Creates a new action to draw a card.
     */
    DrawAction(Snap snap) {
        super("Draw [ALT+D]");
        this.snap = snap;
        snap.addObserver(this);
        fixEnabled();
    }

    /**
     * Check if the action can be performed.
     */
    private void fixEnabled() {
        if (snap.gameIsFinished())
            setEnabled(false);
        else
            setEnabled(true);
    }

    /**
     * Draws a card
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        snap.playerMoves();
    }

    /**
     * Since availability of this action depends on the state of the
     * resources it itself depends on, this action verifies
     * after every update of draw if it can still be performed.
     */
    @Override
    public void update(Observable observed, Object message) {
        fixEnabled();
    }
}
