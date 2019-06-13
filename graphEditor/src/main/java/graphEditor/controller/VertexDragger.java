package graphEditor.controller;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;
import graphEditor.view.GraphPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Vertex Dragger: Allows to drag vertices.
 */
public class VertexDragger implements MouseListener, MouseMotionListener {
    private GraphModel graph;
    private boolean dragging = false; // Flag that enables dragging.
    private Point dragStartingPoint; // Initial dragging point.
    private List<Point> initialLocations; // Initial location of the dragged vertices.

    /**
     * Creates the Vertex Dragger.
     */
    public VertexDragger(GraphModel graph, GraphPanel panel) {
        this.graph = graph;
        this.initialLocations = new ArrayList<>();

        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
    }

    /**
     * Calls all the actions that should be performed when the mouse is pressed.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        checkDragging(e);

        if (dragging)
            startDrag(e);
    }

    /**
     * Calls all the actions that should be performed when the mouse is released.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (dragging)
            finishDrag(e);
    }

    /**
     * Calls all the actions that should be performed when the mouse is dragged.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragging)
            drag(e);
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

    /**
     * Checks if the graph has entered dragging mode.
     */
    private void checkDragging(MouseEvent e) {
        // Dragging starts if the mouse is pressed on top of one of the selected vertices.
        for (GraphVertex vertex : graph.getSelectedVertices())
            if (vertex.intersects(e.getPoint())) {
                dragging = true;
                return;
            }

        dragging = false;
    }

    /**
     * Prepares the VertexDragger to start dragging.
     */
    private void startDrag(MouseEvent e) {
        // Save the starting point of the drag:
        dragStartingPoint = new Point(e.getX(), e.getY());

        // Save the initial position of the selected vertices:
        for (int i = 0; i < graph.getSelectedVerticesCount(); i++) {
            GraphVertex vertex = graph.getSelectedVertices().get(i);
            initialLocations.add(new Point(vertex.getX(), vertex.getY()));
        }
    }

    /**
     * Drag vertices to follow the mouse.
     */
    private void drag(MouseEvent e) {
        // We drag the selected vertices
        for (int i = 0; i < graph.getSelectedVerticesCount(); i++) {
            GraphVertex vertex = graph.getSelectedVertices().get(i);

            // Initial location:
            int initX = initialLocations.get(i).x;
            int initY = initialLocations.get(i).y;

            // New location relative to the mouse:
            int newX = initX + (e.getX() - (int) dragStartingPoint.getX());
            int newY = initY + (e.getY() - (int) dragStartingPoint.getY());

            vertex.setLocation(newX, newY);
        }
    }

    /**
     * Checks if the drag can be performed and finishes it (vertices are not allowed to be dragged out of the panel).
     */
    private void finishDrag(MouseEvent e) {
        // TODO: Drag one last time as an Undoable operation!!!
        // TODO: Check that the vertices are inside the panel!!!
        initialLocations.clear();
        dragging = false;
    }
}
