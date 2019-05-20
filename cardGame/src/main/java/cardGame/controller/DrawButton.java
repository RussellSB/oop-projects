package cardGame.controller;

import cardGame.game.Snap;

import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;

/**
 * Button that draws a card. It uses the Action API to perform its action
 * which means that this is merely a default configuration for this button.
 */
public class DrawButton extends JButton {
    
    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_D);
        setToolTipText("Snap a card");
    }
    
    /**
     * Create a snap button
     */
    public DrawButton(Snap snap) {
        super(new DrawAction(snap));
        setButtonProperties();
    }

}
