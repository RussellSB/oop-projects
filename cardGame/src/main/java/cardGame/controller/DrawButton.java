package cardGame.controller;

import cardGame.game.Snap;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Button that draws a card. It uses the Action API to perform its action
 * which means that this is merely a default configuration for this button.
 */
class DrawButton extends JButton {

    /**
     * Create a snap button
     */
    DrawButton(Snap snap) {
        super(new DrawAction(snap));
        setButtonProperties();
    }

    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_D);
        setToolTipText("Draw a card");
    }

}
