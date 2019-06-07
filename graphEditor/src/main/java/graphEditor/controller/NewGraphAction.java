package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Represents the New Graph action.
 */
public class NewGraphAction extends AbstractAction {
    private GraphModel graph;

    /**
     * Creates the New Graph action.
     */
    NewGraphAction(GraphModel graph) {
        super("New Graph");
        this.graph = graph;
    }

    /**
     * Creates a new graph.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        graph.reset();
    }
}
