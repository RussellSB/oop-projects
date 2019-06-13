package graphEditor.controller.undoableEdits;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;

import javax.swing.*;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class RenameVertexUndoableEdit extends AbstractUndoableEdit {

    private GraphModel graph;
    private String oldName;
    private String newName;
    private GraphVertex vertex;
    private JFrame parentJFrame;

    public RenameVertexUndoableEdit(GraphModel graph, JFrame parentJFrame) {
        this.graph = graph;
        this.parentJFrame = parentJFrame;
        renameVertex(graph);
    }

    /**
     * Renames the selected vertex.
     */
    public void renameVertex(GraphModel graph) {
        if (graph.getSelectedVerticesCount() > 1) {
            JOptionPane.showMessageDialog(parentJFrame, "You can only rename one vertex at a time!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        vertex = graph.getSelectedVertices().get(0);
        oldName = vertex.getName();

        newName = JOptionPane.showInputDialog(parentJFrame, "Introduce the new name for the vertex:", vertex.getName());

        if (newName != null)
            try {
                vertex.setName(newName);
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(parentJFrame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
    }

    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        vertex.setName(oldName);
    }

    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        vertex.setName(newName);
    }
}
