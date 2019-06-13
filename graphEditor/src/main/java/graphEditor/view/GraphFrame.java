package graphEditor.view;

import graphEditor.controller.GraphMenuBar;
import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.*;

/**
 * Frame for the Graph Editor.
 */
public class GraphFrame extends JFrame {
    public static final Dimension PREFERRED_SIZE = new Dimension(1000, 700);
    private GraphPanel panel;
    private boolean ctrlIsDown; // Boolean for the ctrl button status.

    /**
     * Creates a new GraphFrame with a scrollable GraphPanel and the GraphMenuBar.
     */
    public GraphFrame(GraphModel graph) {
        super("Graph Editor");

        setSize(PREFERRED_SIZE);
        setPreferredSize(PREFERRED_SIZE);
        setMaximumSize(PREFERRED_SIZE);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new GraphPanel(graph);

        JScrollPane scrollPane = new JScrollPane(panel);

        getContentPane().add(scrollPane);

        setJMenuBar(new GraphMenuBar(graph, this));

        setVisible(true);
    }

    /**
     * Gets the GraphPanel.
     */
    public GraphPanel getPanel() {
        return panel;
    }

    /**
     * Checks if the ctrl button is being pressed or not.
     */
    public boolean ctrlIsDown() {
        return ctrlIsDown;
    }

    /**
     * Sets ctrlIsDown.
     */
    public void setCtrlIsDown(boolean ctrlIsDown) {
        this.ctrlIsDown = ctrlIsDown;
    }
}