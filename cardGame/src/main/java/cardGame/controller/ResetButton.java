package cardGame.controller;

import cardGame.game.Snap;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Button that shuffles all cards into the deck. It uses the Action API to
 * perform its action which means that this is merely a default configuration
 * for this button.
 */
class ResetButton extends JButton {

    /**
     * Create a reset button
     */
    ResetButton(Snap snap) {
        super(new ResetAction(snap));
        setButtonProperties();
    }

    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_S);
        setToolTipText("Reset game and start from 0");
    }

}
