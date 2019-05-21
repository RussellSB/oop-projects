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
 * View of Draw
 */
public class DrawPanel extends JPanel implements Observer {

    private static final int CARD_SPACING = 2; //pixels
    private static final int Y_OFFSET_NPC = Card.values().length * CARD_SPACING;
    private static final int Y_OFFSET_PLAYER = (int) (Card.values().length * CARD_SPACING * 4.25);

    private Snap snap;

    private int movableX;
    private int movableY;

    /**
     * Create a new DrawPanel
     */
    public DrawPanel(Snap snap) {
        this.snap = snap;
        snap.addObserver(this);
        setBackground(new Color(0x7E, 0x35, 0x4D));
        setVisible(true);
        setOpaque(true);
    }

    /**
     * Get the number of pixels in X this card has been moved
     */
    public int getMovableX() {
        return movableX;
    }

    /**
     * Get the number of pixels in Y this card has been moved
     */
    public int getMovableY() {
        return movableY;
    }

    public boolean inPlayerFaceUpArea(Point point) {
        return point.getX() > getWidth() / (float) 2
                && point.getY() > getHeight() / (float) 2;
    }

    /**
     * Paint the areas in which deck and discard pile can be found
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
     * Get the scaled spacing between edges and cards
     */
    private int getSpacing() {
        return (int) ((getHeight() * 20) / 600.0);
    }

    /**
     * Get the scaled width of cards. Default height is 600, default
     * width is 436, and cards are scaled depending on which dimension limits
     * their relative dimensions
     */
    public int cardWidth() {
        //if ((getHeight() * 600.0) / (getWidth() * 436.0) <= 1.0)
        //return (int) ((cardHeight() * 436.0) / 600.0);
        return (int) (436 * 0.3);
    }

    /**
     * Get the scaled height of cards. Default height is 600, default
     * width is 436, and cards are scaled depending on which dimension limits
     * their relative dimensions
     */
    public int cardHeight() {
        //if ((getHeight() * 600.0) / (getWidth() * 436.0) > 1.0)
        //return (int) ((cardWidth() * 600.0) / 436.0);
        return (int) (600 * 0.3);
    }

    /**
     * Draw deck with parameterizable y_offset and pile
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
     * Draw the discard pile with parameterizable y_offset and pile
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
     * Draw player's deck
     */
    private void paintPlayerDownPile(Graphics g) {
        paintDownPile(g, Y_OFFSET_PLAYER, snap.getPlayerDownPile());
    }

    /**
     * Draw the player's discard pile
     */
    private void paintPlayerUpPile(Graphics g) {
        paintUpPile(g, Y_OFFSET_PLAYER, snap.getPlayerUpPile());
    }

    /**
     * Draw the NPC's deck
     */
    private void paintNpcDownPile(Graphics g) {
        paintDownPile(g, Y_OFFSET_NPC, snap.getNpcDownPile());
    }

    /**
     * Draw the NPC's discard pile
     */
    private void paintNpcUpPile(Graphics g) {
        paintUpPile(g, Y_OFFSET_NPC, snap.getNpcUpPile());
    }

    /**
     * Paint the items that this class alone is responsible for.
     * <p>
     * This method is part of a template method that calls
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
     * Tell this DrawPanel that the object it displays has changed
     */
    @Override
    public void update(Observable observed, Object message) {
        repaint();
    }

}