package graphEditor.view;

import graphEditor.controller.SelectionController;
import graphEditor.controller.VertexDragger;
import graphEditor.model.GraphModel;

/**
 * Simply creates new window with a new frame, with a graph fed into it
 */
public class GraphWindow {

    private GraphFrame frame;

    public GraphWindow(GraphModel graph) {
        frame = new GraphFrame(graph);
        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
        new SelectionController(graph, frame);
        new VertexDragger(graph, frame.getPanel());
    }

    public GraphFrame getFrame() {
        return frame;
    }
}
