package graphEditor.controller.undoableEdits;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * An UndoableEdit used to undo/redo the dragging of a vertices.
 * WARNING: This class assumes that the vertices have already been moved to their final location.
 */
public class VertexDragUndoableEdit extends AbstractUndoableEdit {
    private GraphModel graph;
    private List<GraphVertex> draggedVertices;
    private List<Point> initialLocations; // Initial location of the dragged vertices.
    private List<Point> newLocations; // New location of the dragged vertices.

    /**
     * Saves the draggedVertices, initialLocations and newLocations.
     */
    public VertexDragUndoableEdit(GraphModel graph, List<Point> initialLocations) {
        this.graph = graph;
        this.draggedVertices = new ArrayList<>();
        this.initialLocations = new ArrayList<>();
        this.newLocations = new ArrayList<>();

        this.draggedVertices.addAll(graph.getSelectedVertices());
        this.initialLocations.addAll(initialLocations);

        // Save the new position of the dragged vertices:
        for (GraphVertex vertex : draggedVertices)
            newLocations.add(new Point(vertex.getX(), vertex.getY()));
    }

    /**
     * Undo the drag by moving the vertices back to their original position.
     */
    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        setLocation(initialLocations);
    }

    /**
     * Redo the drag by moving the vertices again to their new position.
     */
    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        setLocation(newLocations);
    }

    /**
     * Auxiliary method to avoid duplicated code.
     * Moves the draggedVertices to their locations.
     */
    private void setLocation(List<Point> locations) {
        graph.deselectAll();

        for (int i = 0; i < draggedVertices.size(); i++) {
            GraphVertex v = draggedVertices.get(i);
            v.setLocation(locations.get(i).x, locations.get(i).y);
            graph.select(v);
        }
    }
}
