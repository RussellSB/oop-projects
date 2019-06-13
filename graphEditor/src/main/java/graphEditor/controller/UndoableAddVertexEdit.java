package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class UndoableAddVertexEdit extends AbstractUndoableEdit {

    private GraphModel graph;

    UndoableAddVertexEdit(GraphModel graph) {
        this.graph = graph;
        graph.createNewVertex();
    }

    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        graph.deleteVertex(graph.getVertices().get(graph.getVerticesCount() - 1)); // delete last
    }

    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        graph.createNewVertex(); // add again
    }
}
