package graphEditor.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class GraphMenuBar extends JMenuBar {

    private JMenu menu;
    private JMenuItem menuItem;

    public GraphMenuBar(){
        super();
        this.addFileMenu();
        this.addEditMenu();
    }

    private void addFileMenu(){
        // File
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_1);
        menu.getAccessibleContext().setAccessibleDescription("The File menu for file related functions.");
        // :save
        menuItem = new JMenuItem("Save", KeyEvent.VK_Q);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Saves current graph.");
        menu.add(menuItem);
        // :load
        menuItem = new JMenuItem("Load", KeyEvent.VK_W);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Loads specified graph.");
        menu.add(menuItem);
        // :new graph
        menuItem = new JMenuItem("New graph", KeyEvent.VK_E);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Resets current frame to new graph. Any unsaved changes will be lost.");
        menu.add(menuItem);

        // Add menu to this bar
        this.add(menu);
    }

    private void addEditMenu(){
        // Edit
        menu = new JMenu("Edit");
        menu.setMnemonic(KeyEvent.VK_2);
        menu.getAccessibleContext().setAccessibleDescription("The edit menu for modifying current graph.");
        // :undo
        menuItem = new JMenuItem("Undo", KeyEvent.VK_A);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Reverts graph to previous state");
        menu.add(menuItem);
        // :redo
        menuItem = new JMenuItem("Redo", KeyEvent.VK_S);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Re-reverts graph to previous state");
        menu.add(menuItem);
        // :add node
        menuItem = new JMenuItem("Add node", KeyEvent.VK_D);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Adds node to graph");
        menu.add(menuItem);
        // :delete node
        menuItem = new JMenuItem("Delete node", KeyEvent.VK_F);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Deletes node from graph");
        menu.add(menuItem);
        // :add edge
        menuItem = new JMenuItem("Add edge", KeyEvent.VK_G);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Adds an edge between two nodes");
        menu.add(menuItem);
        // :delete edge
        menuItem = new JMenuItem("Delete edge", KeyEvent.VK_H);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Deletes an edge between two nodes");
        menu.add(menuItem);

        // Add menu to this bar
        this.add(menu);
    }
}