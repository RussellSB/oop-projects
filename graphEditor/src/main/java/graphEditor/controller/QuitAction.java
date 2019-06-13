package graphEditor.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Represents the Quit action.
 */
public class QuitAction extends AbstractAction {
    /**
     * Creates the Quit action.
     */
    QuitAction() {
        super("Quit");
    }

    /**
     * Quits the program.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
