package graphEditor.controller;

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
    RedoAction(GraphModel graph) {
        super("Redo");
        this.graph = graph;
        setEnabled(false);
        graph.addObserver(this); //TODO: make it update without having to click panel
    }

    /**
     * Redo
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        graph.getUndoManager().redo();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (graph.getUndoManager().canRedo()) {
            setEnabled(true);
        } else {
            setEnabled(false);
        }
    }
}
