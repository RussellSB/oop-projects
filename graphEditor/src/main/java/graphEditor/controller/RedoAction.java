package graphEditor.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Represents the Redo action.
 */
public class RedoAction extends AbstractAction {
    /**
     * Creates the Redo action.
     */
    RedoAction(){
        super("Redo");
        setEnabled(false); // TODO: Only available if the previous operation was an undo.
    }

    /**
     * Redo
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand()); // TODO: Remove when implemented.
        // TODO: Implement.
    }
}
