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
    private List<GraphVertex> draggedVertices;
    private List<Point> initialLocations; // Initial location of the dragged vertices.
    private List<Point> newLocations; // New location of the dragged vertices.

    /**
     * Saves the draggedVertices, initialLocations and newLocations.
     */
    public VertexDragUndoableEdit(GraphModel graph, List<Point> initialLocations) {
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
        for (int i = 0; i < draggedVertices.size(); i++)
            draggedVertices.get(i).setLocation(initialLocations.get(i).x, initialLocations.get(i).y);
    }

    /**
     * Redo the drag by moving the vertices again to their new position.
     */
    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        for (int i = 0; i < draggedVertices.size(); i++)
            draggedVertices.get(i).setLocation(newLocations.get(i).x, newLocations.get(i).y);
    }
}
