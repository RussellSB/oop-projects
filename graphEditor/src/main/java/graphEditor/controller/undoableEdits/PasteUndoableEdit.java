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
 * An UndoableEdit used to undo/redo the pasting of vertices and edges.
 */
public class PasteUndoableEdit extends AbstractUndoableEdit {
    private GraphModel graph;
    private List<GraphVertex> pastedVertices;
    private List<GraphEdge> pastedEdges;

    /**
     * Pastes the vertices and edges and save them future use.
     */
    public PasteUndoableEdit(GraphModel graph, List<GraphVertex> pastedVertices, List<GraphEdge> pastedEdges) {
        this.graph = graph;

        this.pastedVertices = new ArrayList<>();
        this.pastedVertices.addAll(pastedVertices);

        this.pastedEdges = new ArrayList<>();
        this.pastedEdges.addAll(pastedEdges);

        graph.paste(pastedVertices, pastedEdges);
    }

    /**
     * Undo the pasting by deleting the pasted vertices and edges.
     */
    @Override
    public void undo() throws CannotUndoException {
        super.undo();

        for (GraphEdge e : pastedEdges)
            graph.deleteEdge(e);

        for (GraphVertex v : pastedVertices)
            graph.deleteVertex(v);

        graph.deselectAll();
    }

    /**
     * Redo the pasting by adding the pasted vertices and edges again.
     */
    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        graph.paste(pastedVertices, pastedEdges);
    }
}
