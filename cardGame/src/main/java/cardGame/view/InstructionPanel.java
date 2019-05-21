package cardGame.view;
import javax.swing.*;
import java.awt.*;

public class InstructionPanel extends JPanel {

    /**
     * Create a new InstructionPanel
     */
    public InstructionPanel() {
        setBackground(new Color(37, 18, 19));
        setVisible(true);
        setOpaque(true);
    }

    private void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

    private void paintLayout(Graphics g) {
        g.setColor(Color.YELLOW);
        g.drawRect(0, 0, getWidth() - 1 , getHeight() - 1);
        g.drawString("Welcome to SNAP!", getWidth()/3, 30);
        this.drawString(g,
                "In a galaxy far far away...\n\n\n"+
                    "===SUMMARY===\n"+
                    "Ok. Let's make this snappy. The rules of the game are simple.\n"+
                    "When you draw a card, so does the NPC. Pay attention to this,\n" +
                    "because when both you and the NPC draw matching cards, you\n" +
                    "should SNAP! After snapping you get all the cards from your\n" +
                    "face-up pile and their face-up pile. When you run out of cards\n"+
                    "to draw, your previously drawn are flipped over and taken\n"+
                    "as your brand new to-draw pile! Don't run out of cards!\n"+
                 "The first to take all cards win.\n\n\n\n"+
                    "===HOTKEYS===\n"+
                    "Instructions: [ALT+I]\n"+
                    "Draw: [ALT+D]\n"+
                    "Reset: [ALT+R]\n" +
                    "SNAP!: [ALT+S]"+
                    "\n\n\n\n\n\n snap safe                                                                                 ( ͡° ͜ʖ ͡°)",
                getWidth()/10, getHeight()/7);

        g.setColor(Color.BLACK);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintLayout(g);
    }
}
