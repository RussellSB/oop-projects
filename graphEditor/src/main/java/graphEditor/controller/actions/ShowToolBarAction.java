package graphEditor.controller.actions;

import graphEditor.view.GraphFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents the Show Tool Bar action.
 */
public class ShowToolBarAction implements ActionListener {
    private GraphFrame parentJFrame;

    /**
     * Creates the Show Tool Bar action.
     */
    public ShowToolBarAction(GraphFrame parentJFrame) {
        this.parentJFrame = parentJFrame;
    }

    /**
     * Shows or hides the GraphToolBar depending on the state of the button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractButton aButton = (AbstractButton) e.getSource();
        boolean selected = aButton.getModel().isSelected();

        if (selected)
            parentJFrame.getToolBar().setVisible(true);
        else
            parentJFrame.getToolBar().setVisible(false);
    }
}
