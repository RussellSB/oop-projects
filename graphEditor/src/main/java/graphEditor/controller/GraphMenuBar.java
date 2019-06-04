package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class GraphMenuBar extends JMenuBar {
    private GraphModel graph;
    private JMenu menu;
    private JMenuItem menuItem;

    public GraphMenuBar(GraphModel graph) {
        //super(); // TODO: What does this do??
        this.graph = graph;
        this.addFileMenu();
        this.addEditMenu();
        // TODO: addHelpMenu with user instructions
    }

    private void addFileMenu() {
        // File
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_1);
        menu.getAccessibleContext().setAccessibleDescription("The File menu for file related functions.");

        // :save
        menuItem = new JMenuItem();
        menuItem.setAction(new SaveAction(graph));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Saves current graph.");
        menu.add(menuItem);
        // :load
        menuItem = new JMenuItem();
        menuItem.setAction(new LoadAction());
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Loads specified graph.");
        menu.add(menuItem);
        // :new graph
        menuItem = new JMenuItem();
        menuItem.setAction(new NewGraphAction());
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Resets current frame to new graph. Any unsaved changes will be lost.");
        menu.add(menuItem);

        // Add menu to this bar
        this.add(menu);
    }

    private void addEditMenu() {
        // Edit
        menu = new JMenu("Edit");
        menu.setMnemonic(KeyEvent.VK_2);
        menu.getAccessibleContext().setAccessibleDescription("The edit menu for modifying current graph.");

        // :undo
        menuItem = new JMenuItem();
        menuItem.setAction(new UndoAction());
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Reverts graph to previous state");
        menu.add(menuItem);
        // :redo
        menuItem = new JMenuItem();
        menuItem.setAction(new RedoAction());
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Re-reverts graph to previous state");
        menu.add(menuItem);
        // :add node
        menuItem = new JMenuItem();
        menuItem.setAction(new AddNodeAction());
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Adds node to graph");
        menu.add(menuItem);
        // :delete node
        menuItem = new JMenuItem();
        menuItem.setAction(new DeleteNodeAction());
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Deletes node from graph");
        menu.add(menuItem);
        // :add edge
        menuItem = new JMenuItem();
        menuItem.setAction(new AddEdgeAction());
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Adds an edge between two nodes");
        menu.add(menuItem);
        // :delete edge
        menuItem = new JMenuItem();
        menuItem.setAction(new DeleteEdgeAction());
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Deletes an edge between two nodes");
        menu.add(menuItem);

        // Add menu to this bar
        this.add(menu);
    }
}