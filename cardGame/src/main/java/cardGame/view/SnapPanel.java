package cardGame.view;

import cardGame.game.MovableCard;
import cardGame.game.Snap;
import cardGame.model.Card;
import cardGame.model.Pile;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * View of Snap.
 */
public class SnapPanel extends JPanel implements Observer {
    private static final double CARDS_SCALE = 0.3;
    private static final int CARDS_DEFAULT_HEIGHT = 600; // pixels
    private static final int CARDS_DEFAULT_WIDTH = 436; // pixels
    private static final int CARD_SPACING = 2; //pixels
    private static final int Y_OFFSET_NPC = Card.values().length * CARD_SPACING;
    private static final int Y_OFFSET_PLAYER = (int) (Card.values().length * CARD_SPACING * 4.25);

    private Snap snap;

    private int movableX;
    private int movableY;

    /**
     * Create a new SnapPanel.
     */
    public SnapPanel(Snap snap) {
        this.snap = snap;
        snap.addObserver(this);
        setBackground(new Color(0x7E, 0x35, 0x4D));
        setVisible(true);
        setOpaque(true);
    }

    /**
     * Get the number of pixels in X this card has been moved.
     */
    public int getMovableX() {
        return movableX;
    }

    /**
     * Get the number of pixels in Y this card has been moved.
     */
    public int getMovableY() {
        return movableY;
    }

    /**
     * Return true if the mouse is over the player face-up area.
     */
    public boolean inPlayerFaceUpArea(Point point) {
        return point.getX() > getWidth() / (float) 2
                && point.getY() > getHeight() / (float) 2;
    }

    /**
     * Paint the areas in which the card piles can be found.
     */
    private void paintAreas(Graphics g) {
        g.setColor(Color.YELLOW);

        //Draws NPC's row
        g.drawRect(0, 0, getWidth() / 2, getHeight() / 2 - 1);
        g.drawString("NPC Face-Down Area", (getWidth() / 4) - 60, 15);
        g.drawRect(getWidth() / 2, 0, getWidth() / 2 - 1, getHeight() / 2 - 1);
        g.drawString("NPC Face-Up Area", 3 * (getWidth() / 4) - 60, 15);

        //Draw's Player's row
        g.drawRect(0, getHeight() / 2, getWidth() / 2, getHeight() - 1);
        g.drawString("Player Face-Down Area", (getWidth() / 4) - 60, getHeight() / 2 + 15);
        g.drawRect(getWidth() / 2, getHeight() / 2, getWidth() / 2 - 1, getHeight() - 1);
        g.drawString("Player Face-Up Area", 3 * (getWidth() / 4) - 60, getHeight() / 2 + 15);

        g.setColor(Color.BLACK);
    }

    /**
     * Get the scaled spacing between edges and cards.
     */
    private int getSpacing() {
        return (int) ((getHeight() * 20) / 600.0);
    }

    /**
     * Get the scaled width of cards.
     */
    public int cardWidth() {
        return (int) (CARDS_DEFAULT_WIDTH * CARDS_SCALE);
    }

    /**
     * Get the scaled height of cards.
     */
    public int cardHeight() {
        return (int) (CARDS_DEFAULT_HEIGHT * CARDS_SCALE);
    }

    /**
     * Draw face-down pile.
     */
    private void paintDownPile(Graphics g, int y_offset, Pile pile) {
        int depth;
        for (depth = 0; depth < pile.size(); ++depth) {
            int posX = getSpacing() + depth;
            int posY = getSpacing() + y_offset - CARD_SPACING * depth;
            g.drawImage(CardBackTextures.getTexture(CardBack.CARD_BACK_BLUE)
                    , posX, posY, cardWidth(), cardHeight(), this);
            g.drawRect(posX, posY, cardWidth(), cardHeight());
        }

        // If we are with the player face-down pile we also have to draw the
        // movable card
        if (pile == snap.getPlayerDownPile()) {
            MovableCard dependency = snap.getMovableCard();
            if (dependency != null) {
                movableX = getSpacing() + depth + dependency.getRelativeX();
                movableY = getSpacing() + y_offset - CARD_SPACING * depth
                        + dependency.getRelativeY();
                g.drawImage(CardBackTextures.getTexture(CardBack.CARD_BACK_BLUE)
                        , movableX, movableY, cardWidth(), cardHeight(), this);
                g.drawRect(movableX, movableY, cardWidth(), cardHeight());
            }
        }
    }

    /**
     * Draw face-up pile.
     */
    private void paintUpPile(Graphics g, int y_offset, Pile pile) {
        int depth = 0;
        for (Card card : pile) {
            int posX = getWidth() - getSpacing() - cardWidth()
                    + depth - Card.values().length;
            int posY = getSpacing() + y_offset - CARD_SPACING * depth;
            g.drawImage(CardTextures.getTexture(card)
                    , posX, posY, cardWidth(), cardHeight(), this);
            g.drawRect(posX, posY, cardWidth(), cardHeight());
            ++depth;
        }
    }

    /**
     * Draw the player's face-down pile.
     */
    private void paintPlayerDownPile(Graphics g) {
        paintDownPile(g, Y_OFFSET_PLAYER, snap.getPlayerDownPile());
    }

    /**
     * Draw the player's face-up pile.
     */
    private void paintPlayerUpPile(Graphics g) {
        paintUpPile(g, Y_OFFSET_PLAYER, snap.getPlayerUpPile());
    }

    /**
     * Draw the NPC's face-down pile.
     */
    private void paintNpcDownPile(Graphics g) {
        paintDownPile(g, Y_OFFSET_NPC, snap.getNpcDownPile());
    }

    /**
     * Draw the NPC's face-up pile.
     */
    private void paintNpcUpPile(Graphics g) {
        paintUpPile(g, Y_OFFSET_NPC, snap.getNpcUpPile());
    }

    /**
     * Paint the items that this class alone is responsible for.
     *
     * This method is part of a template method that calls.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintAreas(g);
        paintPlayerUpPile(g);
        paintPlayerDownPile(g);
        paintNpcUpPile(g);
        paintNpcDownPile(g);
    }

    /**
     * Tell this SnapPanel that the object it displays has changed.
     */
    @Override
    public void update(Observable observed, Object message) {
        repaint();
    }
}
