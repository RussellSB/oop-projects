package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SaveAction extends AbstractAction {
    private GraphModel graph;

    SaveAction(GraphModel graph){
        super("Save");
        this.graph = graph;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());

        // TODO: Window to select where to save

        try {
            graph.save("test");
        } catch (IOException ex) {
            ex.printStackTrace(); // TODO: Replace with banner window
        }
    }
}
