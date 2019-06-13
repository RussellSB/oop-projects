package graphEditor.controller;

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
    UndoAction(GraphModel graph) {
        super("Undo");
        this.graph = graph;
        setEnabled(false);
        graph.addObserver(this); //TODO: make it update without having to click panel
    }

    /**
     * Undo.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        graph.getUndoManager().undo();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (graph.getUndoManager().canUndo()) {
            setEnabled(true);
        } else {
            setEnabled(false);
        }
    }
}
