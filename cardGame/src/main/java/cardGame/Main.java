package cardGame;

import cardGame.controller.ButtonBar;
import cardGame.controller.CardDragger;
import cardGame.game.Snap;
import cardGame.view.DialogWindowListener;
import cardGame.view.InstructionsDialog;
import cardGame.view.SnapPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Runs the game. Although technically a controller, this class can be found
 * more easily if it's not in that package.
 */
public class Main {
    private static InstructionsDialog instructionsDialog;

    public static InstructionsDialog getInstructionsDialog() {
        return instructionsDialog;
    }

    public static void main(String[] args) {
        final int MAIN_WINDOW_WIDTH = 550;
        final int MAIN_WINDOW_HEIGHT = 750;

        Snap snap = new Snap();

        SnapPanel panel = new SnapPanel(snap);

        JFrame frame = new JFrame("SNAP!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(new ButtonBar(snap));
        frame.getContentPane().add(panel);
        frame.setPreferredSize(new Dimension(MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT));
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null); // Center on screen.
        frame.setVisible(true);

        instructionsDialog = new InstructionsDialog(frame);

        CardDragger cd = new CardDragger(snap, panel);

        DialogWindowListener dw = new DialogWindowListener(snap, frame);
    }
}
