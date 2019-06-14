package graphEditor.controller.undoableEdits;

import graphEditor.model.GraphVertex;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

/**
 * An UndoableEdit used to undo/redo the renaming of a vertex.
 */
public class RenameVertexUndoableEdit extends AbstractUndoableEdit {
    private GraphVertex vertex;
    private String oldName;
    private String newName;

    /**
     * Renames the vertex and saves the old and the new name for future use.
     */
    public RenameVertexUndoableEdit(GraphVertex vertex, String newName) throws RuntimeException {
        this.vertex = vertex;
        this.newName = newName;
        this.oldName = vertex.getName();

        vertex.setName(newName);
    }

    /**
     * Undo the edit by renaming the vertex to its original name.
     */
    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        vertex.setName(oldName);
    }

    /**
     * Redo the edit by renaming the vertex again.
     */
    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        vertex.setName(newName);
    }
}
