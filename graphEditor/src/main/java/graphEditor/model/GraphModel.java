package graphEditor.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * A simple graph class which contains a collection of vertices and edges.
 * TODO: Add setChanged(); & notifyObservers(); to all methods that modify the graph or its components
 */
public class GraphModel extends Observable {
    private List<GraphVertex> vertices;
    private List<GraphEdge> edges;

    public GraphModel() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
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

    public int getVerticesCount() {
        return vertices.size();
    }

    public int getEdgesCount() {
        return edges.size();
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

    public GraphEdge findEdge(GraphVertex v1, GraphVertex v2) throws RuntimeException {
        // Check that both v1 and v2 belong to the graph
        if (!hasVertex(v1) || !hasVertex(v2))
            throw new RuntimeException("Vertices must belong to the graph");

        for (GraphEdge edge : edges)
            if (edge.hasVertex(v1) && edge.hasVertex(v2))
                return edge;

        return null;
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

    public void removeVertex(GraphVertex v) throws RuntimeException {
        // Check that v belongs to the graph
        if (!hasVertex(v))
            throw new RuntimeException("Vertex must belong to the graph");

        // Remove every connected edge
        for (GraphEdge edge : getConnectedEdges(v))
            edges.remove(edge);

        vertices.remove(v);
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

    private boolean conflictingName(GraphVertex v) {
        for (GraphVertex vertex : vertices)
            if (vertex.getName().equals(v.getName()) && vertex != v)
                return true;

        return false;
    }

    private boolean conflictingLocation(GraphVertex v) {
        for (GraphVertex vertex : vertices)
            if (vertex.getX() == v.getX() && vertex.getY() == v.getY() && vertex != v)
                return true;

        return false;
    }

    public void createNewVertex() {
        GraphVertex v = new GraphVertex();
        int i = 0;

        while (conflictingName(v))
            v.setName(GraphVertex.DEFAULT_NAME + " " + ++i);

        while (conflictingLocation(v))
            v.setLocation(v.getX() + 1, v.getY() + 1);

        vertices.add(v);
    }

    /**
     * Saves a graph to a file with the given name.
     *
     * @param filename
     */
    public void save(String filename) throws IOException {
        PrintWriter printWriter = new PrintWriter(new File(filename));

        printWriter.println(vertices.size() + " " + edges.size());
        vertices.forEach(vertex -> printWriter.println(vertex.toString()));
        edges.forEach(edge -> printWriter.println(vertices.indexOf(edge.getV1()) + " " + vertices.indexOf(edge.getV2())));
        printWriter.close();
    }

    /**
     * Loads a graph from the file with the given name.
     *
     * @param filename
     */
    public void load(String filename) throws IOException {
        if (!fileFormatIsOK(filename))
            throw new IOException("Incompatible file format");

        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String line = reader.readLine();
        String[] words = line.split(" ");
        int vertexCount = Integer.parseInt(words[0]);
        int edgeCount = Integer.parseInt(words[1]);
        List<GraphVertex> vertices = new ArrayList<>(vertexCount);
        List<GraphEdge> edges = new ArrayList<>(edgeCount);

        for (int i = 0; i < vertexCount; i++) {
            line = reader.readLine();
            words = line.split(" ");
            StringBuilder name = new StringBuilder(words[4]);

            // For vertices with names that contain spaces
            for (int j = 5; j < words.length; j++) {
                name.append(" ");
                name.append(words[j]);
            }

            vertices.add(i, new GraphVertex(Integer.parseInt(words[0]), Integer.parseInt(words[1]), Integer.parseInt(words[2]), Integer.parseInt(words[3]), name.toString()));
        }

        for (int i = 0; i < edgeCount; i++) {
            line = reader.readLine();
            words = line.split(" ");
            edges.add(i, new GraphEdge(vertices.get(Integer.parseInt(words[0])), vertices.get(Integer.parseInt(words[1]))));
        }

        reader.close();
        this.vertices = vertices;
        this.edges = edges;
    }

    /**
     * Checks the file format line by line using regular expressions
     * @param filename
     * @return
     * @throws IOException
     */
    public boolean fileFormatIsOK(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String line = reader.readLine();

        if (!line.matches("\\d+ \\d+"))
            return false;

        String[] words = line.split(" ");
        int vertexCount = Integer.parseInt(words[0]);
        int edgeCount = Integer.parseInt(words[1]);

        for (int i = 0; i < vertexCount; i++) {
            line = reader.readLine();
            if (!line.matches("\\d+ \\d+ \\d+ \\d+ .*"))
                return false;
        }

        for (int i = 0; i < edgeCount; i++) {
            line = reader.readLine();
            if (!line.matches("\\d+ \\d+"))
                return false;
        }

        reader.close();
        return true;
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
