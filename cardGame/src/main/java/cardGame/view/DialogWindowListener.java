package cardGame.view;

import cardGame.game.Snap;
import cardGame.util.SnapDialogListener;

import javax.swing.*;

/**
 * Listener class that will create a dialog window with the dialog string
 * received from the snap class
 */
public class DialogWindowListener implements SnapDialogListener {
    private JFrame parentFrame;

    public DialogWindowListener(Snap snap, JFrame parentFrame) {
        this.parentFrame = parentFrame;
        snap.addDialogListener(this);
    }

    @Override
    public void onNewDialog(String dialog) {
        JOptionPane.showMessageDialog(parentFrame, dialog);
    }
}
