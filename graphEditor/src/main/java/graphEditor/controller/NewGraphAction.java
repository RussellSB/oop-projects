package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NewGraphAction extends AbstractAction {
    private GraphModel graph;

    NewGraphAction(GraphModel graph) {
        super("New Graph");
        this.graph = graph;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand()); // TODO: Remove
        graph.reset();
    }
}
