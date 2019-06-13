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
        //super("Reset to default size (" + GraphFrame.DEFAULT_WIDTH + " x " + GraphFrame.DEFAULT_HEIGHT + ")");
        super("Reset Size");
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
