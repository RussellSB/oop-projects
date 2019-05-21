package cardGame.controller;

import cardGame.game.Snap;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        setButtonProperties(snap);
    }

    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties(Snap snap) {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setToolTipText("Reset game and start from 0");
        setMnemonic(KeyEvent.VK_R);
    }
}
