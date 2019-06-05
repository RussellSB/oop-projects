package graphEditor.view;

import graphEditor.controller.GraphMenuBar;
import graphEditor.model.GraphModel;

import javax.swing.*;

public class GraphFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 1000;
    private static final int DEFAULT_HEIGHT = 700;

    /**
     * Create a new GraphFrame with a GraphPanel.
     */
    public GraphFrame(GraphModel graph) {
        super("Graph Editor");

        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        getContentPane().add(new GraphPanel(graph));

        setJMenuBar(new GraphMenuBar(graph));
    }
}