package graphEditor.model;

import graphEditor.util.Selectable;

import java.util.Observable;

/**
 * A simple edge class which connects two vertices.
 */
public class GraphEdge extends Observable implements Selectable {
    private GraphVertex v1;
    private GraphVertex v2;
    private boolean isSelected = false;

    /**
     * Creates a new edge that connects vertices v1 and v2.
     *
     * @throws IllegalArgumentException if v1 and v2 are the same.
     */
    public GraphEdge(GraphVertex v1, GraphVertex v2) throws IllegalArgumentException {
        // Self-loops are not allowed
        if (v1 == v2)
            throw new IllegalArgumentException("Self-loops are not allowed: v1 and v2 must be different");

        this.v1 = v1;
        this.v2 = v2;
    }

    /**
     * Gets the first vertex.
     */
    public GraphVertex getV1() {
        return v1;
    }

    /**
     * Gets the second vertex.
     */
    public GraphVertex getV2() {
        return v2;
    }

    /**
     * Checks if the edge contains v.
     */
    public boolean hasVertex(GraphVertex v) {
        return this.v1 == v || this.v2 == v;
    }

    /**
     * Returns the string representation of the object.
     */
    @Override
    public String toString() {
        return this.v1.getName() + " " + this.v2.getName();
    }

    /**
     * Marks the edge as selected.
     */
    @Override
    public void select() {
        this.isSelected = true;
        setChanged();
        notifyObservers();
    }

    /**
     * Marks the edge as not selected.
     */
    @Override
    public void deselect() {
        this.isSelected = false;
        setChanged();
        notifyObservers();
    }

    /**
     * Checks if the edge is selected or not.
     */
    @Override
    public boolean isSelected() {
        return this.isSelected;
    }
}
