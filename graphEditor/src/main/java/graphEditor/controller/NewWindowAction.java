package graphEditor.controller;

import graphEditor.model.GraphModel;
import graphEditor.view.GraphWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Represents the New Window action.
 */
public class NewWindowAction extends AbstractAction {
    /**
     * Creates the New Window action.
     */
    NewWindowAction() {
        super("New Window");
    }

    /**
     * Opens a new Graph Editor window.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new GraphWindow(new GraphModel());
    }
}
