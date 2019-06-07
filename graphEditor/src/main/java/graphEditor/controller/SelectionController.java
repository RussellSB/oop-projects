package graphEditor.controller;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;
import graphEditor.view.GraphPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ListIterator;

public class SelectionController implements MouseListener {

    private GraphModel graph;
    private GraphPanel panel;

    public SelectionController(GraphModel graph, GraphPanel panel) {
        this.graph = graph;
        this.panel = panel;
        panel.addMouseListener(this);
        //panel.addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point clickCoord = new Point(e.getX(), e.getY());

        ListIterator listIterator = graph.getVertices().listIterator(graph.getVertices().size());
        while (listIterator.hasPrevious()) {
            GraphVertex vertex = (GraphVertex) listIterator.previous();

            if (vertex.getRectangle().contains(clickCoord)) {
                if (vertex.isSelected()) {
                    vertex.deselect();
                    break;
                } else {
                    vertex.select();
                    break;
                }
            }
        }
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
