package graphEditor.view;

import graphEditor.controller.SelectionController;
import graphEditor.controller.VertexDragger;
import graphEditor.model.GraphModel;

/**
 * Window for the GraphEditor. Contains the GraphFrame that contains the GraphPanel.
 */
public class GraphWindow {
    private GraphFrame frame;

    /**
     * Creates the GraphWindow creating the GraphFrame, the SelectionController and the VertexDragger.
     */
    public GraphWindow(GraphModel graph) {
        this.frame = new GraphFrame(graph);

        new SelectionController(graph, frame);

        new VertexDragger(graph, frame.getPanel());

        // TODO: Edge creator (add to comments too)
    }

    /**
     * Gets the GraphFrame.
     */
    public GraphFrame getFrame() {
        return frame;
    }
}
