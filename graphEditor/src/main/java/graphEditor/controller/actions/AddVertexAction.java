package graphEditor.controller.actions;

import graphEditor.controller.undoableEdits.AddVertexUndoableEdit;
import graphEditor.model.GraphModel;
import graphEditor.view.GraphPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Represents the Add Vertex action.
 */
public class AddVertexAction extends AbstractAction {
    private GraphModel graph;
    private GraphPanel panel;

    /**
     * Creates the Add Vertex action.
     */
    public AddVertexAction(GraphModel graph, GraphPanel panel) {
        super("Add Vertex");

        this.graph = graph;
        this.panel = panel;
    }

    /**
     * Adds a new vertex to the graph.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the position of the top left corner inside the visible rectangle of the panel:
        int x = (int) panel.getVisibleRect().getX() + GraphPanel.BORDER_THICKNESS + 10;
        int y = (int) panel.getVisibleRect().getY() + GraphPanel.BORDER_THICKNESS + 10;

        graph.addUndoableEdit(new AddVertexUndoableEdit(graph, x, y));
    }
}
