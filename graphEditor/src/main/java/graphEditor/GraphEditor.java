package graphEditor;

import graphEditor.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphEditor {
    /**
     * Showcases the functionality of {@code save} and {@code load} methods.
     *
     * @param args Command line arguments. Unused for this example.
     */
    public static void main(String[] args) {
        GraphVertex v1 = new GraphVertex(0, 0, 30, 40, "one");
        GraphVertex v2 = new GraphVertex(100, 100, 30, 30, "two");
        GraphVertex v3 = new GraphVertex(50, 200, 80, 20, "three");

        GraphEdge eV1V2 = new GraphEdge(v1, v2);
        GraphEdge eV2V3 = new GraphEdge(v2, v3);

        GraphModel g1 = new GraphModel(Arrays.asList(v1, v2, v3), Arrays.asList(eV1V2, eV2V3));

        System.out.println("Saving graph:");
        System.out.println(g1);
        save(g1, "graph_test.txt");

        System.out.println("Loading graph:");
        System.out.println(load("graph_test.txt"));
    }

    /**
     * Saves a graph to a file with the given name.
     *
     * @param graph    The graph to save.
     * @param filename The name of the file to save to.
     */
    public static void save(GraphModel graph, String filename) {
        try (PrintWriter printWriter = new PrintWriter(new File(filename))) {
            printWriter.println(graph.getVertices().size() + " " + graph.getEdges().size());
            graph.getVertices().forEach(vertex -> printWriter.println(vertex.toString()));
            graph.getEdges().forEach(edge -> printWriter.println(graph.getVertexIndex(edge.getV1()) + " " + graph.getVertexIndex(edge.getV2())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a graph from a file.
     *
     * @param filename The name of the file to load.
     * @return The {@link GraphModel} object which was loaded, or null if an error occurred.
     */
    public static GraphModel load(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            String[] words = line.split(" ");
            int vertexCount = Integer.parseInt(words[0]);
            int edgeCount = Integer.parseInt(words[1]);
            List<GraphVertex> vertices = new ArrayList<>(vertexCount);
            List<GraphEdge> edges = new ArrayList<>(edgeCount);

            for (int i = 0; i < vertexCount; i++) {
                line = reader.readLine();
                words = line.split(" ");
                vertices.add(i, new GraphVertex(Integer.parseInt(words[0]), Integer.parseInt(words[1]), Integer.parseInt(words[2]), Integer.parseInt(words[3]), words[4]));
            }

            for (int i = 0; i < edgeCount; i++) {
                line = reader.readLine();
                words = line.split(" ");
                edges.add(i, new GraphEdge(vertices.get(Integer.parseInt(words[0])), vertices.get(Integer.parseInt(words[1]))));
            }

            return new GraphModel(vertices, edges);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}