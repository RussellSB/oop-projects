package graphEditor.controller;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;
import graphEditor.view.GraphPanel;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class VertexDragger implements MouseInputListener {

    private GraphModel graph;

    private boolean drag;

    public VertexDragger(GraphModel graph, GraphPanel panel) {
        this.graph = graph;
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // We go trough the vertices list backwards so in case of overlap the vertex on top is dragged.
        for (int i = graph.getVerticesCount() - 1; i >= 0; i--) {
            GraphVertex vertex = graph.getVertices().get(i);

            if (vertex.intersects(e.getPoint()) && graph.isSelected(vertex)) {
                drag = true;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (drag) {
            graph.deselectAll();
            drag = false;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        for (GraphVertex v : graph.getSelectedVertices()) {
            v.setLocation(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
