package graphEditor.controller;

import graphEditor.controller.undoableEdits.DeleteUndoableEdit;
import graphEditor.controller.undoableEdits.PasteUndoableEdit;
import graphEditor.model.GraphEdge;
import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Copy Paste Manager: Allows to copy, cut and paste vertices and edges.
 */
public class CopyPasteManager extends Observable implements Observer {
    private static final int COPIED_LOCATION_OFFSET = 30; // Offset added to the location of the pasted vertices.
    private GraphModel graph;
    private boolean copied = false; // Flag that indicates if the CopyPasteManager has copied something or not.
    private List<GraphVertex> copiedVertices;
    private List<GraphEdge> copiedEdges;
    private List<GraphVertex> newVertices;
    private List<GraphEdge> newEdges;

    /**
     * Creates the CopyPasteManager.
     */
    CopyPasteManager(GraphModel graph) {
        this.graph = graph;
        graph.addObserver(this);

        copiedVertices = new ArrayList<>();
        newVertices = new ArrayList<>();
        copiedEdges = new ArrayList<>();
        newEdges = new ArrayList<>();
    }

    /**
     * Copy.
     */
    public void copy() {
        if (!readyToCopyCut())
            return;

        copiedVertices.clear();
        copiedEdges.clear();

        // Copy the selected vertices:
        copiedVertices.addAll(graph.getSelectedVertices());

        // Copy only the selected edges that connect copied vertices:
        for (GraphEdge e : graph.getSelectedEdges())
            if (copiedVertices.contains(e.getV1()) &&
                    copiedVertices.contains(e.getV2()) &&
                    !copiedEdges.contains(e)
            )
                copiedEdges.add(e);

        copied = true;

        setChanged();
        notifyObservers();
    }

    /**
     * Cut.
     */
    public void cut() {
        if (!readyToCopyCut())
            return;

        // First, copy the selection:
        copy();

        // Then, delete the selection:
        graph.addUndoableEdit(new DeleteUndoableEdit(graph));
    }

    /**
     * Paste.
     */
    public void paste() {
        if (!readyToPaste())
            return;

        // Create a new version of the copied vertices:
        for (GraphVertex v : copiedVertices)
            newVertices.add(new GraphVertex(v.getX() + COPIED_LOCATION_OFFSET,
                    v.getY() + COPIED_LOCATION_OFFSET,
                    v.getWidth(),
                    v.getHeight(),
                    v.getName())
            );

        // Create a new version of the copied edges:
        for (GraphEdge e : copiedEdges) {
            int v1Index = copiedVertices.indexOf(e.getV1());
            int v2Index = copiedVertices.indexOf(e.getV2());

            newEdges.add(new GraphEdge(newVertices.get(v1Index), newVertices.get(v2Index)));
        }

        // Add the new vertices and edges to the graph:
        graph.addUndoableEdit(new PasteUndoableEdit(graph, newVertices, newEdges));

        // Prepare vertices to be pasted again:
        copiedVertices.clear();
        copiedVertices.addAll(newVertices);
        newVertices.clear();

        // Prepare edges to be pasted again:
        copiedEdges.clear();
        copiedEdges.addAll(newEdges);
        newEdges.clear();
    }

    /**
     * Checks if the CopyPasteManager is ready to paste (there is something copied).
     */
    public boolean readyToPaste() {
        return copied;
    }

    /**
     * Checks if the CopyPasteManager is ready to copy or cut (at least one vertex is selected).
     */
    public boolean readyToCopyCut() {
        return graph.getSelectedVerticesCount() >= 1;
    }

    /**
     * If the GraphModel changes the CopyPasteManager changes too and notifies its observers.
     */
    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
}
