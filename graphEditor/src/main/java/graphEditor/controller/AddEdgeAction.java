package graphEditor.controller;

import graphEditor.model.GraphModel;
import graphEditor.view.GraphPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents the Add Edge action.
 */
public class AddEdgeAction extends AbstractAction implements Observer {
    private GraphModel graph;
    private GraphPanel panel;

    private AddEdgeListener edgeListener;
    private boolean existsEdgeListener = false;

    /**
     * Creates the Add Edge action.
     */
    AddEdgeAction(GraphModel graph, GraphPanel panel) {
        super("Add Edge");
        this.graph = graph;
        this.panel = panel;
        graph.addObserver(this);
        setEnabled(false);
    }

    /**
     * Checks the graph state to determine if this action can be enabled or not.
     * The Add Edge action is only available if one (and only one) vertex is selected.
     */
    @Override
    public void update(Observable o, Object arg) {

        if (graph.getSelectedVerticesCount() == 1 && !existsEdgeListener)
            setEnabled(true);
        else
            setEnabled(false);

        if (existsEdgeListener) {
            if (!edgeListener.getListen()) {
                panel.removeMouseListener(edgeListener);
                System.out.println("removed!");
                existsEdgeListener = false;
            }
        }
    }

    /**
     * Adds a new edge between the selected vertex and the one that will be clicked afterwards.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("actionEdge");

        if (!existsEdgeListener) {
            edgeListener = new AddEdgeListener(graph);
            existsEdgeListener = true;
            panel.addMouseListener(edgeListener);
        }

    }
}
