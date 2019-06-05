package graphEditor.view;

import graphEditor.model.GraphEdge;
import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class GraphPanel extends JPanel implements Observer {
    private final static int V_ARC_HEIGHT = 20;
    private final static int V_ARC_WIDTH = 20;
    private GraphModel graph;

    /**
     * Create a new GraphPanel, where vertices and edges will be drawn.
     */
    public GraphPanel(GraphModel graph) {
        this.graph = graph;
        graph.addObserver(this);

        setBorder(BorderFactory.createLineBorder(new Color(70, 60, 110), 30));
        setBackground(new Color(83, 70, 126));
        setVisible(true);
        setOpaque(true);
    }

    public void setGraphModel(GraphModel graph) {
        this.graph = graph;
        graph.addObserver(this);
    }

    private void paintEdges(Graphics g) {
        int v1CenterX, v1CenterY, v2CenterX, v2CenterY;
        GraphVertex v1, v2;

        g.setColor(Color.WHITE);

        for (GraphEdge e : graph.getEdges()) {
            v1 = e.getV1();
            v2 = e.getV2();

            v1CenterX = v1.getX() + v1.getWidth() / 2;
            v1CenterY = v1.getY() + v1.getHeight() / 2;
            v2CenterX = v2.getX() + v2.getWidth() / 2;
            v2CenterY = v2.getY() + v2.getHeight() / 2;

            g.drawLine(v1CenterX, v1CenterY, v2CenterX, v2CenterY);
        }
    }

    private void paintVertices(Graphics g) {
        for (GraphVertex v : graph.getVertices()) {
            // Paint rectangle.
            g.setColor(new Color(21, 152, 145));
            g.fillRoundRect(v.getX(), v.getY(), v.getWidth(), v.getHeight(), V_ARC_WIDTH, V_ARC_HEIGHT);

            // To center the text.
            int textWidth = g.getFontMetrics().stringWidth(v.getName());
            int textHeight = g.getFontMetrics().getHeight();
            int vertexCenterX = v.getX() + v.getWidth() / 2;
            int vertexCenterY = v.getY() + v.getHeight() / 2;

            // Paint name.
            g.setColor(Color.WHITE);
            g.drawString(v.getName(), vertexCenterX - textWidth / 2, vertexCenterY + textHeight / 4);
        }
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
     * Tell this GraphPanel that the graph it displays has changed so it repaints.
     */
    @Override
    public void update(Observable observed, Object message) {
        repaint();
    }
}
