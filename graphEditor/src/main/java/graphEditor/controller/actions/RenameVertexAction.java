package graphEditor.controller.actions;

import graphEditor.controller.undoableEdits.RenameVertexUndoableEdit;
import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;
import graphEditor.view.GraphFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents the Rename Vertex action.
 */
public class RenameVertexAction extends AbstractAction implements Observer {
    private GraphModel graph;
    private GraphFrame parentJFrame;

    /**
     * Creates the Rename Vertex action.
     */
    public RenameVertexAction(GraphModel graph, GraphFrame parentJFrame) {
        super("Rename Vertex");

        this.graph = graph;
        this.parentJFrame = parentJFrame;

        graph.addObserver(this);
    }

    /**
     * Renames the selected vertex.
     */
    public static void renameVertex(GraphModel graph, GraphFrame parentJFrame) {
        if (graph.getSelectedVerticesCount() > 1) {
            JOptionPane.showMessageDialog(parentJFrame, "You can only rename one vertex at a time!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        GraphVertex vertex = graph.getSelectedVertices().get(0);

        String newName = JOptionPane.showInputDialog(parentJFrame, "Introduce the new name for the vertex:", vertex.getName());

        if (newName != null)
            try {
                graph.addUndoableEdit(new RenameVertexUndoableEdit(graph, vertex, newName, parentJFrame));
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(parentJFrame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
    }

    /**
     * Renames the selected vertex.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        renameVertex(graph, parentJFrame);
    }

    /**
     * Checks the graph state to determine if this action can be enabled or not.
     * The Rename Vertex action is only available if one (and only one) vertex is selected.
     */
    @Override
    public void update(Observable o, Object arg) {
        setEnabled(graph.getSelectedVerticesCount() == 1);
    }
}
