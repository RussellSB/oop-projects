package cardGame.controller;

import cardGame.game.Snap;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Button that restarts the game. It uses the Action API to perform its action
 * which means that this is merely a default configuration for this button.
 */
class RestartButton extends JButton {

    /**
     * Create a restart game button.
     */
    RestartButton(Snap snap) {
        super(new RestartAction(snap));
        setButtonProperties();
    }

    /**
     * Initialise the properties of this button.
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setToolTipText("Reset game and start from 0");
        setMnemonic(KeyEvent.VK_R);
    }
}
