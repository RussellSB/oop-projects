package graphEditor.controller.actions;

import graphEditor.controller.CopyPasteManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents the Paste action.
 */
public class PasteAction extends AbstractAction implements Observer {
    private CopyPasteManager copyPasteManager;

    /**
     * Creates the Paste action.
     */
    public PasteAction(CopyPasteManager copyPasteManager) {
        super("Paste");

        this.copyPasteManager = copyPasteManager;

        copyPasteManager.addObserver(this);
    }

    /**
     * Tells the CopyPasteManager to paste.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        copyPasteManager.paste();
    }

    /**
     * Checks the graph state to determine if this action can be enabled or not.
     * The Paste action is only available if something was copied/cut previously.
     */
    @Override
    public void update(Observable o, Object arg) {
        setEnabled(copyPasteManager.readyToPaste());
    }
}
