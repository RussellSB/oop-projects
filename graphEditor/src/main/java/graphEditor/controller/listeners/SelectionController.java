package graphEditor.controller.listeners;

import graphEditor.controller.actions.RenameVertexAction;
import graphEditor.model.GraphEdge;
import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;
import graphEditor.view.GraphFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Selection Controller: Allows to select vertices and edges.
 */
public class SelectionController implements MouseListener, KeyListener {
    private GraphModel graph;
    private GraphFrame parentJFrame;

    /**
     * Creates the Selection Controller.
     */
    public SelectionController(GraphModel graph, GraphFrame parentJFrame) {
        this.graph = graph;
        this.parentJFrame = parentJFrame;

        parentJFrame.addKeyListener(this);
        parentJFrame.getPanel().addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Calls all the actions that should be performed when the mouse is pressed.
     */
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Controls if the ctrl button is pressed.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        parentJFrame.setCtrlIsDown(e.isControlDown());
    }

    /**
     * Controls if the ctrl button is released.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        parentJFrame.setCtrlIsDown(e.isControlDown());
    }

    /**
     * Renames a vertex after a double click.
     *
     * @return true if the action was performed; false otherwise.
     */
    private boolean vertexRenaming(MouseEvent e) {
        // Only if double clicked:
        if (e.getClickCount() != 2)
            return false;

        // We go trough the vertices list backwards so in case of overlap the vertex on top is selected:
        for (int i = graph.getVerticesCount() - 1; i >= 0; i--) {
            GraphVertex vertex = graph.getVertices().get(i);

            if (vertex.intersects(e.getPoint())) {
                RenameVertexAction.renameVertex(graph, parentJFrame);
                return true;
            }
        }

        return false;
    }

    /**
     * Selects vertices.
     *
     * @return true if the action was performed; false otherwise.
     */
    private boolean vertexSelection(MouseEvent e) {
        // We go trough the vertices list backwards so in case of overlap the vertex on top is selected:
        for (int i = graph.getVerticesCount() - 1; i >= 0; i--) {
            GraphVertex vertex = graph.getVertices().get(i);

            if (vertex.intersects(e.getPoint())) {
                if (parentJFrame.ctrlIsDown()) { // If CTRL is held down, add/remove vertex from selection:
                    if (graph.isSelected(vertex))
                        graph.deSelect(vertex);
                    else
                        graph.select(vertex);
                } else {
                    if (!graph.isSelected(vertex)) { // If not selected, deselect all and select:
                        graph.deselectAll();
                        graph.select(vertex);
                    }
                }

                return true;
            }
        }

        return false;
    }

    /**
     * Selects edges.
     *
     * @return true if the action was performed; false otherwise.
     */
    private boolean edgeSelection(MouseEvent e) {
        // We go trough the edges list backwards so in case of overlap the edge on top is selected.
        for (int i = graph.getEdgesCount() - 1; i >= 0; i--) {
            GraphEdge edge = graph.getEdges().get(i);

            if (edge.intersects(e.getPoint())) {
                if (parentJFrame.ctrlIsDown()) { // If CTRL is held down, add/remove edge from selection:
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
}
