package graphEditor.controller.undoableEdits;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import java.awt.*;
import java.util.List;

public class VertexDragUndoableEdit extends AbstractUndoableEdit {

    private GraphModel graph;
    private List<GraphVertex> draggedVertices; // List of dragged vertices
    private Point snapshotLocation;

    public VertexDragUndoableEdit(GraphModel graph, List<GraphVertex> draggedVertices) {
        System.out.println("VertexDragUndoableEdit!");
        this.graph = graph;
        this.draggedVertices = draggedVertices;
    }

    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        /*
        for(GraphVertex vertexDragged : draggedVertices){
            System.out.println("a");
            System.out.println(vertexDragged.getName());
        }

         */

        for (GraphVertex vertex : graph.getVertices()) {
            for (GraphVertex vertexDragged : draggedVertices) {
                // set to old location
                if (vertex.equals(vertexDragged)) {
                    System.out.println(vertex.getName());
                    snapshotLocation = vertex.getSnapshotLocation();
                    vertex.snapshotLocation();
                    vertex.setLocation((int) snapshotLocation.getX(), (int) snapshotLocation.getY());
                }
            }
        }

        System.out.println("Undone!");
    }

    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        System.out.println("Redone!");
        for (GraphVertex vertex : graph.getVertices()) {
            for (GraphVertex vertexDragged : draggedVertices) {
                // set to old location
                if (vertex.equals(vertexDragged)) {
                    snapshotLocation = vertex.getSnapshotLocation();
                    vertex.snapshotLocation();
                    vertex.setLocation((int) snapshotLocation.getX(), (int) snapshotLocation.getY());
                }
            }
        }
    }


}
