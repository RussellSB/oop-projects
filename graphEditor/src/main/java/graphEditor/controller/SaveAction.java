package graphEditor.controller;

import graphEditor.model.GraphModel;
import graphEditor.view.GraphFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

/**
 * Represents the Save action.
 */
public class SaveAction extends AbstractAction {
    private GraphModel graph;
    private GraphFrame parentJFrame;

    /**
     * Creates the Save action.
     */
    SaveAction(GraphModel graph, GraphFrame parentJFrame) {
        super("Save");
        this.graph = graph;
        this.parentJFrame = parentJFrame;
    }

    /**
     * Saves the graph in the specified file.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.dir"))); // sets to current directory
        fc.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
        fc.setAcceptAllFileFilterUsed(false);

        if (fc.showSaveDialog(parentJFrame) == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();

            String filename;
            if (file.getName().endsWith(".txt"))
                filename = file.getPath();
            else
                filename = file.getPath() + ".txt";

            try {
                graph.save(filename);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(parentJFrame, ex.getMessage(), "IO error", JOptionPane.ERROR_MESSAGE);
            }
        }

        parentJFrame.setCtrlFlag(false); // updates CTRL Flag in the scenario that this is accessed holding down CTRL
    }
}
