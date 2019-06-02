package graphEditor.view;

import javax.swing.*;
import java.awt.*;
import graphEditor.controller.GraphMenuBar;

public class GraphFrame extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;


    /**
     * Create a new GraphFrame.
     */
    public GraphFrame(){
        super("Graph Editor");
        getContentPane().add(new GraphPanel());
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(new GraphMenuBar());
        setVisible(true);
    }
}