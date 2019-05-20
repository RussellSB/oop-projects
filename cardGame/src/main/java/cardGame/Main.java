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
        JFrame frame = new JFrame("Simple Snap game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(new ButtonBar(snap));
        DrawPanel panel = new DrawPanel(snap);
        CardDragger cd = new CardDragger(snap, panel);
        frame.getContentPane().add(panel);
        frame.setPreferredSize(new Dimension(660, 800));
        frame.pack();
        frame.setLocationRelativeTo (null); // Center on screen.
        frame.setVisible(true);
    }
}
