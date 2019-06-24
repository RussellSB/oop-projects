package graphEditor.view;

import graphEditor.model.GraphEdge;
import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;
import javafx.scene.shape.Line;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Observable;
import java.util.Observer;

/**
 * Panel for the graph editor, where vertices and edges will be painted.
 */
public class GraphPanel extends JPanel implements Observer {
    public static final Dimension PREFERRED_SIZE = new Dimension(5000, 5000);
    public static final int BORDER_THICKNESS = 30;
    private static final int V_ARC_HEIGHT = 20; // Height of the arc of the corners of the vertices.
    private static final int V_ARC_WIDTH = 20; // Width of the arc of the corners of the vertices.
    private GraphModel graph;

    /**
     * Creates a new GraphPanel, where vertices and edges will be painted.
     */
    GraphPanel(GraphModel graph) {
        this.graph = graph;
        graph.addObserver(this);

        setBorder(BorderFactory.createLineBorder(new Color(70, 60, 110), BORDER_THICKNESS));
        setBackground(new Color(83, 70, 126));
        setVisible(true);
        setOpaque(true);

        setSize(PREFERRED_SIZE);
        setPreferredSize(PREFERRED_SIZE);
        setMaximumSize(PREFERRED_SIZE);
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

            // Get the center point of v1 and v2:
            v1CenterX = v1.getX() + v1.getWidth() / 2;
            v1CenterY = v1.getY() + v1.getHeight() / 2;
            v2CenterX = v2.getX() + v2.getWidth() / 2;
            v2CenterY = v2.getY() + v2.getHeight() / 2;

            Graphics2D g2 = (Graphics2D) g;

            // Style depends on whether the edge is selected or not:
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
            // To center the text:
            int textWidth = g.getFontMetrics().stringWidth(v.getName());
            int textHeight = g.getFontMetrics().getHeight();
            int vertexCenterX = v.getX() + v.getWidth() / 2;
            int vertexCenterY = v.getY() + v.getHeight() / 2;

            // Style depends on whether the vertex is selected or not:
            if (graph.isSelected(v)) {
                // Paint rectangle:
                g.setColor(new Color(21, 194, 187));
                g.fillRoundRect(v.getX(), v.getY(), v.getWidth(), v.getHeight(), V_ARC_WIDTH, V_ARC_HEIGHT);

                // Paint rectangle border:
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(5));
                g.setColor(Color.BLACK);
                g.drawRoundRect(v.getX(), v.getY(), v.getWidth(), v.getHeight(), V_ARC_WIDTH, V_ARC_HEIGHT);
                g2.setStroke(new BasicStroke(1)); // Leave the stroke as it was.

                // Paint name:
                g.setColor(Color.BLACK);
                g.drawString(v.getName(), vertexCenterX - textWidth / 2, vertexCenterY + textHeight / 4);
            } else {
                // Paint rectangle:
                g.setColor(new Color(21, 152, 145));
                g.fillRoundRect(v.getX(), v.getY(), v.getWidth(), v.getHeight(), V_ARC_WIDTH, V_ARC_HEIGHT);

                // Paint name:
                g.setColor(Color.WHITE);
                g.drawString(v.getName(), vertexCenterX - textWidth / 2, vertexCenterY + textHeight / 4);
            }
        }
    }

    /**
     * Paints the addingEdgeLine (auxiliary line used to add edges visually) in case the graph is in "Adding Edge Mode".
     */
    private void paintAddingEdgeLine(Graphics g) {
        // Only if the graph is in "Adding Edge Mode".
        if (!graph.isAddingEdgeMode())
            return;

        Graphics2D g2 = (Graphics2D) g;

        Line addingEdgeLine = graph.getAddingEdgeLine();
        Line2D addingEdgeLine2D = new Line2D.Float(
                (float) addingEdgeLine.getStartX(),
                (float) addingEdgeLine.getStartY(),
                (float) addingEdgeLine.getEndX(),
                (float) addingEdgeLine.getEndY()
        );

        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(5));
        g2.draw(addingEdgeLine2D);
        g2.setStroke(new BasicStroke(1)); // Leave the stroke as it was.
    }

    /**
     * Gets the font metrics.
     */
    public FontMetrics getFontMetrics() {
        return getGraphics().getFontMetrics();
    }

    /**
     * Paints all the items that this class is responsible for.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintEdges(g);
        paintAddingEdgeLine(g);
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
