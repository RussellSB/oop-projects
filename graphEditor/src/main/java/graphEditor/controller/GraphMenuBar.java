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
        menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
        // :save
        menuItem = new JMenuItem("Save", KeyEvent.VK_Q);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
        menu.add(menuItem);
        // :load
        menuItem = new JMenuItem("Load", KeyEvent.VK_W);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
        menu.add(menuItem);
        // :new graph
        menuItem = new JMenuItem("New graph", KeyEvent.VK_E);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
        menu.add(menuItem);

        // Add menu to this bar
        this.add(menu);
    }

    private void addEditMenu(){
        // Edit
        menu = new JMenu("Edit");
        menu.setMnemonic(KeyEvent.VK_2);
        //menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
        // :undo
        menuItem = new JMenuItem("Undo", KeyEvent.VK_A);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_MASK));
        //menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
        menu.add(menuItem);
        // :redo
        menuItem = new JMenuItem("Redo", KeyEvent.VK_S);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
        //menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
        menu.add(menuItem);
        // :add node
        menuItem = new JMenuItem("Add node", KeyEvent.VK_D);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_MASK));
        //menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
        menu.add(menuItem);
        // :delete node
        menuItem = new JMenuItem("Delete node", KeyEvent.VK_F);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.ALT_MASK));
        //menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
        menu.add(menuItem);
        // :add edge
        menuItem = new JMenuItem("Add edge", KeyEvent.VK_G);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.ALT_MASK));
        //menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
        menu.add(menuItem);
        // :delete edge
        menuItem = new JMenuItem("Delete edge", KeyEvent.VK_H);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.ALT_MASK));
        //menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
        menu.add(menuItem);

        // Add menu to this bar
        this.add(menu);
    }
}