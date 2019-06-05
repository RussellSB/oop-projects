package graphEditor;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;
import graphEditor.view.GraphFrame;

import java.io.IOException;

public class GraphEditor {
    /**
     * Showcases the functionality of {@code save} and {@code load} methods.
     *
     * @param args Command line arguments. Unused for this example.
     */
    public static void main(String[] args) {
        GraphModel graph = new GraphModel();

        // When the program is executed by using "java graphEdit filename" the graph from the file "filename" has to be loaded and used.
        if (args.length == 1) {
            try {
                graph.load(args[0]);
            } catch (IOException e) { // TODO: Improve error handling
                e.printStackTrace(); // TODO: Replace with Error Dialog Window
            }
        }

        //g.fillRoundRect(200, 150, 100, 100, 20, 20);
        //g.fillRoundRect(600, 150, 100, 100, 20, 20);

        GraphVertex v1 = new GraphVertex(200, 150, 100, 100, "One");
        GraphVertex v2 = new GraphVertex(600, 150, 100, 100, "Two");
        GraphVertex v3 = new GraphVertex(400, 400, 100, 100, "Three");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        //graph.createNewVertex();
        //graph.createNewVertex();
        //graph.createNewVertex();
        //graph.createNewVertex();

        graph.addEdge(v1, v2);
        graph.addEdge(v2, v3);
        graph.addEdge(v3, v1);

        //System.out.println(graph);

        new GraphFrame(graph);



        /*GraphVertex v1 = new GraphVertex(0, 0, 30, 40, "one");
        GraphVertex v2 = new GraphVertex(100, 100, 30, 30, "two");
        GraphVertex v3 = new GraphVertex(50, 200, 80, 20, "three");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.createNewVertex();
        graph.createNewVertex();
        graph.createNewVertex();

        graph.addEdge(v1, v2);
        graph.addEdge(v2, v3);

        System.out.println("Saving graph:");
        System.out.println(graph);
        try {
            graph.save("graph_test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Loading graph:");
        try {
            graph.load("graph_test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(graph);*/
    }
}