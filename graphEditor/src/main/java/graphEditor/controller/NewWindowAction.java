package graphEditor.controller;

import graphEditor.model.GraphModel;
import graphEditor.view.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Represents the Default Size action.
 */
public class NewWindowAction extends AbstractAction {
    private GraphModel graph;

    /**
     * Creates the New Window action, in the scenario that frame isn't already fed
     */
    NewWindowAction(GraphModel graph) {
        super("New Window");
        this.graph = graph;
    }

    /**
     * Creates new window
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new Window(graph);
    }
}
