package graphEditor.controller.undoableEdits;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;
import graphEditor.view.GraphFrame;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

/**
 * An UndoableEdit used to undo/redo the renaming of a vertex.
 */
public class RenameVertexUndoableEdit extends AbstractUndoableEdit {
    private GraphModel graph;
    private GraphFrame parentJFrame;
    private GraphVertex vertex;
    private String oldName;
    private String newName;

    /**
     * Renames the vertex and saves the old and the new name for future use.
     */
    public RenameVertexUndoableEdit(GraphModel graph, GraphVertex vertex, String newName, GraphFrame parentJFrame) throws RuntimeException {
        this.graph = graph;
        this.parentJFrame = parentJFrame;
        this.vertex = vertex;
        this.newName = newName;
        this.oldName = vertex.getName();

        vertex.setName(newName, parentJFrame.getPanel().getFontMetrics());
    }

    /**
     * Undo the edit by renaming the vertex to its original name.
     * Selects the modified vertex.
     */
    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        vertex.setName(oldName, parentJFrame.getPanel().getFontMetrics());

        graph.deselectAll();
        graph.select(vertex);
    }

    /**
     * Redo the edit by renaming the vertex again.
     * Selects the modified vertex.
     */
    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        vertex.setName(newName, parentJFrame.getPanel().getFontMetrics());

        graph.deselectAll();
        graph.select(vertex);
    }
}
