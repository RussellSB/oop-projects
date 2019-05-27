package cardGame.controller;

import cardGame.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Represents an action made to open the instructions window
 */
public class InstructionAction extends AbstractAction {
    /**
     * Creates a new action to shuffle all cards back into the deck
     */
    InstructionAction() {
        super("Instructions");
    }

    /**
     * Opens up instructions window
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Main.getInstructionsDialog().openWindow();
    }
}
