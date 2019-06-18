package graphEditor.controller.listeners;

import graphEditor.controller.undoableEdits.VertexDragUndoableEdit;
import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;
import graphEditor.view.GraphPanel;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Vertex Dragger: Allows to drag vertices.
 */
public class VertexDragger implements MouseInputListener {
    private GraphModel graph;
    private boolean readyToDrag; // Flag that enables dragging.
    private boolean dragging; // Flag that indicates the dragging has started.
    private Point dragStartingPoint; // Initial point of the mouse when the drag starts.
    private List<Point> initialLocations; // Initial location of the dragged vertices.

    /**
     * Creates the Vertex Dragger.
     */
    public VertexDragger(GraphModel graph, GraphPanel panel) {
        this.graph = graph;

        this.initialLocations = new ArrayList<>();
        this.readyToDrag = false;
        this.dragging = false;

        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
    }

    /**
     * Calls all the actions that should be performed when the mouse is pressed.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        checkDragging(e);
    }

    /**
     * Calls all the actions that should be performed while the mouse is being dragged.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (readyToDrag && !dragging)
            startDrag(e);

        if (dragging)
            drag(e);
    }

    /**
     * Calls all the actions that should be performed when the mouse is released.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (dragging)
            finishDrag();
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
     * Checks if the graph ia ready to drag.
     */
    private void checkDragging(MouseEvent e) {
        // Dragging starts if the mouse is pressed on top of one of the selected vertices.
        for (GraphVertex vertex : graph.getSelectedVertices())
            if (vertex.isClicked(e.getPoint())) {
                readyToDrag = true;
                return;
            }

        readyToDrag = false;
    }

    /**
     * Prepares the VertexDragger to start dragging.
     */
    private void startDrag(MouseEvent e) {
        // Save the starting point of the drag:
        dragStartingPoint = new Point(e.getX(), e.getY());

        // Save the initial position of the selected vertices:
        for (GraphVertex vertex : graph.getSelectedVertices())
            initialLocations.add(new Point(vertex.getX(), vertex.getY()));

        dragging = true;
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
    private void finishDrag() {
        if (allVerticesInsidePanel()) {
            graph.addUndoableEdit(new VertexDragUndoableEdit(graph, initialLocations));
        } else {
            // Move vertices back to their original position:
            for (int i = 0; i < graph.getSelectedVerticesCount(); i++)
                graph.getSelectedVertices().get(i).setLocation(
                        initialLocations.get(i).x, initialLocations.get(i).y);
        }

        initialLocations.clear();
        readyToDrag = false;
        dragging = false;
    }

    /**
     * Checks if all the selected vertices are inside of the panel.
     */
    private boolean allVerticesInsidePanel() {
        int topLimit = GraphPanel.BORDER_THICKNESS;
        int leftLimit = GraphPanel.BORDER_THICKNESS;
        int bottomLimit = (int) GraphPanel.PREFERRED_SIZE.getHeight() - GraphPanel.BORDER_THICKNESS;
        int rightLimit = (int) GraphPanel.PREFERRED_SIZE.getWidth() - GraphPanel.BORDER_THICKNESS;

        for (GraphVertex vertex : graph.getSelectedVertices()) {
            int topLeftCornerX = vertex.getX();
            int topLeftCornerY = vertex.getY();
            int bottomRightCornerX = vertex.getX() + vertex.getWidth();
            int bottomRightCornerY = vertex.getY() + vertex.getHeight();

            if (topLeftCornerX < leftLimit ||
                    topLeftCornerY < topLimit ||
                    bottomRightCornerX > rightLimit ||
                    bottomRightCornerY > bottomLimit
            )
                return false;
        }

        return true;
    }
}
