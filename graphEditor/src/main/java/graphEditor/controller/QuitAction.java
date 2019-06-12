package graphEditor.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Represents the Quit action.
 */
public class QuitAction extends AbstractAction {
    private JFrame parentJFrame;

    /**
     * Creates the Quit action.
     */
    QuitAction(JFrame parentJFrame) {
        super("Quit");
        this.parentJFrame = parentJFrame;
    }

    /**
     * Quits the program.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //parentJFrame.dispatchEvent(new WindowEvent(parentJFrame, WindowEvent.WINDOW_CLOSING));
        System.exit(0);
    }
}
