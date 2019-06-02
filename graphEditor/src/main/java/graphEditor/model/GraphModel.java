package graphEditor.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple graph class which contains a collection of vertices and edges.
 */
public class GraphModel {

    private List<GraphVertex> vertices;
    private List<GraphEdge> edges;

    public GraphModel() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    // TODO: Remove
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

    public boolean hasVertex(GraphVertex v) {
        return vertices.indexOf(v) != -1;
    }

    public boolean hasEdge(GraphEdge e) {
        return edges.indexOf(e) != -1;
    }

    public boolean hasEdge(GraphVertex v1, GraphVertex v2) throws RuntimeException {
        // Check that both v1 and v2 belong to the graph
        if (!hasVertex(v1) || !hasVertex(v2))
            throw new RuntimeException("Vertices must belong to the graph");

        for (GraphEdge edge : edges)
            if (edge.hasVertex(v1) && edge.hasVertex(v2))
                return true;

        return false;
    }

    public void addVertex(GraphVertex v) throws RuntimeException {
        // Check if v is already in the graph
        if (hasVertex(v))
            throw new RuntimeException("The introduced vertex is already in the graph");

        vertices.add(v);
    }

    public void addEdge(GraphVertex v1, GraphVertex v2) throws RuntimeException {
        // Check that an edge between v1 and v2 doesn't exist already
        if (hasEdge(v1, v2))
            throw new RuntimeException("An edge between v1 and v2 already exists");

        edges.add(new GraphEdge(v1, v2));
    }

    public int getVerticesCount() {
        return vertices.size();
    }

    public int getEdgesCount() {
        return edges.size();
    }

    public void removeVertex(GraphVertex v) throws RuntimeException {
        // Check that v belongs to the graph
        if (!hasVertex(v))
            throw new RuntimeException("Vertex must belong to the graph");

        // Remove every connected edge
        for (GraphEdge edge : getConnectedEdges(v))
            edges.remove(edge);

        vertices.remove(v);
    }

    public List<GraphEdge> getConnectedEdges(GraphVertex v) throws RuntimeException {
        // Check that v belongs to the graph
        if (!hasVertex(v))
            throw new RuntimeException("Vertex must belong to the graph");

        List<GraphEdge> list = new ArrayList<>();

        for (GraphEdge edge : edges)
            if (edge.hasVertex(v))
                list.add(edge);

        return list;
    }

    public void removeEdge(GraphEdge e) throws RuntimeException {
        // Check that e belongs to the graph
        if (!hasEdge(e))
            throw new RuntimeException("Edge must belong to the graph");

        edges.remove(e);
    }

    public void removeEdge(GraphVertex v1, GraphVertex v2) throws RuntimeException {
        GraphEdge e = findEdge(v1, v2);

        // Check that we found the edge
        if (e == null)
            throw new RuntimeException("There must be an edge between v1 and v2");

        edges.remove(e);
    }

    public GraphEdge findEdge(GraphVertex v1, GraphVertex v2) throws RuntimeException {
        // Check that both v1 and v2 belong to the graph
        if (!hasVertex(v1) || !hasVertex(v2))
            throw new RuntimeException("Vertices must belong to the graph");

        for (GraphEdge edge : edges)
            if (edge.hasVertex(v1) && edge.hasVertex(v2))
                return edge;

        return null;
    }

    // TODO
    public boolean conflictingName(GraphVertex v) {
        return false;
    }

    // TODO
    public boolean conflictingPosition(GraphVertex v) {
        return false;
    }

    // TODO
    public GraphVertex createNewVertex() {
        GraphVertex v = new GraphVertex();

        return v;
    }

    // TODO
    public void save (String filename) {

    }

    // TODO
    public void load (String filename) {

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.vertices.size()).append(" vertices, ").append(this.edges.size()).append(" edges").append('\n');
        this.vertices.forEach(vertex -> sb.append(vertex.toString()).append('\n'));
        this.edges.forEach(edge -> sb.append(edge.toString()).append('\n'));
        return sb.toString();
    }
}
