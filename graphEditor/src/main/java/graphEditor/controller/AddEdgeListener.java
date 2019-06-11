package graphEditor.controller;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;
import graphEditor.view.GraphFrame;
import graphEditor.view.GraphPanel;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class AddEdgeListener implements MouseInputListener {

    private GraphVertex v1;
    private GraphModel graph;
    private GraphPanel panel;
    private GraphFrame parentJFrame;

    public AddEdgeListener(GraphModel graph, GraphFrame parentJFrame) {
        System.out.println("listenEdge");
        this.v1 = graph.getSelectedVertices().get(0);

        this.graph = graph;
        this.parentJFrame = parentJFrame;
        this.panel = parentJFrame.getPanel();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("clickNorm");
        for (int i = 0; i < graph.getVerticesCount(); i++) {
            GraphVertex v2 = graph.getVertices().get(i);

            if (v2.intersects(e.getPoint())) {
                System.out.println("CLICKsuccess");

                try {
                    graph.addEdge(v1, v2);
                    break;
                } catch (Exception RuntimeException) {
                    JOptionPane.showMessageDialog(parentJFrame, "You already have an edge there!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
        System.out.println("StoplistenEdge");
        panel.removeMouseListener(this);
        System.out.println("removed!");
    }

    @Override
    public void mouseExited(MouseEvent e) {

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
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
