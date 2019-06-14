package graphEditor;

import graphEditor.model.GraphModel;
import graphEditor.view.GraphWindow;

import javax.swing.*;
import java.io.IOException;

/**
 * Graph Editor Program.
 */
public class GraphEditor {
    /**
     * Main: starts the Graph Editor Program.
     */
    public static void main(String[] args) {
        GraphModel graph = new GraphModel();
        GraphWindow window = new GraphWindow(graph);

        // When the program is executed by using "java graphEdit filename" the graph from the file "filename" is loaded and used:
        if (args.length == 1) {
            try {
                graph.load(args[0]);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(window.getFrame(), e.getMessage(), "IO error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}