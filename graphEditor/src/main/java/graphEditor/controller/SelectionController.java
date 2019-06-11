package graphEditor.controller;

import graphEditor.model.GraphEdge;
import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;
import graphEditor.view.GraphFrame;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * TODO: Comments for the class & methods.
 */
public class SelectionController implements MouseListener, KeyListener {
    private GraphModel graph;
    private JFrame parentJFrame;
    private boolean isControlDown;

    public SelectionController(GraphModel graph, GraphFrame parentJFrame) {
        this.graph = graph;
        this.parentJFrame = parentJFrame;
        parentJFrame.getPanel().addMouseListener(this);
        parentJFrame.getPanel().addKeyListener(this);
        parentJFrame.getPanel().requestFocus();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!vertexRenaming(e))
            if (!vertexSelection(e))
                if (!edgeSelection(e))
                    graph.deselectAll();
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

    // TODO: REMOVE and move to the vertex dragger.
    private boolean preDraggingVertexSelection(MouseEvent e) {
        System.out.println("Came to the dumb one");
        // We go trough the vertices list backwards so in case of overlap the vertex on top is selected.
        for (int i = graph.getVerticesCount() - 1; i >= 0; i--) {
            GraphVertex vertex = graph.getVertices().get(i);

            if (vertex.intersects(e.getPoint())) {
                graph.select(vertex);
                return true;
            }
        }

        return false;
    }

    private boolean vertexSelection(MouseEvent e) {
        // We go trough the vertices list backwards so in case of overlap the vertex on top is selected.
        for (int i = graph.getVerticesCount() - 1; i >= 0; i--) {
            GraphVertex vertex = graph.getVertices().get(i);

            if (vertex.intersects(e.getPoint())) {
                if (isControlDown) { // If CTRL is held down add/remove vertex from selection
                    if (graph.isSelected(vertex))
                        graph.deSelect(vertex);
                    else
                        graph.select(vertex);
                } else {
                    if (!graph.isSelected(vertex)) { // If not selected, deselect all and select
                        graph.deselectAll();
                        graph.select(vertex);
                    }
                }

                return true;
            }
        }

        return false;
    }

    private boolean edgeSelection(MouseEvent e) {
        // We go trough the edges list backwards so in case of overlap the edge on top is selected.
        for (int i = graph.getEdgesCount() - 1; i >= 0; i--) {
            GraphEdge edge = graph.getEdges().get(i);

            if (edge.intersects(e.getPoint())) {
                if (isControlDown) { // TODO: Ctrl button is kept pressed
                    if (graph.isSelected(edge))
                        graph.deSelect(edge);
                    else
                        graph.select(edge);
                } else {
                    graph.deselectAll();
                    graph.select(edge);
                }

                return true;
            }
        }

        return false;
    }

    private boolean vertexRenaming(MouseEvent e) {
        // Only if double clicked.
        if (e.getClickCount() != 2)
            return false;

        // We go trough the vertices list backwards so in case of overlap the vertex on top is selected.
        for (int i = graph.getVerticesCount() - 1; i >= 0; i--) {
            GraphVertex vertex = graph.getVertices().get(i);

            if (vertex.intersects(e.getPoint())) {
                RenameVertexAction.renameVertex(graph, parentJFrame);
                return true;
            }
        }

        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        isControlDown = e.isControlDown();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        isControlDown = e.isControlDown();
    }
}
