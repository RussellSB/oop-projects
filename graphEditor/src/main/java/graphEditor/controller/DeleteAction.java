package graphEditor.controller;

import graphEditor.model.GraphEdge;
import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents the Delete action.
 */
public class DeleteAction extends AbstractAction implements Observer {
    private GraphModel graph;

    /**
     * Creates the Delete action.
     */
    DeleteAction(GraphModel graph) {
        super("Delete");
        this.graph = graph;
        graph.addObserver(this);
        setEnabled(false);
    }

    /**
     * Deletes the selected items (whether they are vertices or edges).
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int i;

        // Delete vertices
        List<GraphVertex> vertices = graph.getVertices();
        i = 0;
        while (i < vertices.size())
            if (vertices.get(i).isSelected())
                graph.removeVertex(vertices.get(i));
            else
                i++;

        // Delete edges
        List<GraphEdge> edges = graph.getEdges();
        i = 0;
        while (i < edges.size())
            if (edges.get(i).isSelected())
                graph.removeEdge(edges.get(i));
            else
                i++;
    }

    /**
     * Checks the graph state to determine if this action can be enabled or not.
     * The Delete action is only available if at least one item is selected.
     */
    @Override
    public void update(Observable o, Object arg) {
        for (GraphVertex vertex : graph.getVertices())
            if (vertex.isSelected()) {
                setEnabled(true);
                return;
            }

        for (GraphEdge edge : graph.getEdges())
            if (edge.isSelected()) {
                setEnabled(true);
                return;
            }

        setEnabled(false);
    }
}
