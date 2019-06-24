package graphEditor.controller.listeners;

import graphEditor.controller.undoableEdits.AddEdgeUndoableEdit;
import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;
import graphEditor.view.GraphFrame;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

/**
 * Add Edge Listener: Allows to add edges.
 */
public class AddEdgeListener implements MouseInputListener {
    private GraphVertex v1;
    private GraphModel graph;
    private GraphFrame parentJFrame;

    /**
     * Creates the AddEdgeListener.
     */
    public AddEdgeListener(GraphModel graph, GraphFrame parentJFrame) throws RuntimeException {
        if (graph.getSelectedVerticesCount() > 1)
            throw new RuntimeException("You can only select one edge");

        this.graph = graph;
        this.parentJFrame = parentJFrame;
        this.v1 = graph.getSelectedVertices().get(0); // Selected vertex.

        parentJFrame.getPanel().addMouseListener(this);
        parentJFrame.getPanel().addMouseMotionListener(this);

        // Set the starting point of the addingEdgeLine:
        int v1CenterX = v1.getX() + v1.getWidth() / 2;
        int v1CenterY = v1.getY() + v1.getHeight() / 2;
        graph.setAddingEdgeLineStart(v1CenterX, v1CenterY);

        // Set the initial ending point of the addingEdgeLine:
        graph.setAddingEdgeLineEnd(v1CenterX, v1CenterY);

        // Tell the graph (and the GraphPanel) we are adding a new edge:
        graph.setAddingEdgeMode(true);
    }

    /**
     * Makes the other end of the addingEdgeLineEnd follow the mouse.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        if (graph.getSelectedVerticesCount() == 1) {
            graph.setAddingEdgeLineEnd(e.getX(), e.getY());
        } else {
            // If we unselect the origin vertex we cancel the Add Edge action:
            graph.setAddingEdgeMode(false);
            parentJFrame.getPanel().removeMouseListener(this);
            parentJFrame.getPanel().removeMouseMotionListener(this);
        }
    }

    /**
     * If the mouse presses a vertex the edge will be added; if not we exit "Adding Edge Mode".
     */
    @Override
    public void mousePressed(MouseEvent e) {
        // We go trough the vertices list backwards so in case of overlap the vertex on top is selected:
        for (int i = graph.getVerticesCount() - 1; i >= 0; i--) {
            GraphVertex v2 = graph.getVertices().get(i);

            if (v2.isClicked(e.getPoint())) {
                try {
                    graph.addUndoableEdit(new AddEdgeUndoableEdit(graph, v1, v2));
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(parentJFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    // v2 gets selected when we click on it but we want v1 to stay selected:
                    graph.deSelect(v2);
                    graph.select(v1);

                    // Exit "Adding Edge Mode" and remove this listener:
                    graph.setAddingEdgeMode(false); // Exit
                    parentJFrame.getPanel().removeMouseListener(this);
                    parentJFrame.getPanel().removeMouseMotionListener(this);
                }

                break;
            }
        }

        // If we don't click in any vertex we simply exit "Adding Edge Mode" and remove this listener.
        graph.setAddingEdgeMode(false);
        parentJFrame.getPanel().removeMouseListener(this);
        parentJFrame.getPanel().removeMouseMotionListener(this);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
