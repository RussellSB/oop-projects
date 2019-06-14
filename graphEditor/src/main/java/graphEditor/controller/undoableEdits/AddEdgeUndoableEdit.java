package graphEditor.controller.undoableEdits;

import graphEditor.model.GraphEdge;
import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

/**
 * An UndoableEdit used to undo/redo the adding of an edge.
 */
public class AddEdgeUndoableEdit extends AbstractUndoableEdit {
    private GraphModel graph;
    private GraphEdge addedEdge;

    /**
     * Adds the edge to the graph and saves it for future use.
     */
    public AddEdgeUndoableEdit(GraphModel graph, GraphVertex v1, GraphVertex v2) throws RuntimeException {
        this.graph = graph;
        this.addedEdge = graph.addEdge(v1, v2);
    }

    /**
     * Undo the edit by deleting the added edge.
     */
    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        graph.deleteEdge(addedEdge);
    }

    /**
     * Redo the edit by adding the edge again.
     */
    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        graph.addEdge(addedEdge);
    }
}
