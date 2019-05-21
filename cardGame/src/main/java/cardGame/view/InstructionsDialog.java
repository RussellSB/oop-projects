package cardGame.view;

import javax.swing.*;
import java.awt.*;

public class InstructionsDialog extends JDialog {
    public InstructionsDialog(JFrame parentFrame) {
        super(parentFrame, "Instructions");

        String instructionsHTMLtext = "<div style=\"color: yellow; text-align: center;\"> <p><strong>Welcome to SNAP!</strong><br/><br/></p> <p>In a galaxy far far away...<br/><br/></p> <p><strong>HOTKEYS</strong></p> <p>Instructions: [ALT+I]<br/>Draw: [ALT+D]<br/>Reset: [ALT+R]<br/>SNAP!: [ALT+S]<br/><br/></p> <p><strong>SUMMARY</strong></p> <p>Okay. Let's make this snappy. The rules of the game are simple. When you draw a card, so does the NPC. Pay attention to this, because when both you and the NPC draw matching cards, you should SNAP! After snapping you get all the cards from your face-up pile and their face-up pile. When you run out of cards to draw, your previously drawn are flipped over and taken as your brand new to-draw pile.</p><p>Don't run out of cards! The first to take all cards wins.<br/><br/></p> <p>Created with love by Russell & Andr&eacute;s</p> <p>( ͡&deg; ͜ʖ ͡&deg;)</p></div> ";

        setSize(450, 650);
        setLocationRelativeTo(null); // Center on screen.
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        JTextPane instructionsText = new JTextPane();
        instructionsText.setEditable(false);
        instructionsText.setMargin(new Insets(30, 30, 30, 30));
        instructionsText.setContentType("text/html");
        instructionsText.setBackground(new Color(37, 18, 19));
        instructionsText.setText(instructionsHTMLtext);

        add(instructionsText);
    }

    public void openWindow() {
        setVisible(true);
    }
}
