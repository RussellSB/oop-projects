package cardGame.controller;

import cardGame.game.Snap;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents an action made to perform a snap.
 */
public class SnapAction extends AbstractAction implements Observer {

    private Snap snap;

    /**
     * Creates a new action to perform a snap.
     */
    SnapAction(Snap snap) {
        super("SNAP!");
        this.snap = snap;
        snap.addObserver(this);
        fixEnabled();
    }

    /**
     * Check if the action can be performed.
     */
    private void fixEnabled() {
        if (snap.matchDetected())
            setEnabled(true);
        else
            setEnabled(false);
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
