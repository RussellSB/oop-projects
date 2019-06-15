package graphEditor.controller.actions;

import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents the Undo action.
 */
public class UndoAction extends AbstractAction implements Observer {
    private GraphModel graph;

    /**
     * Creates the Undo action.
     */
    public UndoAction(GraphModel graph) {
        super("Undo");

        this.graph = graph;

        graph.addObserver(this);
    }

    /**
     * Undo.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        graph.undo();
    }

    /**
     * Checks the undoManager state to determine if this action can be enabled or not.
     */
    @Override
    public void update(Observable o, Object arg) {
        setEnabled(graph.getUndoManager().canUndo());
    }
}
