package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Menu bar for the graph editor.
 */
public class GraphMenuBar extends JMenuBar {
    private GraphModel graph;
    private JFrame parentJFrame;

    /**
     * Creates a new menu bar with all its menus.
     */
    public GraphMenuBar(GraphModel graph, JFrame parentJFrame) {
        this.graph = graph;
        this.parentJFrame = parentJFrame;

        this.addFileMenu();
        this.addEditMenu();
    }

    /**
     * Creates the file menu with all its menu items.
     */
    private void addFileMenu() {
        JMenu menu;
        JMenuItem menuItem;

        // File menu
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_1);
        menu.getAccessibleContext().setAccessibleDescription("The File menu for file related functions.");

        // :new graph
        menuItem = new JMenuItem();
        menuItem.setAction(new NewGraphAction(graph));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Resets current frame to new graph. Any unsaved changes will be lost.");
        menu.add(menuItem);

        // :open
        menuItem = new JMenuItem();
        menuItem.setAction(new OpenAction(graph, parentJFrame));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Loads specified graph.");
        menu.add(menuItem);

        // :save
        menuItem = new JMenuItem();
        menuItem.setAction(new SaveAction(graph, parentJFrame));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Saves current graph.");
        menu.add(menuItem);

        // Separator
        menu.add(new JSeparator());

        // :quit
        menuItem = new JMenuItem();
        menuItem.setAction(new QuitAction(parentJFrame));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Quit program.");
        menu.add(menuItem);

        // Add menu to this bar
        this.add(menu);
    }

    /**
     * Creates the edit menu with all its menu items.
     */
    private void addEditMenu() {
        JMenu menu;
        JMenuItem menuItem;

        // Edit menu
        menu = new JMenu("Edit");
        menu.setMnemonic(KeyEvent.VK_2);
        menu.getAccessibleContext().setAccessibleDescription("The edit menu for modifying current graph.");

        // :undo
        menuItem = new JMenuItem();
        menuItem.setAction(new UndoAction());
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Reverts graph to previous state");
        menu.add(menuItem);

        // :redo
        menuItem = new JMenuItem();
        menuItem.setAction(new RedoAction());
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK + InputEvent.SHIFT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Reverts the previous undo action");
        menu.add(menuItem);

        // Separator
        menu.add(new JSeparator());

        // TODO: Copy, cut, paste will go here in that order.

        // :add vertex
        menuItem = new JMenuItem();
        menuItem.setAction(new AddVertexAction(graph));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Adds vertex to graph");
        menu.add(menuItem);

        // :add edge
        menuItem = new JMenuItem();
        menuItem.setAction(new AddEdgeAction(graph));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Adds an edge between two nodes");
        menu.add(menuItem);

        // Separator
        menu.add(new JSeparator());

        // :delete selection
        menuItem = new JMenuItem();
        menuItem.setAction(new DeleteAction(graph));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, InputEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Deletes the selected objects");
        menu.add(menuItem);

        // Add menu to this bar
        this.add(menu);
    }
}