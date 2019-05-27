package cardGame.controller;

import cardGame.game.Snap;
import cardGame.view.SnapPanel;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

/**
 * Implements the ability to drag the top (movable) card of the player face-down
 * pile to the player face-up area of the snapPanel.
 */
public class CardDragger extends MouseInputAdapter {

    private Snap snap;
    private SnapPanel panel;

    private boolean selected;
    private int startX;
    private int startY;

    /**
     * Create a new card dragger that receives mouse events from the SnapPanel
     * supplied to this constructor.
     */
    public CardDragger(Snap snap, SnapPanel panel) {
        this.snap = snap;
        this.panel = panel;
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        selected = false;
    }

    /**
     * If the mouse button is pressed in the area where the movable card is
     * drawn (obviously a lack of drawable cards makes this impossible)
     * that card is 'selected' so it can be dragged.
     */
    @Override
    public void mousePressed(MouseEvent event) {
        if (snap.getMovableCard() != null) {
            if (event.getX() > panel.getMovableX() &&
                    event.getX() < panel.getMovableX() + panel.cardWidth() &&
                    event.getY() > panel.getMovableY() &&
                    event.getY() < panel.getMovableY() + panel.cardHeight()
            ) {
                selected = true;
                startX = event.getX();
                startY = event.getY();
            }
        }
    }

    /**
     * When the movable card is released with the mouse in the player face-up
     * area, the card is moved.
     */
    @Override
    public void mouseReleased(MouseEvent event) {
        if (selected) {
            if (panel.inPlayerFaceUpArea(event.getPoint()))
                snap.playerMoves();
            else {
                snap.getMovableCard().setRelativeX(0);
                snap.getMovableCard().setRelativeY(0);
            }
        }
        selected = false;
    }

    /**
     * If a card is selected it is moved relative to the positions the mouse
     * was first pressed.
     */
    @Override
    public void mouseDragged(MouseEvent event) {
        if (selected) {
            snap.getMovableCard().setRelativeX(event.getX() - startX);
            snap.getMovableCard().setRelativeY(event.getY() - startY);
        }
    }

}
