package graphEditor;

import graphEditor.controller.SelectionController;
import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;
import graphEditor.view.GraphFrame;

import javax.swing.*;
import java.io.IOException;

public class GraphEditor {
    /**
     * Showcases the functionality of {@code save} and {@code load} methods.
     *
     * @param args Command line arguments. Unused for this example.
     */
    public static void main(String[] args) {
        GraphModel graph = new GraphModel();

        GraphFrame frame = new GraphFrame(graph);

        new SelectionController(graph, frame.getPanel());

        // When the program is executed by using "java graphEdit filename" the graph from the file "filename" has to be loaded and used.
        if (args.length == 1) {
            try {
                graph.load(args[0]);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage(), "IO error", JOptionPane.ERROR_MESSAGE);
            }
        }

        ////////////////////////////////////////////////////////////////////////
        //////////////////////////////// TESTING ///////////////////////////////
        // TODO: Remove all this when testing is finished.

        // Sleep 1 second
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        GraphVertex v1 = new GraphVertex(200, 150, 100, 100, "One");
        GraphVertex v2 = new GraphVertex(600, 150, 100, 100, "Two");
        GraphVertex v3 = new GraphVertex(400, 400, 100, 100, "Three");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        // Sleep 1 second
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        v1.setName("ONEEE");
        v2.setName("TWOOO");
        v3.setName("THREEEE");

        graph.addEdge(v1, v2);
        graph.addEdge(v2, v3);
        graph.addEdge(v3, v1);

        /*
        // Sleep 1 second
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        graph.select(v1);
        graph.select(graph.findEdge(v2, v2));

        graph.createNewVertex();
        graph.createNewVertex();

        // Sleep 5 seconds
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        System.out.println(graph);
        */
        //////////////////////////////// TESTING ///////////////////////////////
        ////////////////////////////////////////////////////////////////////////
    }
}