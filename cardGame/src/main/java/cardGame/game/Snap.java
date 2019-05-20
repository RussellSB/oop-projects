package cardGame.game;

import cardGame.model.AbstractDeck;
import cardGame.model.CompleteDeck;
import cardGame.model.Pile;

import java.util.Observable;
import java.util.Observer;

/**
 * Represents a simplified version of the snap cards game
 */
public class Snap extends Observable implements Observer {

    private Pile playerUpPile = new Pile();
    private Pile playerDownPile = new Pile();
    private Pile npcUpPile = new Pile();
    private Pile npcDownPile = new Pile();
    private MovableCard movable;

    /**
     * Create a new Snap game with all 54 different cards shuffled between the
     * player and the NPC
     */
    public Snap() {
        shuffle();
        createMovableCard();
    }

    public Pile getPlayerUpPile() {
        return playerUpPile;
    }

    public Pile getPlayerDownPile() {
        return playerDownPile;
    }

    public Pile getNpcUpPile() {
        return npcUpPile;
    }

    public Pile getNpcDownPile() {
        return npcDownPile;
    }

    public MovableCard getMovableCard() {
        return movable;
    }

    /**
     * Creates the movable card from the player face-down pile
     */
    private void createMovableCard() {
        if (movable != null) {
            movable.deleteObserver(this);
            movable = null;
        }
        if (!playerDownPile.isEmpty()) {
            movable = new MovableCard(playerDownPile.draw());
            movable.addObserver(this);
        }
    }

    /**
     * Shuffle a complete deck between the face-down piles of both players
     */
    private void shuffle() {
        AbstractDeck deck = new CompleteDeck();
        deck.shuffle();

        boolean playerTurn = true; // Player is the first one to get a card

        while (!deck.isEmpty()) {
            if (playerTurn)
                playerDownPile.put(deck.draw());
            else
                npcDownPile.put(deck.draw());

            playerTurn = !playerTurn;
        }
    }
    
    
    /**
     * Draw a card from the player face-down pile and put it on the player
     * face-up pile
     */
    public void playerMoves() {
        if(movable != null)
            playerUpPile.put(movable.getCard());
        createMovableCard();
        setChanged();
        notifyObservers();
    }
    
    /**
     * Empty piles and shuffle again
     */
    public void reset() {
        playerUpPile.empty();
        playerDownPile.empty();
        npcUpPile.empty();
        npcDownPile.empty();

        shuffle();
        createMovableCard();
        setChanged();
        notifyObservers();
    }

    /**
     * TODO
     */
    public void playerSnaps() {
        // TODO
    }

    /** 
     * If the movable card updates this updates too
     */
    @Override
    public void update(Observable observable, Object message) {
        setChanged();
        notifyObservers();
    }

}
