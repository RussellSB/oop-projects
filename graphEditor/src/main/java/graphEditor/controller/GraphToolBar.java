package graphEditor.controller;

import graphEditor.controller.actions.*;
import graphEditor.model.GraphModel;
import graphEditor.view.GraphFrame;

import javax.swing.*;

/**
 * Tool Bar for the Graph Editor.
 */
public class GraphToolBar extends JToolBar {
    private GraphModel graph;
    private GraphFrame parentJFrame;

    /**
     * Creates the Tool Bar with all its buttons.
     */
    public GraphToolBar(GraphModel graph, GraphFrame parentJFrame) {
        super("Tool Bar");

        this.graph = graph;
        this.parentJFrame = parentJFrame;

        setFloatable(false);

        addButtons();
    }

    /**
     * Adds all the buttons to the Tool Bar.
     */
    private void addButtons() {
        add(new JButton(new NewGraphAction(graph)));

        add(new JSeparator(SwingConstants.VERTICAL)); // Separator

        add(new JButton(new OpenAction(graph, parentJFrame)));
        add(new JButton(new SaveAction(graph, parentJFrame)));

        add(new JSeparator(SwingConstants.VERTICAL)); // Separator

        add(new JButton(new UndoAction(graph)));
        add(new JButton(new RedoAction(graph)));

        add(new JSeparator(SwingConstants.VERTICAL)); // Separator

        add(new JButton(new AddVertexAction(graph)));
        add(new JButton(new AddEdgeAction(graph, parentJFrame)));

        add(new JSeparator(SwingConstants.VERTICAL)); // Separator

        add(new JButton(new RenameVertexAction(graph, parentJFrame)));

        add(new JSeparator(SwingConstants.VERTICAL)); // Separator

        add(new JButton(new SelectAllAction(graph)));

        add(new JSeparator(SwingConstants.VERTICAL)); // Separator

        add(new JButton(new DeleteAction(graph)));
    }
}
