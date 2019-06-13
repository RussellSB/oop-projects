package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Represents the Add Vertex action.
 */
public class AddVertexAction extends AbstractAction {
    private GraphModel graph;

    /**
     * Creates the Add Vertex action.
     */
    AddVertexAction(GraphModel graph) {
        super("Add Vertex");
        this.graph = graph;
    }

    /**
     * Adds a new vertex to the graph.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        graph.getUndoManager().addEdit(new UndoableAddVertexEdit(graph));
    }
}
