package graphEditor.controller.actions;

import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents the Redo action.
 */
public class RedoAction extends AbstractAction implements Observer {
    private GraphModel graph;

    /**
     * Creates the Redo action.
     */
    public RedoAction(GraphModel graph) {
        super("Redo");
        this.graph = graph;
        graph.addObserver(this);
    }

    /**
     * Redo
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        graph.redo();
    }

    /**
     * Checks the undoManager state to determine if this action can be enabled or not.
     */
    @Override
    public void update(Observable o, Object arg) {
        setEnabled(graph.getUndoManager().canRedo());
    }
}
