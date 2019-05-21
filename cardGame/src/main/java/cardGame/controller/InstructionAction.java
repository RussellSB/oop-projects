package cardGame.controller;

import cardGame.game.Snap;
import cardGame.view.InstructionPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/**
 * Represents an action made to shuffle all cards back into the deck. Although
 * useless on an empty pile, this action is always available.
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
        InstructionPanel panel = new InstructionPanel();
        JFrame frame = new JFrame("Instructions");
        frame.getContentPane().add(panel);
        frame.setPreferredSize(new Dimension(3*550/4, 3*750/4));
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center on screen.
        frame.setVisible(true);
    }

}
