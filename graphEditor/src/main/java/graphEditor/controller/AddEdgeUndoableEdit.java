package graphEditor.controller;

import graphEditor.model.GraphEdge;
import graphEditor.model.GraphModel;
import graphEditor.view.GraphFrame;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class AddEdgeUndoableEdit extends AbstractUndoableEdit {

    private GraphModel graph;
    private GraphEdge undoneEdge;

    AddEdgeUndoableEdit(GraphModel graph, GraphFrame parentJFrame) {
        this.graph = graph;
        new AddEdgeListener(graph, parentJFrame);
    }

    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        undoneEdge = graph.getEdges().get(graph.getEdgesCount() - 1);
        graph.deleteEdge(undoneEdge); // delete last
    }

    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        graph.addEdge(undoneEdge); // add back the undone edge
    }
}
