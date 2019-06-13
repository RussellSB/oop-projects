package graphEditor.controller.actions;

import graphEditor.controller.undoableEdits.AddVertexUndoableEdit;
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
    public AddVertexAction(GraphModel graph) {
        super("Add Vertex");
        this.graph = graph;
    }

    /**
     * Adds a new vertex to the graph.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        graph.getUndoManager().addEdit(new AddVertexUndoableEdit(graph));
    }
}
