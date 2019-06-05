package graphEditor.view;

import graphEditor.model.GraphEdge;
import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class GraphPanel extends JPanel implements Observer {

    private GraphModel graph;

    private final static int V_ARC_HEIGHT = 20;
    private final static int V_ARC_WIDTH = 20;

    /**
     * Create a new GraphPanel, where vertices will be drawn
     */
    public GraphPanel(GraphModel graph) {
        this.graph = graph;
        graph.addObserver(this);

        setBorder(BorderFactory.createLineBorder(new Color(70, 60, 110), 50));
        setBackground(new Color(83, 70, 126));
        setVisible(true);
        setOpaque(true);
    }

    public void setModel(GraphModel graph) {
        this.graph = graph;
        graph.addObserver(this);
    }

    private void paintVertices(Graphics g) {
        g.setColor(new Color(21, 152, 145));
        for (GraphVertex v : graph.getVertices()){
            g.fillRoundRect(v.getX(), v.getY(), v.getWidth(), v.getHeight(), V_ARC_WIDTH, V_ARC_HEIGHT);
        }
    }

    private void paintEdges(Graphics g) {

        int v1_x, v1_y, v2_x, v2_y;
        GraphVertex v1, v2;

        g.setColor(Color.WHITE);
        for (GraphEdge e : graph.getEdges()){

            v1 = e.getV1();
            v2 = e.getV2();

            v1_x = v1.getX() + v1.getWidth() / 2;
            v1_y = v1.getY() + v1.getHeight() / 2;
            v2_x = v2.getX() + v2.getWidth() / 2;
            v2_y = v2.getY() + v2.getHeight() / 2;

            g.drawLine(v1_x, v1_y, v2_x, v2_y);
        }
        //g.drawLine(300, 200, 600, 200);

    }

    /**
     * Paint the items that this class is responsible for.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintEdges(g);
        paintVertices(g);
    }

    /**
     * Tell this GraphPanel that the object it displays has changed.
     */
    @Override
    public void update(Observable observed, Object message) {
        repaint();
    }
}
