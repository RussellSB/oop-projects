package cardGame.model;

import cardGame.util.Emptiable;
import cardGame.util.Sized;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An arbitrary deck of cards.
 */
abstract public class AbstractDeck implements Emptiable, Sized {

    /**
     * For the purpose of digital shuffling the list-interface, or rather
     * the Collections function for swapping that requires the list-interface
     * is essential, as the algorithm used for it is also used in the
     * shuffle-method.
     */
    private List<Card> cards;

    /**
     * Create a new deck and add cards to it.
     */
    AbstractDeck() {
        cards = new ArrayList<>();
        addCards();
    }

    /**
     * Adds the cards this deck has by default.
     */
    abstract protected void addCards();

    /**
     * Place a card back on the deck at the top of the deck.
     */
    void addOnTop(Card card) {
        cards.add(card);
    }

    /**
     * Shuffle the deck.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Check the number of cards in this deck.
     */
    @Override
    public int size() {
        return cards.size();
    }

    /**
     * Check if there are any cards left in this deck.
     */
    @Override
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    /**
     * Make this deck empty (e.g. make isEmpty() return true).
     */
    @Override
    public void empty() {
        cards.clear();
    }

    /**
     * Draw a card from the deck. This method will return null if the
     * deck is empty.
     */
    public Card draw() {
        if (!isEmpty())
            return cards.remove(cards.size() - 1);
        return null;
    }
}
