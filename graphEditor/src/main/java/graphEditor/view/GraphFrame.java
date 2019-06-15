package graphEditor.view;

import graphEditor.controller.GraphMenuBar;
import graphEditor.controller.GraphToolBar;
import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.*;

/**
 * Frame for the Graph Editor.
 */
public class GraphFrame extends JFrame {
    public static final Dimension PREFERRED_SIZE = new Dimension(1000, 700);
    private boolean ctrlIsDown; // Boolean for the ctrl button status.
    private GraphPanel panel;
    private GraphToolBar toolBar;

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

        getContentPane().add(new JScrollPane(panel)); // Add the GraphPanel inside of a JScrollPane.

        setJMenuBar(new GraphMenuBar(graph, this)); // Add Menu bar.

        toolBar = new GraphToolBar(graph, this);
        add(toolBar, BorderLayout.PAGE_START); // Add Tool bar.

        setVisible(true);
    }

    /**
     * Gets the GraphPanel.
     */
    public GraphPanel getPanel() {
        return panel;
    }

    /**
     * Gets the GraphToolBar.
     */
    public GraphToolBar getToolBar() {
        return toolBar;
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