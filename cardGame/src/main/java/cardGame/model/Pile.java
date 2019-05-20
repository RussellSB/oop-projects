package cardGame.model;

import cardGame.util.Emptiable;
import cardGame.util.Sized;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Stack;

/**
 * Pile of cards which has all cards open
 */
public class Pile implements Emptiable, Sized, Iterable<Card> {

    private Stack<Card> pile;

    /**
     * Create a new empty pile
     */
    public Pile() {
        pile = new Stack<>();
    }

    /**
     * Put a card on the pile
     */
    public void put(Card card) {
        pile.push(card);
    }

    /**
     * Remove all cards from this pile
     */
    public Stack<Card> emptyPile() {
        Stack<Card> retVal = pile;
        pile = new Stack<>();
        return retVal;
    }

    /**
     * Returns the top card of the pile, or null if none is present
     */
    public Card top() {
        if (!isEmpty())
            return pile.peek();
        return null;
    }

    /**
     * Returns an iterator which visits the cards in this pile
     * from BOTTOM to TOP, not from top to bottom.
     */
    @Override
    public Iterator<Card> iterator() {
        return new ConcretePileIterator();
    }

    /**
     * Check the number of cards in this pile
     */
    @Override
    public int size() {
        return pile.size();
    }

    /**
     * Check if there are any cards left in this pile.
     */
    @Override
    public boolean isEmpty() {
        return pile.isEmpty();
    }

    /**
     * Remove all the cards in this pile
     */
    @Override
    public void empty() {
        pile.clear();
    }

    /**
     * Draw a card from the pile. This method will return null if the
     * pile is empty,
     */
    public Card draw() {
        if (!isEmpty())
            return pile.remove(pile.size() - 1);
        return null;
    }

    /**
     * Allows iterating over this pile without changing it
     * Does not support remove, so will throw an UnsuportedOperationException
     */
    private class ConcretePileIterator implements Iterator<Card> {

        private ListIterator<Card> backing;

        /**
         * Create an iterator for this immutable pile using the iterator of the
         * Pile it protects
         */
        ConcretePileIterator() {
            backing = pile.listIterator(0);
        }

        /**
         * Find the next card in this pile
         */
        @Override
        public Card next() {
            return backing.next();
        }

        /**
         * Check if all cards have been looked at.
         */
        @Override
        public boolean hasNext() {
            return backing.hasNext();
        }

        /**
         * Removes a card from this pile
         */
        @Override
        public void remove() {
            backing.remove();
        }
    }
}
