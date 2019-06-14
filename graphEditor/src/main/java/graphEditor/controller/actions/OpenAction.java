package graphEditor.controller.actions;

import graphEditor.controller.undoableEdits.LoadGraphNonUndoableEdit;
import graphEditor.model.GraphModel;
import graphEditor.view.GraphFrame;

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
    private GraphFrame parentJFrame;

    /**
     * Creates the Open action.
     */
    public OpenAction(GraphModel graph, GraphFrame parentJFrame) {
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
        fc.setCurrentDirectory(new File(System.getProperty("user.dir"))); // sets to current directory
        fc.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
        fc.setAcceptAllFileFilterUsed(false);

        if (fc.showOpenDialog(parentJFrame) == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();

            try {
                graph.addUndoableEdit(new LoadGraphNonUndoableEdit(graph, file.getPath()));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(parentJFrame, ex.getMessage(), "IO error", JOptionPane.ERROR_MESSAGE);
            }
        }

        parentJFrame.setCtrlIsDown(false); // Updates the CTRL flag (after opening a new window the CTRL flag gets stuck in its last state, which could be true if the shortcut was used).
    }
}
