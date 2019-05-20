package cardGame.controller;

import cardGame.game.Snap;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents an action made to draw a card.
 */
public class SnapAction extends AbstractAction implements Observer {

    private Snap snap;

    /**
     * Creates a new action to draw a card.
     */
    SnapAction(Snap snap) {
        super("SNAP! [spacebar]");
        this.snap = snap;
        snap.addObserver(this);
        fixEnabled();
    }

    /**
     * Check if the action can be performed.
     */
    private void fixEnabled() {
        // TODO: This will need to be adapted if we add the extra pile
        if (snap.getPlayerUpPile().isEmpty() || snap.getNpcUpPile().isEmpty())
            setEnabled(false);
        else
            setEnabled(true);
    }

    /**
     * Snap!
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        snap.playerSnaps();
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
