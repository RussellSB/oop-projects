package graphEditor.model;

/**
 * A simple edge class which connects two vertices.
 */
public class GraphEdge {
    private GraphVertex v1;
    private GraphVertex v2;

    public GraphEdge(GraphVertex v1, GraphVertex v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public GraphVertex getV1() {
        return v1;
    }

    public GraphVertex getV2() {
        return v2;
    }

    public String toString() {
        return this.v1.getName() + " " + this.v2.getName();
    }
}
