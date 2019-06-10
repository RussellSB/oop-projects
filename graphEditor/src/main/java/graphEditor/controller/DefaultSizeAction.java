package graphEditor.controller;

import graphEditor.view.GraphFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Represents the Default Size action.
 */
public class DefaultSizeAction extends AbstractAction {
    private JFrame parentJFrame;

    /**
     * Creates the Default Size action.
     */
    DefaultSizeAction(JFrame parentJFrame) {
        super("Default Size (" + GraphFrame.DEFAULT_WIDTH + " x " + GraphFrame.DEFAULT_HEIGHT + ")");
        this.parentJFrame = parentJFrame;
    }

    /**
     * Re-sizes the window to its default size.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        parentJFrame.setSize(GraphFrame.DEFAULT_WIDTH, GraphFrame.DEFAULT_HEIGHT);
    }
}
