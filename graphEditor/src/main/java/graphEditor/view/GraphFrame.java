package graphEditor.view;

import graphEditor.controller.GraphMenuBar;
import graphEditor.model.GraphModel;

import javax.swing.*;

public class GraphFrame extends JFrame {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;

    private GraphPanel panel; //to be used to draw on from menubar

    /**
     * Create a new GraphFrame.
     */
    public GraphFrame(GraphModel graph) {
        super("Graph Editor");

        this.panel = new GraphPanel(graph);

        getContentPane().add(panel);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(new GraphMenuBar(graph));
        setVisible(true);
    }

    public GraphPanel getGraphPanel() {
        return this.panel;
    }
}