package graphEditor.controller.actions;

import graphEditor.controller.undoableEdits.RenameVertexUndoableEdit;
import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents the Rename Vertex action.
 */
public class RenameVertexAction extends AbstractAction implements Observer {
    private GraphModel graph;
    private JFrame parentJFrame;

    /**
     * Creates the Rename Vertex action.
     */
    public RenameVertexAction(GraphModel graph, JFrame parentJFrame) {
        super("Rename Vertex");

        this.graph = graph;
        this.parentJFrame = parentJFrame;

        graph.addObserver(this);
        setEnabled(false);
    }

    /**
     * Renames the selected vertex.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        graph.getUndoManager().addEdit(new RenameVertexUndoableEdit(graph, parentJFrame));
    }

    /**
     * Checks the graph state to determine if this action can be enabled or not.
     * The Rename Vertex action is only available if one (and only one) vertex is selected.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (graph.getSelectedVerticesCount() == 1)
            setEnabled(true);
        else
            setEnabled(false);
    }
}
