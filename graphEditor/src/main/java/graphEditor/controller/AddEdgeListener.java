package graphEditor.controller;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class AddEdgeListener implements MouseInputListener {

    private GraphVertex v1;
    private GraphModel graph;

    private boolean listen = true;

    public AddEdgeListener(GraphModel graph) {
        System.out.println("listenEdge");
        this.v1 = graph.getSelectedVertices().get(0);
        this.graph = graph;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (listen) {
            System.out.println("clickNorm");
            for (int i = 0; i < graph.getVerticesCount(); i++) {
                GraphVertex v2 = graph.getVertices().get(i);

                if (v2.intersects(e.getPoint())) {
                    System.out.println("CLICKsuccess");
                    graph.addEdge(v1, v2);
                    break;
                }
            }
            System.out.println("StoplistenEdge");
            listen = false;
        }
    }

    public boolean getListen() {
        return listen;
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
