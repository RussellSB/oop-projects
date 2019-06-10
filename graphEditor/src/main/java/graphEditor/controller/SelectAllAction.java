package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Represents the Select All action.
 */
public class SelectAllAction extends AbstractAction {
    private GraphModel graph;

    /**
     * Creates the Select All action.
     */
    SelectAllAction(GraphModel graph) {
        super("Select All");
        this.graph = graph;
    }

    /**
     * Selects all the vertices and edges of the graph.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        graph.selectAll();
    }
}
