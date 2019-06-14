package graphEditor.controller.undoableEdits;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

/**
 * An UndoableEdit used to undo/redo the adding of a vertex.
 */
public class AddVertexUndoableEdit extends AbstractUndoableEdit {
    private GraphModel graph;
    private GraphVertex addedVertex;

    /**
     * Adds the vertex to the graph and saves it for future use.
     */
    public AddVertexUndoableEdit(GraphModel graph) {
        this.graph = graph;
        this.addedVertex = graph.createNewVertex();
    }

    /**
     * Undo the edit by deleting the added vertex.
     */
    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        graph.deleteVertex(addedVertex);

        graph.deselectAll();
    }

    /**
     * Redo the edit by adding the vertex again.
     * Selects the modified vertex.
     */
    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        graph.addVertex(addedVertex);

        graph.deselectAll();
        graph.select(addedVertex);
    }
}
