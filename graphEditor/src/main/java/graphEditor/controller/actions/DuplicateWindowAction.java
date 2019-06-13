package graphEditor.controller.actions;

import graphEditor.model.GraphModel;
import graphEditor.view.GraphFrame;
import graphEditor.view.GraphWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Represents the Duplicate Window action.
 */
public class DuplicateWindowAction extends AbstractAction {
    private GraphModel graph;
    private GraphFrame parentJFrame;

    /**
     * Creates the Duplicate Window action.
     */
    public DuplicateWindowAction(GraphModel graph, GraphFrame parentJFrame) {
        super("Duplicate Window");
        this.graph = graph;
        this.parentJFrame = parentJFrame;
    }

    /**
     * Duplicates the window.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new GraphWindow(graph);
        parentJFrame.setCtrlIsDown(false); // updates CTRL Flag in the scenario that this is accessed holding down CTRL
    }
}
