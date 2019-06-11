package graphEditor.controller;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;
import graphEditor.view.GraphPanel;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class AddEdgeListener implements MouseInputListener {

    private GraphVertex v1;
    private GraphModel graph;
    private GraphPanel panel;

    public AddEdgeListener(GraphModel graph, GraphPanel panel) {
        System.out.println("listenEdge");
        this.v1 = graph.getSelectedVertices().get(0);
        this.graph = graph;
        this.panel = panel;
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
                    System.out.println("Already added!");
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
