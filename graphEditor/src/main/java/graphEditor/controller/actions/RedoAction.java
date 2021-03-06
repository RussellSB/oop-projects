package graphEditor.controller.actions;

import graphEditor.model.GraphModel;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents the Redo action.
 */
public class RedoAction extends AbstractAction implements Observer {
    private UndoManager undoManager;

    /**
     * Creates the Redo action.
     */
    public RedoAction(GraphModel graph) {
        super("Redo");

        this.undoManager = graph.getUndoManager();

        graph.addObserver(this);

        setEnabled(false);
    }

    /**
     * Redo.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        undoManager.redo();
    }

    /**
     * Checks the undoManager state to determine if this action can be enabled or not.
     */
    @Override
    public void update(Observable o, Object arg) {
        setEnabled(undoManager.canRedo());
    }
}
