package graphEditor.view;

import javax.swing.*;
import java.awt.*;
import graphEditor.controller.GraphMenuBar;

public class GraphFrame extends JFrame {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;

    private GraphPanel panel; //to be used to draw on from menubar

    /**
     * Create a new GraphFrame.
     */
    public GraphFrame(){
        super("Graph Editor");

        this.panel = new GraphPanel();

        getContentPane().add(panel);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(new GraphMenuBar());
        setVisible(true);
    }

    public GraphPanel getGraphPanel(){
        return this.panel;
    }
}