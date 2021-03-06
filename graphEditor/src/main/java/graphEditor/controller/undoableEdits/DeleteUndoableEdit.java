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
 * An UndoableEdit used to undo/redo the deletion of vertices and edges.
 */
public class DeleteUndoableEdit extends AbstractUndoableEdit {
    private GraphModel graph;
    private List<GraphVertex> deletedVertices;
    private List<GraphEdge> deletedEdges;

    /**
     * Deletes all the selected vertices and edges after storing them.
     */
    public DeleteUndoableEdit(GraphModel graph) {
        this.graph = graph;

        this.deletedVertices = new ArrayList<>();
        this.deletedVertices.addAll(graph.getSelectedVertices());

        this.deletedEdges = new ArrayList<>();
        this.deletedEdges.addAll(graph.getSelectedEdges());

        // We also add the edges connected to the deletedVertices that aren't in deletedVertices yet.
        for (GraphVertex vertex : this.deletedVertices)
            for (GraphEdge edge : graph.getConnectedEdges(vertex))
                if (!this.deletedEdges.contains(edge))
                    this.deletedEdges.add(edge);

        graph.deleteSelection();
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
     * Redo the deletion by deleting the deletedVertices and the deletedEdges again.
     */
    @Override
    public void redo() throws CannotRedoException {
        super.redo();

        for (GraphEdge edge : deletedEdges)
            graph.deleteEdge(edge);

        for (GraphVertex vertex : deletedVertices)
            graph.deleteVertex(vertex);

        graph.deselectAll();
    }
}
