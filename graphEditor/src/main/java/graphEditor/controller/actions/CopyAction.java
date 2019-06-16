package graphEditor.controller.actions;

import graphEditor.controller.CopyPasteManager;
import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents the Copy action.
 */
public class CopyAction extends AbstractAction implements Observer {
    private CopyPasteManager copyPasteManager;

    /**
     * Creates the Copy action.
     */
    public CopyAction(GraphModel graph) {
        super("Copy");

        this.copyPasteManager = graph.getCopyPasteManager();

        copyPasteManager.addObserver(this);
    }

    /**
     * Tells the CopyPasteManager to copy.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        copyPasteManager.copy();
    }

    /**
     * Checks the graph state to determine if this action can be enabled or not.
     * The Copy action is only available if at least one vertex is selected.
     */
    @Override
    public void update(Observable o, Object arg) {
        setEnabled(copyPasteManager.readyToCopyCut());
    }
}
