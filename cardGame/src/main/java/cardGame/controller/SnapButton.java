package cardGame.controller;

import cardGame.game.Draw;

import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;

/**
 * Button that draws a card. It uses the Action API to perform its action
 * which means that this is merely a default configuration for this button.
 */
public class SnapButton extends JButton {

    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_D);
        setToolTipText("Draw a card");
    }

    /**
     * Create a draw button
     */
    public SnapButton(Draw draw) {
        super(new SnapAction(draw));
        setButtonProperties();
    }

}
