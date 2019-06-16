package graphEditor.model;

import graphEditor.controller.CopyPasteManager;
import javafx.scene.shape.Line;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.UndoManager;
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
    private boolean addingEdgeMode; // Flag that indicates if we are in the middle of the process of adding a new edge.
    private Line addingEdgeLine; // Line used to add edges in a visual way.
    private UndoManager undoManager;
    private CopyPasteManager copyPasteManager;

    /**
     * Creates an empty graph.
     */
    public GraphModel() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.selectedVertices = new ArrayList<>();
        this.selectedEdges = new ArrayList<>();
        this.addingEdgeMode = false;
        this.addingEdgeLine = new Line();
        this.undoManager = new UndoManager();
        this.copyPasteManager = new CopyPasteManager(this);
    }

    /**
     * Gets the list of edges.
     */
    public List<GraphEdge> getEdges() {
        return edges;
    }

    /**
     * Gets the list of vertices.
     */
    public List<GraphVertex> getVertices() {
        return vertices;
    }

    /**
     * Gets the list of selected vertices.
     */
    public List<GraphVertex> getSelectedVertices() {
        return selectedVertices;
    }

    /**
     * Gets the list of selected edges.
     */
    public List<GraphEdge> getSelectedEdges() {
        return selectedEdges;
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
     * Gets a list with the edges connected to the specified vertex.
     *
     * @throws RuntimeException if the vertex v doesn't belong to the graph.
     */
    public List<GraphEdge> getConnectedEdges(GraphVertex v) throws RuntimeException {
        // Check that v belongs to the graph:
        if (!hasVertex(v))
            throw new RuntimeException("Vertex must belong to the graph");

        List<GraphEdge> list = new ArrayList<>();

        for (GraphEdge edge : edges)
            if (edge.hasVertex(v))
                list.add(edge);

        return list;
    }

    /**
     * Gets the Undo Manager.
     */
    public UndoManager getUndoManager() {
        return this.undoManager;
    }

    /**
     * Gets the Copy Paste Manager.
     */
    public CopyPasteManager getCopyPasteManager() {
        return copyPasteManager;
    }

    /**
     * Checks if the graph contains the specified vertex.
     */
    private boolean hasVertex(GraphVertex v) {
        return vertices.contains(v);
    }

    /**
     * Checks if the graph doesn't contain the specified edge.
     */
    private boolean hasNotEdge(GraphEdge e) {
        return !edges.contains(e);
    }

    /**
     * Checks if the graph contains an edge between the specified vertices.
     *
     * @throws RuntimeException if the vertices don't belong to the graph.
     */
    private boolean hasEdge(GraphVertex v1, GraphVertex v2) throws RuntimeException {
        // Check that both v1 and v2 belong to the graph:
        if (!hasVertex(v1) || !hasVertex(v2))
            throw new RuntimeException("Vertices must belong to the graph");

        for (GraphEdge edge : edges)
            if (edge.hasVertex(v1) && edge.hasVertex(v2) && v1 != v2)
                return true;

        return false;
    }

    /**
     * Checks if the graph is in "Adding Edge Mode"; checks if we are in the middle of the process of adding a new edge.
     */
    public boolean isAddingEdgeMode() {
        return addingEdgeMode;
    }

    /**
     * Gets the addingEdgeLine; line used to add edges in a visual way.
     */
    public Line getAddingEdgeLine() {
        return addingEdgeLine;
    }

    /**
     * Sets the value of addingEdgeMode.
     */
    public void setAddingEdgeMode(boolean addingEdgeMode) {
        this.addingEdgeMode = addingEdgeMode;

        setChanged();
        notifyObservers();
    }

    /**
     * Checks if there's another vertex with the same name as v.
     */
    private boolean conflictingName(GraphVertex v) {
        for (GraphVertex vertex : vertices)
            if (vertex.getName().equals(v.getName()) && vertex != v)
                return true;

        return false;
    }

    /**
     * Checks if there's another vertex with the same position as v.
     */
    private boolean conflictingLocation(GraphVertex v) {
        for (GraphVertex vertex : vertices)
            if (vertex.getX() == v.getX() && vertex.getY() == v.getY() && vertex != v)
                return true;

        return false;
    }

    /**
     * Adds a new vertex to the graph.
     *
     * @throws RuntimeException if the introduced vertex is already in the graph.
     */
    public void addVertex(GraphVertex v) throws RuntimeException {
        // Check if v is already in the graph:
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
     * @return the just created edge.
     * @throws RuntimeException if an edge between v1 and v2 already exists.
     * @throws RuntimeException if the vertices don't belong to the graph.
     */
    public GraphEdge addEdge(GraphVertex v1, GraphVertex v2) throws RuntimeException {
        // Check that an edge between v1 and v2 doesn't exist already:
        if (hasEdge(v1, v2))
            throw new RuntimeException("You already have an edge there!");

        GraphEdge e = new GraphEdge(v1, v2);

        edges.add(e);

        setChanged();
        notifyObservers();

        return e;
    }

    /**
     * Adds the edge to the graph.
     *
     * @throws RuntimeException if an edge between v1 and v2 already exists.
     */
    public void addEdge(GraphEdge edge) throws RuntimeException {
        // Check that an edge between v1 and v2 doesn't exist already:
        if (hasEdge(edge.getV1(), edge.getV2()))
            throw new RuntimeException("You already have an edge there!");

        edges.add(edge);

        setChanged();
        notifyObservers();
    }

    /**
     * Allows to add a list of vertices and edges to the graph.
     * Useful for the paste operation.
     * These vertices and edges will be selected after being added.
     */
    public void paste(List<GraphVertex> vertices, List<GraphEdge> edges) {
        deselectAll();

        for (GraphVertex v : vertices) {
            addVertex(v);
            select(v);
        }

        for (GraphEdge e : edges) {
            addEdge(e);
            select(e);
        }
    }

    /**
     * Creates a new vertex with the default name and size in the specified position.
     * Name and position will be changed so they don't conflict with already existing vertices.
     * The new vertex will be selected after being added.
     *
     * @return the just created vertex.
     */
    public GraphVertex createNewVertex(int x, int y) {
        GraphVertex v = new GraphVertex();
        v.setLocation(x, y);

        // Change the name if there is a conflict with another vertex:
        int i = 0;
        while (conflictingName(v))
            v.setName(GraphVertex.DEFAULT_NAME + " " + ++i);

        // Change the location if there is a conflict with another vertex:
        while (conflictingLocation(v))
            v.setLocation(v.getX() + 40, v.getY() + 30);

        vertices.add(v);

        v.addObserver(this);

        // Select the newly created vertex:
        deselectAll();
        select(v);

        setChanged();
        notifyObservers();

        return v;
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

        // Remove every connected edge:
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
        if (hasNotEdge(e))
            throw new RuntimeException("Edge must belong to the graph");

        edges.remove(e);

        setChanged();
        notifyObservers();
    }

    /**
     * Deletes the selection (whether they are vertices or edges).
     */
    public void deleteSelection() {
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

            // For vertices with names that contain spaces:
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

        this.selectedVertices.clear();
        this.selectedEdges.clear();
        this.vertices = vertices;
        this.edges = edges;

        // Set the graph to observe the new vertices:
        for (GraphVertex vertex : vertices)
            vertex.addObserver(this);

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
     * Checks the file format line by line using regular expressions.
     *
     * @throws IOException if there's a problem while opening the file.
     */
    private boolean fileFormatIsOK(String filename) throws IOException {
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

        // Select the vertex if it's not selected yet:
        if (!selectedVertices.contains(v))
            selectedVertices.add(v);

        // Select the connected edges if they are not selected yet:
        for (GraphEdge e : getConnectedEdges(v))
            if (!selectedEdges.contains(e))
                selectedEdges.add(e);

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
        if (hasNotEdge(e))
            throw new RuntimeException("Edge must belong to the graph");

        // Select the edge if it's not selected yet.
        if (!selectedEdges.contains(e))
            selectedEdges.add(e);

        setChanged();
        notifyObservers();
    }

    /**
     * Marks the specified vertex as not selected as well as the edges connected to it.
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
        if (hasNotEdge(e))
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
        if (hasNotEdge(e))
            throw new RuntimeException("Edge must belong to the graph");

        return selectedEdges.contains(e);
    }

    /**
     * Checks if there is something (edge or vertex) selected.
     */
    public boolean somethingIsSelected() {
        return selectedEdges.size() + selectedVertices.size() > 0;
    }

    /**
     * Marks all the vertices (and edges) as selected.
     */
    public void selectAll() {
        selectedVertices.clear();
        selectedVertices.addAll(vertices);

        selectedEdges.clear();
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
     * Sets the start point of the addingEdgeLine.
     */
    public void setAddingEdgeLineStart(int startX, int startY) {
        addingEdgeLine.setStartX(startX);
        addingEdgeLine.setStartY(startY);

        setChanged();
        notifyObservers();
    }

    /**
     * Sets the end point of the addingEdgeLine.
     */
    public void setAddingEdgeLineEnd(int endX, int endY) {
        addingEdgeLine.setEndX(endX);
        addingEdgeLine.setEndY(endY);

        setChanged();
        notifyObservers();
    }

    /**
     * Adds an UndoableEdit to the undoManager.
     */
    public void addUndoableEdit(AbstractUndoableEdit edit) {
        undoManager.addEdit(edit);

        setChanged();
        notifyObservers();
    }

    /**
     * Undo the last edit.
     */
    public void undo() {
        undoManager.undo();

        setChanged();
        notifyObservers();
    }

    /**
     * Redo the last undo.
     */
    public void redo() {
        undoManager.redo();

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
