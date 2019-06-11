package graphEditor.view;

import graphEditor.model.GraphEdge;
import graphEditor.model.GraphModel;
import graphEditor.model.GraphTempEdge;
import graphEditor.model.GraphVertex;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Observable;
import java.util.Observer;

/**
 * Panel for the graph editor, where vertices and edges will be painted.
 */
public class GraphPanel extends JPanel implements Observer {
    private int repaintingCount = 0; // TODO: Remove when testing is finished.
    private final static int V_ARC_HEIGHT = 20;
    private final static int V_ARC_WIDTH = 20;
    private GraphModel graph;

    /**
     * Create a new GraphPanel, where vertices and edges will be painted.
     */
    GraphPanel(GraphModel graph) {
        this.graph = graph;
        graph.addObserver(this);

        setBorder(BorderFactory.createLineBorder(new Color(70, 60, 110), 30));
        setBackground(new Color(83, 70, 126));
        setVisible(true);
        setOpaque(true);

    }

    /**
     * Sets the graph that will be painted by this panel.
     */
    public void setGraphModel(GraphModel graph) {
        this.graph = graph;
        graph.addObserver(this);
    }

    /**
     * Paints the edges.
     */
    private void paintEdges(Graphics g) {
        int v1CenterX, v1CenterY, v2CenterX, v2CenterY;
        GraphVertex v1, v2;

        for (GraphEdge e : graph.getEdges()) {
            v1 = e.getV1();
            v2 = e.getV2();

            // Get the center point of v1 and v2.
            v1CenterX = v1.getX() + v1.getWidth() / 2;
            v1CenterY = v1.getY() + v1.getHeight() / 2;
            v2CenterX = v2.getX() + v2.getWidth() / 2;
            v2CenterY = v2.getY() + v2.getHeight() / 2;

            Graphics2D g2 = (Graphics2D) g;

            // Style depends on whether the edge is selected or not.
            if (graph.isSelected(e)) {
                g.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(5));
            } else {
                g.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(2));
            }

            g2.draw(new Line2D.Float(v1CenterX, v1CenterY, v2CenterX, v2CenterY));
            g2.setStroke(new BasicStroke(1)); // Leave the stroke as it was.
        }
    }

    /**
     * Paints the vertices.
     */
    private void paintVertices(Graphics g) {
        for (GraphVertex v : graph.getVertices()) {
            // To center the text.
            int textWidth = g.getFontMetrics().stringWidth(v.getName());
            int textHeight = g.getFontMetrics().getHeight();
            int vertexCenterX = v.getX() + v.getWidth() / 2;
            int vertexCenterY = v.getY() + v.getHeight() / 2;

            // Style depends on whether the vertex is selected or not.
            if (graph.isSelected(v)) {
                // Paint rectangle.
                g.setColor(new Color(21, 194, 187));
                g.fillRoundRect(v.getX(), v.getY(), v.getWidth(), v.getHeight(), V_ARC_WIDTH, V_ARC_HEIGHT);

                // Paint rectangle border.
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(5));
                g.setColor(Color.BLACK);
                g.drawRoundRect(v.getX(), v.getY(), v.getWidth(), v.getHeight(), V_ARC_WIDTH, V_ARC_HEIGHT);
                g2.setStroke(new BasicStroke(1)); // Leave the stroke as it was.

                // Paint name.
                g.setColor(Color.BLACK);
                g.drawString(v.getName(), vertexCenterX - textWidth / 2, vertexCenterY + textHeight / 4);
            } else {
                // Paint rectangle.
                g.setColor(new Color(21, 152, 145));
                g.fillRoundRect(v.getX(), v.getY(), v.getWidth(), v.getHeight(), V_ARC_WIDTH, V_ARC_HEIGHT);

                // Paint name.
                g.setColor(Color.WHITE);
                g.drawString(v.getName(), vertexCenterX - textWidth / 2, vertexCenterY + textHeight / 4);
            }
        }
    }

    private void paintTempEdges(Graphics g) {
        for (GraphTempEdge te : graph.getTempEdges()) {
            int v1CenterX, v1CenterY, endPointX, endPointY;

            GraphVertex v1 = te.getV1();
            Point endPoint = te.getEndPoint();

            // Get the center point of v1 and endPoint
            v1CenterX = v1.getX() + v1.getWidth() / 2;
            v1CenterY = v1.getY() + v1.getHeight() / 2;
            endPointX = endPoint.x;
            endPointY = endPoint.y;

            Graphics2D g2 = (Graphics2D) g;

            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(5));
            g2.draw(new Line2D.Float(v1CenterX, v1CenterY, endPointX, endPointY));
            g2.setStroke(new BasicStroke(1)); // Leave the stroke as it was.
        }
    }

    /**
     * Paints all the items that this class is responsible for.
     */
    @Override
    public void paintComponent(Graphics g) {
        System.out.println("Repainting " + repaintingCount++ + "..."); // TODO: Remove when testing is finished.
        super.paintComponent(g);
        paintEdges(g);
        paintTempEdges(g);
        paintVertices(g);
    }

    /**
     * Tell this GraphPanel that the graph it displays has changed and it's time to repaint it.
     */
    @Override
    public void update(Observable observed, Object message) {
        repaint();
    }
}
