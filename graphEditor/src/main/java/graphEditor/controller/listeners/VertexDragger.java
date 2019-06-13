package graphEditor.controller.listeners;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;
import graphEditor.view.GraphPanel;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class VertexDragger implements MouseInputListener {

    private GraphModel graph;

    private boolean dragSelected = false; // flag that enables dragging
    private List<Point> initialLocations; // for selected vertices to be dragged
    private Point dragStart; // initial point we started dragging from

    public VertexDragger(GraphModel graph, GraphPanel panel) {
        this.graph = graph;
        initialLocations = new ArrayList<>();
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {

        // Check if mouse pressed one of the selected vertices
        for (int i = 0; i < graph.getSelectedVerticesCount(); i++) {
            GraphVertex selectedVertex = graph.getSelectedVertices().get(i);

            if (selectedVertex.intersects(e.getPoint())) {
                dragStart = new Point(e.getX(), e.getY());
                dragSelected = true; // flagged true, enables dragging
                break;
            }
        }

        if (dragSelected) {
            // Initialize integer Lists for storing all initial selected vertex positions
            for (int i = 0; i < graph.getSelectedVerticesCount(); i++) {
                GraphVertex vertex = graph.getSelectedVertices().get(i);
                initialLocations.add(new Point(vertex.getX(), vertex.getY()));
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (dragSelected) {
            initialLocations.clear();
            dragSelected = false;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if (dragSelected) {
            // We drag the selected vertices
            for (int i = 0; i < graph.getSelectedVerticesCount(); i++) {
                GraphVertex vertex = graph.getSelectedVertices().get(i);
                int initX = initialLocations.get(i).x;
                int initY = initialLocations.get(i).y;

                vertex.setLocation(initX + (e.getX() - (int) dragStart.getX()), initY + (e.getY() - (int) dragStart.getY()));
            }
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
