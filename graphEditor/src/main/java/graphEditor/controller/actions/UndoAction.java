package graphEditor.controller.actions;

import graphEditor.model.GraphModel;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents the Undo action.
 */
public class UndoAction extends AbstractAction implements Observer {
    private UndoManager undoManager;

    /**
     * Creates the Undo action.
     */
    public UndoAction(GraphModel graph) {
        super("Undo");

        this.undoManager = graph.getUndoManager();

        graph.addObserver(this);

        setEnabled(false);
    }

    /**
     * Undo.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        undoManager.undo();
    }

    /**
     * Checks the undoManager state to determine if this action can be enabled or not.
     */
    @Override
    public void update(Observable o, Object arg) {
        setEnabled(undoManager.canUndo());
    }
}
