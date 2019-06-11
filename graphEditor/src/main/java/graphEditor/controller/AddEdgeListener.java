package graphEditor.controller;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphTempEdge;
import graphEditor.model.GraphVertex;
import graphEditor.view.GraphFrame;
import graphEditor.view.GraphPanel;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class AddEdgeListener implements MouseInputListener {

    private GraphVertex v1;
    private GraphModel graph;
    private GraphPanel panel;
    private GraphFrame parentJFrame;

    public AddEdgeListener(GraphModel graph, GraphFrame parentJFrame) {
        int v1CenterX, v1CenterY;

        this.v1 = graph.getSelectedVertices().get(0);
        this.graph = graph;
        this.parentJFrame = parentJFrame;
        this.panel = parentJFrame.getPanel();

        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);

        v1CenterX = v1.getX() + v1.getWidth() / 2;
        v1CenterY = v1.getY() + v1.getHeight() / 2;

        //System.out.println("Added temp edge");
        graph.addTempEdge(v1, new Point(v1CenterX, v1CenterY));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("clickNorm");
        for (int i = 0; i < graph.getVerticesCount(); i++) {
            GraphVertex v2 = graph.getVertices().get(i);

            if (v2.intersects(e.getPoint())) {
                //System.out.println("CLICKsuccess");

                try {
                    graph.addEdge(v1, v2);
                    break;
                } catch (Exception RuntimeException) {
                    JOptionPane.showMessageDialog(parentJFrame, "You already have an edge there!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
        //System.out.println("Removing all temp edges!");
        graph.removeAllTempEdges();
        //System.out.println("StopListeners!!!!!!!!!!!!!!");
        panel.removeMouseListener(this);
        //System.out.println("removed1!");
        panel.removeMouseMotionListener(this);
        //System.out.println("removed2!");
        graph.createNewVertex();
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
        //TODO: panel draw line following cursor until click
        for (GraphTempEdge te : graph.getTempEdges()) {
            System.out.println(e.getX() + "," + e.getY());
            te.setEndPoint(new Point(e.getX(), e.getY()));
        }

    }

}
