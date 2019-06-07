package graphEditor.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Represents the Undo action.
 */
public class UndoAction extends AbstractAction {
    /**
     * Creates the Undo action.
     */
    UndoAction(){
        super("Undo");
        setEnabled(false); // TODO: Only available if there is a previous operation.
    }

    /**
     * Undo.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand()); // TODO: Remove when implemented.
        // TODO: Implement.
    }
}
