package graphEditor.controller;

import graphEditor.model.GraphModel;
import graphEditor.util.TxtFileFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class OpenAction extends AbstractAction {
    private GraphModel graph;
    private JFrame parentJFrame;

    OpenAction(GraphModel graph, JFrame parentJFrame) {
        super("Open");
        this.graph = graph;
        this.parentJFrame = parentJFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new TxtFileFilter());
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
