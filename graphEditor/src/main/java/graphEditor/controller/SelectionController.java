package graphEditor.controller;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;
import graphEditor.view.GraphPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * TODO: Comments for the class & methods.
 */
public class SelectionController implements MouseListener {
    private GraphModel graph;

    public SelectionController(GraphModel graph, GraphPanel panel) {
        this.graph = graph;
        panel.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!vertexSelection(e))
            if (!edgeSelection(e))
                if (!vertexRenaming(e))
                    graph.deselectAll();
    }

    private boolean vertexSelection(MouseEvent e) {
        Point clickCoord = new Point(e.getX(), e.getY());

        // We go trough the vertices list backwards so in case of overlap the vertex on top is selected.
        for (int i = graph.getVerticesCount() - 1; i >= 0; i--) {
            GraphVertex vertex = graph.getVertices().get(i);

            if (vertex.getRectangle().contains(clickCoord)) {
                // TODO: Unless ctrl is kept pressed:
                graph.deselectAll();

                graph.select(vertex);

                return true;
            }
        }

        return false;
    }

    private boolean edgeSelection(MouseEvent e) {
        // TODO: Implement with imaginary rectangle surrounding the line.
        return false;
    }

    private boolean vertexRenaming(MouseEvent e) {
        // TODO: Implement with double click.
        return false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
