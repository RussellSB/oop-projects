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

        addFileMenu();
        addEditMenu();
    }

    /**
     * Creates the File menu with all its menu items.
     */
    private void addFileMenu() {
        JMenu menu;

        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_1);
        menu.getAccessibleContext().setAccessibleDescription("The File menu for file related functions.");

        addNewGraphMenuItem(menu);

        addOpenMenuItem(menu);

        addSaveMenuItem(menu);

        menu.add(new JSeparator()); // Separator

        addQuitMenuItem(menu);

        // Add menu to this bar
        this.add(menu);
    }

    /**
     * Adds the New Graph menu item to the menu.
     */
    private void addNewGraphMenuItem(JMenu menu) {
        JMenuItem menuItem = new JMenuItem();
        menuItem.setAction(new NewGraphAction(graph));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Resets current frame to new graph. Any unsaved changes will be lost.");

        menu.add(menuItem);
    }

    /**
     * Adds the Open menu item to the menu.
     */
    private void addOpenMenuItem(JMenu menu) {
        JMenuItem menuItem = new JMenuItem();
        menuItem.setAction(new OpenAction(graph, parentJFrame));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Loads specified graph.");

        menu.add(menuItem);
    }

    /**
     * Adds the Save menu item to the menu.
     */
    private void addSaveMenuItem(JMenu menu) {
        JMenuItem menuItem = new JMenuItem();
        menuItem.setAction(new SaveAction(graph, parentJFrame));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Saves current graph.");

        menu.add(menuItem);
    }

    /**
     * Adds the Quit menu item to the menu.
     */
    private void addQuitMenuItem(JMenu menu) {
        JMenuItem menuItem = new JMenuItem();
        menuItem.setAction(new QuitAction(parentJFrame));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Quit program.");

        menu.add(menuItem);
    }

    /**
     * Creates the Edit menu with all its menu items.
     */
    private void addEditMenu() {
        JMenu menu;

        // Edit menu
        menu = new JMenu("Edit");
        menu.setMnemonic(KeyEvent.VK_2);
        menu.getAccessibleContext().setAccessibleDescription("The edit menu for modifying current graph.");

        addUndoMenuItem(menu);

        addRedoMenuItem(menu);

        menu.add(new JSeparator()); // Separator

        // TODO: Copy, cut, paste (plus separator) will go here in that order.

        addAddVertexMenuItem(menu);

        addAddEdgeMenuItem(menu);

        menu.add(new JSeparator()); // Separator

        addSelectAllMenuItem(menu);

        menu.add(new JSeparator()); // Separator

        addRenameVertexMenuItem(menu);

        menu.add(new JSeparator()); // Separator

        addDeleteMenuItem(menu);

        // Add menu to this bar
        this.add(menu);
    }

    /**
     * Adds the Undo menu item to the menu.
     */
    private void addUndoMenuItem(JMenu menu) {
        JMenuItem menuItem = new JMenuItem();
        menuItem.setAction(new UndoAction());
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Reverts graph to previous state");

        menu.add(menuItem);
    }

    /**
     * Adds the Redo menu item to the menu.
     */
    private void addRedoMenuItem(JMenu menu) {
        JMenuItem menuItem = new JMenuItem();
        menuItem.setAction(new RedoAction());
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK + InputEvent.SHIFT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Reverts the previous undo action");

        menu.add(menuItem);
    }

    /**
     * Adds the Add Vertex menu item to the menu.
     */
    private void addAddVertexMenuItem(JMenu menu) {
        JMenuItem menuItem = new JMenuItem();
        menuItem.setAction(new AddVertexAction(graph));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Adds vertex to graph");

        menu.add(menuItem);
    }

    /**
     * Adds the Add Edge menu item to the menu.
     */
    private void addAddEdgeMenuItem(JMenu menu) {
        JMenuItem menuItem = new JMenuItem();
        menuItem.setAction(new AddEdgeAction(graph));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Adds an edge between two nodes");

        menu.add(menuItem);
    }

    /**
     * Adds the Select All menu item to the menu.
     */
    private void addSelectAllMenuItem(JMenu menu) {
        JMenuItem menuItem = new JMenuItem();
        menuItem.setAction(new SelectAllAction(graph));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Selects all the vertices and edges");

        menu.add(menuItem);
    }

    /**
     * Adds the Rename Vertex menu item to the menu.
     */
    private void addRenameVertexMenuItem(JMenu menu) {
        JMenuItem menuItem = new JMenuItem();
        menuItem.setAction(new RenameVertexAction(graph, parentJFrame));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Renames the selected vertex");

        menu.add(menuItem);
    }

    /**
     * Adds the Delete Selection menu item to the menu.
     */
    private void addDeleteMenuItem(JMenu menu) {
        JMenuItem menuItem = new JMenuItem();
        menuItem.setAction(new DeleteAction(graph));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, InputEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Deletes the selected objects");

        menu.add(menuItem);
    }
}