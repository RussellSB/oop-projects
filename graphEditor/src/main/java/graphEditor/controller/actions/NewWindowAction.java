package graphEditor.controller.actions;

import graphEditor.model.GraphModel;
import graphEditor.view.GraphFrame;
import graphEditor.view.GraphWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Represents the New Window action.
 */
public class NewWindowAction extends AbstractAction {
    private GraphFrame parentJFrame;

    /**
     * Creates the New Window action.
     */
    public NewWindowAction(GraphFrame parentJFrame) {
        super("New Window");
        this.parentJFrame = parentJFrame;
    }

    /**
     * Opens a new Graph Editor window.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new GraphWindow(new GraphModel());
        parentJFrame.setCtrlIsDown(false); // Updates the CTRL flag (after opening a new window the CTRL flag gets stuck in its last state, which could be true if the shortcut was used).
    }
}
