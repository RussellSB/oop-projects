package graphEditor.model;

/**
 * A simple edge class which connects two vertices.
 */
public class GraphEdge {
    private GraphVertex v1;
    private GraphVertex v2;

    /**
     *
     * @param v1
     * @param v2
     * @throws IllegalArgumentException if v1 and v2 are the same
     */
    public GraphEdge(GraphVertex v1, GraphVertex v2) throws IllegalArgumentException {
        // Self-loops are not allowed
        if (v1 == v2)
            throw new IllegalArgumentException("Self-loops are not allowed: v1 and v2 must be different");

        this.v1 = v1;
        this.v2 = v2;
    }

    public GraphVertex getV1() {
        return v1;
    }

    public GraphVertex getV2() {
        return v2;
    }

    public boolean hasVertex(GraphVertex v) {
        return this.v1 == v || this.v2 == v;
    }

    @Override
    public String toString() {
        return this.v1.getName() + " " + this.v2.getName();
    }
}
