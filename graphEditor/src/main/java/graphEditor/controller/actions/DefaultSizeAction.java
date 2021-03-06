package graphEditor.controller.actions;

import graphEditor.view.GraphFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Represents the Default Size action.
 */
public class DefaultSizeAction extends AbstractAction {
    private GraphFrame parentJFrame;

    /**
     * Creates the Default Size action.
     */
    public DefaultSizeAction(GraphFrame parentJFrame) {
        super("Default Size");

        this.parentJFrame = parentJFrame;
    }

    /**
     * Re-sizes the window to its default size.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        parentJFrame.setSize(GraphFrame.PREFERRED_SIZE);
    }
}
