package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddVertexAction extends AbstractAction {
    private GraphModel graph;

    AddVertexAction(GraphModel graph) {
        super("Add vertex");
        this.graph = graph;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        graph.createNewVertex();
    }
}
