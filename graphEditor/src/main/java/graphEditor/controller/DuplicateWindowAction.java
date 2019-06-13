package graphEditor.controller;

import graphEditor.model.GraphModel;
import graphEditor.view.GraphWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Represents the Duplicate Window action.
 */
public class DuplicateWindowAction extends AbstractAction {
    private GraphModel graph;

    /**
     * Creates the Duplicate Window action.
     */
    DuplicateWindowAction(GraphModel graph) {
        super("Duplicate Window");
        this.graph = graph;
    }

    /**
     * Duplicates the window.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new GraphWindow(graph);
    }
}
