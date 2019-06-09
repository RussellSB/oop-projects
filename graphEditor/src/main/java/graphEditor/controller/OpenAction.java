package graphEditor.controller;

import graphEditor.model.GraphModel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

/**
 * Represents the Open action.
 */
public class OpenAction extends AbstractAction {
    private GraphModel graph;
    private JFrame parentJFrame;

    /**
     * Creates the Open action.
     */
    OpenAction(GraphModel graph, JFrame parentJFrame) {
        super("Open");
        this.graph = graph;
        this.parentJFrame = parentJFrame;
    }

    /**
     * Loads a graph from the specified file.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
        fc.setAcceptAllFileFilterUsed(false);

        if (fc.showOpenDialog(parentJFrame) == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();

            try {
                graph.load(file.getPath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(parentJFrame, ex.getMessage(), "IO error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
