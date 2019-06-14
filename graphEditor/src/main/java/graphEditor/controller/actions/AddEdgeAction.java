package graphEditor.controller.actions;

import graphEditor.controller.listeners.AddEdgeListener;
import graphEditor.model.GraphModel;
import graphEditor.view.GraphFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents the Add Edge action.
 */
public class AddEdgeAction extends AbstractAction implements Observer {
    private GraphModel graph;
    private GraphFrame parentJFrame;

    /**
     * Creates the Add Edge action.
     */
    public AddEdgeAction(GraphModel graph, GraphFrame parentJFrame) {
        super("Add Edge");

        this.graph = graph;
        this.parentJFrame = parentJFrame;

        graph.addObserver(this);
        setEnabled(false);
    }

    /**
     * Creates a new AddEdgeListener that will take care of adding a new edge between the selected vertex and the one that will be clicked afterwards.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new AddEdgeListener(graph, parentJFrame);
        setEnabled(false);
    }

    /**
     * Checks the graph state to determine if this action can be enabled or not.
     * The Add Edge action is only available if one (and only one) vertex is selected.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (graph.getSelectedVerticesCount() == 1) {
            setEnabled(true);
        } else
            setEnabled(false);
    }
}
