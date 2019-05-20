package cardGame;

import cardGame.game.Snap;

import cardGame.view.DrawPanel;

import cardGame.controller.ButtonBar;
import cardGame.controller.CardDragger;

import javax.swing.JFrame;

import java.awt.Dimension;

/**
 * Runs the game. Although technically a controller this class can be found
 * more easily if it's not in that package
 */
public class Main {
    public static void main(String[] args) {
        Snap snap = new Snap();

        DrawPanel panel = new DrawPanel(snap);

        JFrame frame = new JFrame("Simple Snap game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(new ButtonBar(snap));
        frame.getContentPane().add(panel);
        frame.setPreferredSize(new Dimension(650, 750));
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo (null); // Center on screen.
        frame.setVisible(true);

        CardDragger cd = new CardDragger(snap, panel);
    }
}
