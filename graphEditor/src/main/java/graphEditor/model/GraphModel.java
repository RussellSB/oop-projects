package graphEditor.model;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * A simple graph class which contains a collection of vertices and edges.
 * The graph is a observer of the vertices it contains.
 */
public class GraphModel extends Observable implements Observer {
    private List<GraphVertex> vertices;
    private List<GraphEdge> edges;
    private List<GraphVertex> selectedVertices;
    private List<GraphEdge> selectedEdges;
    private List<GraphTempEdge> tempEdges; //could be length 1 or 0

    /**
     * Creates an empty graph.
     */
    public GraphModel() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        selectedVertices = new ArrayList<>();
        selectedEdges = new ArrayList<>();
        tempEdges = new ArrayList<>();
    }

    /**
     * Gets the index of the specified vertex.
     */
    public int getVertexIndex(GraphVertex vertex) {
        return vertices.indexOf(vertex);
    }

    /**
     * Gets the list of edges.
     */
    public List<GraphEdge> getEdges() {
        return edges;
    }

    /**
     * Gets temporary edge if it exists
     */
    public List<GraphTempEdge> getTempEdges() {
        return tempEdges;
    }

    /**
     * Gets the list of vertices.
     */
    public List<GraphVertex> getVertices() {
        return vertices;
    }

    /**
     * Gets the list of selected edges.
     */
    public List<GraphEdge> getSelectedEdges() {
        return selectedEdges;
    }

    /**
     * Gets the list of selected vertices.
     */
    public List<GraphVertex> getSelectedVertices() {
        return selectedVertices;
    }


    /**
     * Gets the total number of vertices contained in the graph.
     */
    public int getVerticesCount() {
        return vertices.size();
    }

    /**
     * Gets the total number of edges contained in the graph.
     */
    public int getEdgesCount() {
        return edges.size();
    }

    /**
     * Gets the total number of selected vertices contained in the graph.
     */
    public int getSelectedVerticesCount() {
        return selectedVertices.size();
    }

    /**
     * Gets the total number of selected edges contained in the graph.
     */
    public int getSelectedEdgesCount() {
        return selectedEdges.size();
    }

    /**
     * Checks if there is something (edge or vertex) selected.
     */
    public boolean somethingIsSelected() {
        return selectedEdges.size() + selectedVertices.size() > 0;
    }

    /**
     * Gets a list with the edges connected to the specified vertex.
     *
     * @throws RuntimeException if the vertex v doesn't belong to the graph.
     */
    public List<GraphEdge> getConnectedEdges(GraphVertex v) throws RuntimeException {
        // Check that v belongs to the graph.
        if (!hasVertex(v))
            throw new RuntimeException("Vertex must belong to the graph");

        List<GraphEdge> list = new ArrayList<>();

        for (GraphEdge edge : edges)
            if (edge.hasVertex(v))
                list.add(edge);

        return list;
    }

    /**
     * Checks if the graph contains the specified vertex.
     */
    public boolean hasVertex(GraphVertex v) {
        return vertices.contains(v);
    }

    /**
     * Checks if the graph contains the specified edge.
     */
    public boolean hasEdge(GraphEdge e) {
        return edges.contains(e);
    }

    /**
     * Checks if the graph contains an edge between the specified vertices.
     *
     * @throws RuntimeException if the vertices don't belong to the graph.
     */
    public boolean hasEdge(GraphVertex v1, GraphVertex v2) throws RuntimeException {
        // Check that both v1 and v2 belong to the graph.
        if (!hasVertex(v1) || !hasVertex(v2))
            throw new RuntimeException("Vertices must belong to the graph");

        for (GraphEdge edge : edges)
            if (edge.hasVertex(v1) && edge.hasVertex(v2))
                return true;

        return false;
    }

    /**
     * Returns the edge that connects the specified vertices (null if it doesn't exist).
     *
     * @throws RuntimeException if the vertices don't belong to the graph.
     */
    public GraphEdge findEdge(GraphVertex v1, GraphVertex v2) throws RuntimeException {
        // Check that both v1 and v2 belong to the graph.
        if (!hasVertex(v1) || !hasVertex(v2))
            throw new RuntimeException("Vertices must belong to the graph");

        for (GraphEdge edge : edges)
            if (edge.hasVertex(v1) && edge.hasVertex(v2))
                return edge;

        return null;
    }

    /**
     * Adds a new vertex to the graph.
     *
     * @throws RuntimeException if the introduced vertex is already in the graph.
     */
    public void addVertex(GraphVertex v) throws RuntimeException {
        // Check if v is already in the graph.
        if (hasVertex(v))
            throw new RuntimeException("The introduced vertex is already in the graph");

        vertices.add(v);

        v.addObserver(this); // Graph is an observer of every of its vertices.

        setChanged();
        notifyObservers();
    }

    /**
     * Adds a new edge to the graph that connects the specified vertices.
     *
     * @throws RuntimeException if an edge between v1 and v2 already exists.
     * @throws RuntimeException if the vertices don't belong to the graph.
     */
    public void addEdge(GraphVertex v1, GraphVertex v2) throws RuntimeException {
        // Check that an edge between v1 and v2 doesn't exist already:
        if (hasEdge(v1, v2))
            throw new RuntimeException("An edge between v1 and v2 already exists");

        GraphEdge e = new GraphEdge(v1, v2);

        edges.add(e);

        setChanged();
        notifyObservers();
    }

    /**
     * Adds a new edge to the graph that connects the specified vertices.
     *
     * @throws RuntimeException if an edge between v1 and v2 already exists.
     * @throws RuntimeException if the vertices don't belong to the graph.
     */
    public void addTempEdge(GraphVertex v1, Point endPoint) throws RuntimeException {
        // Check that an edge between v1 and v2 doesn't exist already:
        if (tempEdges.size() > 1)
            throw new RuntimeException("More than one temporary edge shouldn't exist!");

        GraphTempEdge te = new GraphTempEdge(v1, endPoint);
        tempEdges.add(te);

        te.addObserver(this);

        setChanged();
        notifyObservers();
    }

    public void removeAllTempEdges() {
        tempEdges.clear();
        setChanged();
        notifyObservers();
    }

    /**
     * Removes the specified vertex and all the edges connected to it.
     *
     * @throws RuntimeException if the vertex doesn't belong to the graph.
     */
    public void deleteVertex(GraphVertex v) throws RuntimeException {
        // Check that v belongs to the graph:
        if (!hasVertex(v))
            throw new RuntimeException("Vertex must belong to the graph");

        // Remove every connected edge.
        List<GraphEdge> connectedEdges = getConnectedEdges(v);
        for (GraphEdge edge : connectedEdges)
            edges.remove(edge);

        vertices.remove(v);

        setChanged();
        notifyObservers();
    }

    /**
     * Removes the specified edge.
     *
     * @throws RuntimeException if the edge doesn't belong to the graph.
     */
    public void deleteEdge(GraphEdge e) throws RuntimeException {
        // Check that e belongs to the graph:
        if (!hasEdge(e))
            throw new RuntimeException("Edge must belong to the graph");

        edges.remove(e);

        setChanged();
        notifyObservers();
    }

    /**
     * Removes the edge that connects v1 and v2.
     *
     * @throws RuntimeException if there's no edge between v1 and v2.
     */
    public void deleteEdge(GraphVertex v1, GraphVertex v2) throws RuntimeException {
        GraphEdge e = findEdge(v1, v2);

        // Check that we found the edge.
        if (e == null)
            throw new RuntimeException("There must be an edge between v1 and v2");

        edges.remove(e);

        setChanged();
        notifyObservers();
    }

    /**
     * Deletes the selection (whether they are vertices or edges).
     */
    public void deleteSelected() {
        // Delete edges connected to the selected vertices:
        for (GraphVertex v : selectedVertices) {
            edges.removeAll(getConnectedEdges(v));
            selectedEdges.removeAll(getConnectedEdges(v));
        }

        // Delete selected vertices:
        vertices.removeAll(selectedVertices);
        selectedVertices.clear();

        // Delete selected edges:
        edges.removeAll(selectedEdges);
        selectedEdges.clear();

        setChanged();
        notifyObservers();
    }

    /**
     * Checks if there's another vertex with the same name as v.
     */
    public boolean conflictingName(GraphVertex v) {
        for (GraphVertex vertex : vertices)
            if (vertex.getName().equals(v.getName()) && vertex != v)
                return true;

        return false;
    }

    /**
     * Checks if there's another vertex with the same position as v.
     */
    public boolean conflictingLocation(GraphVertex v) {
        for (GraphVertex vertex : vertices)
            if (vertex.getX() == v.getX() && vertex.getY() == v.getY() && vertex != v)
                return true;

        return false;
    }

    /**
     * Creates a new vertex with the default name and position.
     * Name and position will be changed so they don't conflict with already existing vertices.
     */
    public void createNewVertex() {
        GraphVertex v = new GraphVertex();
        int i = 0;

        while (conflictingName(v))
            v.setName(GraphVertex.DEFAULT_NAME + " " + ++i);

        while (conflictingLocation(v))
            v.setLocation(v.getX() + 40, v.getY() + 30);

        vertices.add(v);

        v.addObserver(this);

        setChanged();
        notifyObservers();
    }

    /**
     * Saves a graph to a file with the given name.
     *
     * @throws IOException if there's a problem while creating/opening the file.
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
     * @throws IOException if there's a problem while opening the file.
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

            // For vertices with names that contain spaces.
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

        setChanged();
        notifyObservers();
    }

    /**
     * Checks the file format line by line using regular expressions.
     *
     * @throws IOException if there's a problem while opening the file.
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
            if (!line.matches("\\d+ \\d+ \\d+ \\d+ .+"))
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

    /**
     * Removes all vertices and edges.
     */
    public void reset() {
        vertices.clear();
        edges.clear();
        selectedVertices.clear();
        selectedEdges.clear();
        tempEdges.clear();

        setChanged();
        notifyObservers();
    }

    /**
     * Marks the specified vertex as selected as well as all the edges connected to it.
     *
     * @throws RuntimeException if the vertex doesn't belong to the graph.
     */
    public void select(GraphVertex v) throws RuntimeException {
        // Check that v belongs to the graph:
        if (!hasVertex(v))
            throw new RuntimeException("Vertex must belong to the graph");

        selectedVertices.add(v);

        // Select the connected edges:
        selectedEdges.addAll(getConnectedEdges(v));

        setChanged();
        notifyObservers();
    }

    /**
     * Marks the specified edge as selected.
     *
     * @throws RuntimeException if the edge doesn't belong to the graph.
     */
    public void select(GraphEdge e) throws RuntimeException {
        // Check that e belongs to the graph:
        if (!hasEdge(e))
            throw new RuntimeException("Edge must belong to the graph");

        selectedEdges.add(e);

        setChanged();
        notifyObservers();
    }

    /**
     * Marks the specified vertex as not selected.
     *
     * @throws RuntimeException if the vertex doesn't belong to the graph.
     */
    public void deSelect(GraphVertex v) throws RuntimeException {
        // Check that v belongs to the graph:
        if (!hasVertex(v))
            throw new RuntimeException("Vertex must belong to the graph");

        // Only if the vertex is indeed selected:
        if (selectedVertices.contains(v)) {
            // Deselect vertex:
            selectedVertices.remove(v);

            // Deselect connected edges:
            selectedEdges.removeAll(getConnectedEdges(v));
        }

        setChanged();
        notifyObservers();
    }

    /**
     * Marks the specified edge as not selected.
     *
     * @throws RuntimeException if the edge doesn't belong to the graph.
     */
    public void deSelect(GraphEdge e) throws RuntimeException {
        // Check that e belongs to the graph:
        if (!hasEdge(e))
            throw new RuntimeException("Edge must belong to the graph");

        selectedEdges.remove(e);

        setChanged();
        notifyObservers();
    }

    /**
     * Checks if the specified vertex is selected or not.
     *
     * @throws RuntimeException if the vertex doesn't belong to the graph.
     */
    public boolean isSelected(GraphVertex v) throws RuntimeException {
        // Check that v belongs to the graph:
        if (!hasVertex(v))
            throw new RuntimeException("Vertex must belong to the graph");

        return selectedVertices.contains(v);
    }

    /**
     * Checks if the specified edge is selected or not.
     *
     * @throws RuntimeException if the edge doesn't belong to the graph.
     */
    public boolean isSelected(GraphEdge e) throws RuntimeException {
        // Check that e belongs to the graph:
        if (!hasEdge(e))
            throw new RuntimeException("Edge must belong to the graph");

        return selectedEdges.contains(e);
    }

    /**
     * Marks all the vertices (and edges) as selected.
     */
    public void selectAll() {
        selectedVertices.addAll(vertices);
        selectedEdges.addAll(edges);

        setChanged();
        notifyObservers();
    }

    /**
     * Marks all the vertices and edges as not selected.
     */
    public void deselectAll() {
        selectedVertices.clear();
        selectedEdges.clear();

        setChanged();
        notifyObservers();
    }

    /**
     * Returns the string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(vertices.size())
                .append(" vertices, ")
                .append(edges.size())
                .append(" edges")
                .append('\n');
        vertices.forEach(vertex -> sb.append(vertex.toString()).append('\n'));
        edges.forEach(edge -> sb.append(edge.toString()).append('\n'));
        return sb.toString();
    }

    /**
     * If one of the vertices changed, the graph changes too and notifies its observers.
     */
    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
}
