package graphEditor.model;

import java.util.List;

/**
 * A simple graph class which contains a collection of vertices and edges.
 */
public class GraphModel {

    private List<GraphVertex> vertices;
    private List<GraphEdge> edges;

    public GraphModel(List<GraphVertex> vertices, List<GraphEdge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public int getVertexIndex(GraphVertex vertex) {
        return this.vertices.indexOf(vertex);
    }

    public List<GraphEdge> getEdges() {
        return edges;
    }

    public List<GraphVertex> getVertices() {
        return vertices;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.vertices.size()).append(" vertices, ").append(this.edges.size()).append(" edges").append('\n');
        this.vertices.forEach(vertex -> sb.append(vertex.toString()).append('\n'));
        this.edges.forEach(edge -> sb.append(edge.toString()).append('\n'));
        return sb.toString();
    }
}
