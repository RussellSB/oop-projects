package graphEditor.controller.actions;

import graphEditor.controller.CopyPasteManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents the Cut action.
 */
public class CutAction extends AbstractAction implements Observer {
    private CopyPasteManager copyPasteManager;

    /**
     * Creates the Cut action.
     */
    public CutAction(CopyPasteManager copyPasteManager) {
        super("Cut");

        this.copyPasteManager = copyPasteManager;

        copyPasteManager.addObserver(this);
    }

    /**
     * Tells the CopyPasteManager to cut.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        copyPasteManager.cut();
    }

    /**
     * Checks the graph state to determine if this action can be enabled or not.
     * The Cut action is only available if at least one vertex is selected.
     */
    @Override
    public void update(Observable o, Object arg) {
        setEnabled(copyPasteManager.readyToCopyCut());
    }
}
