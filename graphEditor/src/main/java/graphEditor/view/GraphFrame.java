package graphEditor.view;

import graphEditor.controller.GraphMenuBar;
import graphEditor.model.GraphModel;

import javax.swing.*;

/**
 * Frame for the Graph Editor
 */
public class GraphFrame extends JFrame {
    public static final int DEFAULT_WIDTH = 1000;
    public static final int DEFAULT_HEIGHT = 700;
    private GraphPanel panel;

    /**
     * Create a new GraphFrame with a GraphPanel.
     */
    public GraphFrame(GraphModel graph) {
        super("Graph Editor");
        panel = new GraphPanel(graph);

        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(panel);
        setJMenuBar(new GraphMenuBar(graph, this));
        setVisible(true);
    }

    /**
     * Gets the GraphPanel.
     */
    public GraphPanel getPanel() {
        return panel;
    }
}