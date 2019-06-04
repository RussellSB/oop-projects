package graphEditor.view;

import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class GraphPanel extends JPanel implements Observer {
    private GraphModel graph;

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

    private void paintVertices() {
        // TODO
    }

    private void paintEdges() {
        // TODO
    }

    /**
     * Paint the items that this class is responsible for.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintVertices();
        paintEdges();
    }

    /**
     * Tell this GraphPanel that the object it displays has changed.
     */
    @Override
    public void update(Observable observed, Object message) {
        repaint();
    }
}
