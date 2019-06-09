package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents the Add Edge action.
 */
public class AddEdgeAction extends AbstractAction implements Observer {
    private GraphModel graph;

    /**
     * Creates the Add Edge action.
     */
    AddEdgeAction(GraphModel graph) {
        super("Add Edge");
        this.graph = graph;
        graph.addObserver(this);
        setEnabled(false);
    }

    /**
     * Adds a new edge between the selected vertex and the one that will be clicked afterwards.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand()); // TODO: Remove when implemented.
        // TODO: Implement.
    }

    /**
     * Checks the graph state to determine if this action can be enabled or not.
     * The Add Edge action is only available if one (and only one) vertex is selected.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (graph.getSelectedVertices().size() == 1)
            setEnabled(true);
        else
            setEnabled(false);
    }
}
