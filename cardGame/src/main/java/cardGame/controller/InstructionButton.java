package cardGame.controller;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Button that shows a window with the game instructions.
 */
class InstructionButton extends JButton {

    /**
     * Create an instructions button
     */
    InstructionButton() {
        super(new InstructionAction());
        setButtonProperties();
    }

    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setToolTipText("Game instructions");
        setMnemonic(KeyEvent.VK_I);
    }
}
