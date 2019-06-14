package graphEditor.controller.undoableEdits;

import graphEditor.model.GraphEdge;
import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import java.util.ArrayList;
import java.util.List;

/**
 * An UndoableEdit used to undo/redo the creation of a new graph (and the deletion of the previous one).
 */
public class NewGraphUndoableEdit extends AbstractUndoableEdit {
    private GraphModel graph;
    private List<GraphVertex> deletedVertices;
    private List<GraphEdge> deletedEdges;

    /**
     * Creates a new graph after storing the deletedVertices and the deletedEdges.
     */
    public NewGraphUndoableEdit(GraphModel graph) {
        this.graph = graph;

        this.deletedVertices = new ArrayList<>();
        this.deletedVertices.addAll(graph.getVertices());

        this.deletedEdges = new ArrayList<>();
        this.deletedEdges.addAll(graph.getEdges());

        graph.reset();
    }

    /**
     * Undo the deletion by adding back the deletedVertices and the deletedEdges.
     */
    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        graph.paste(deletedVertices, deletedEdges);
    }

    /**
     * Redo the deletion by creating a new graph again.
     */
    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        graph.reset();
    }
}
