package graphEditor;

import graphEditor.model.GraphModel;
import graphEditor.model.GraphVertex;
import graphEditor.view.Window;

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
        Window window = new Window(graph);

        // When the program is executed by using "java graphEdit filename" the graph from the file "filename" has to be loaded and used.
        if (args.length == 1) {
            try {
                graph.load(args[0]);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(window.getFrame(), e.getMessage(), "IO error", JOptionPane.ERROR_MESSAGE);
            }
        }

        ////////////////////////////////////////////////////////////////////////
        //////////////////////////////// TESTING ///////////////////////////////
        // TODO: Remove all this when testing is finished.

        GraphVertex v1 = new GraphVertex(200, 150, 100, 100, "One");
        GraphVertex v2 = new GraphVertex(600, 150, 100, 100, "Two");
        GraphVertex v3 = new GraphVertex(400, 400, 100, 100, "Three");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        //graph.addEdge(v1, v2);
        //graph.addEdge(v2, v3);
        //graph.addEdge(v3, v1);
        //////////////////////////////// TESTING ///////////////////////////////
        ////////////////////////////////////////////////////////////////////////
    }
}