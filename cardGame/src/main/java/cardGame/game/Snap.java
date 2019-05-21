package cardGame.game;

import cardGame.model.AbstractDeck;
import cardGame.model.Card;
import cardGame.model.CompleteDeck;
import cardGame.model.Pile;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import static cardGame.model.Card.Face.JOKER;

/**
 * Represents a simplified version of the Snap cards game.
 */
public class Snap extends Observable implements Observer {

    private final static double NPC_SNAPPING_PERCENTAGE = 0.3;

    private boolean gameFinished = false;
    private Pile playerUpPile = new Pile();
    private Pile playerDownPile = new Pile();
    private Pile npcUpPile = new Pile();
    private Pile npcDownPile = new Pile();
    private MovableCard movable;

    /**
     * Create a new Snap game with all 54 different cards shuffled between the
     * player and the NPC.
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
     * Return true if the game is finished (someone won).
     */
    public boolean gameIsFinished() {
        return gameFinished;
    }

    /**
     * Creates the movable card from the player face-down pile.
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
     * Shuffle a complete deck between the face-down piles of both players.
     * One card from a shuffled complete deck is given to each player by turns.
     */
    private void shuffle() {
        AbstractDeck deck = new CompleteDeck();
        deck.shuffle();

        boolean playerTurn = true; // Player is the first one to get a card.

        while (!deck.isEmpty()) {
            if (playerTurn)
                playerDownPile.put(deck.draw());
            else
                npcDownPile.put(deck.draw());

            playerTurn = !playerTurn;
        }
    }

    /**
     * Restart the game: empty all piles and shuffle again.
     */
    public void restart() {
        gameFinished = false;

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
     * Check if there is a winner and set the game as finished in such case.
     */
    private void checkWinner() {
        // If both piles are empty the player has lost the game.
        if ((movable == null && playerUpPile.isEmpty())
                || (npcUpPile.isEmpty() && npcDownPile.isEmpty())) {
            if (movable == null && playerUpPile.isEmpty())
                System.out.println("PLAYER LOOSES"); // TODO: Replace with a "Looser" window
            else
                System.out.println("PLAYER WINS"); // TODO: Replace with a "Winner" window

            gameFinished = true;
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Returns true if a match is detected between the face-up piles.
     */
    public boolean matchDetected() {
        if (playerUpPile.top() == null || npcUpPile.top() == null)
            return false;
        else
            return playerUpPile.top().getFace() == npcUpPile.top().getFace()
                    || playerUpPile.top().getFace() == JOKER
                    || npcUpPile.top().getFace() == JOKER;
    }

    /**
     * Draw a card from the player's face-down pile and put it on the player's
     * face-up pile.
     */
    public void playerMoves() {
        // If the face-down pile is empty we take the cards from the face-up one
        // by flipping (reversing the order of) that pile.
        if (movable == null) {
            int size = playerUpPile.size();
            for (int i = 0; i < size; i++)
                playerDownPile.put(playerUpPile.draw());
            createMovableCard();
        }

        playerUpPile.put(movable.getCard());
        createMovableCard();

        setChanged();
        notifyObservers();

        // NPC's turn
        npcMoves();

        checkWinner();
    }

    /**
     * Draw a card from the NPC's face-down pile and put it on the NPC's
     * face-up pile.
     */
    private void npcMoves() {
        // If the face-down pile is empty we take the cards from the face-up one
        // by flipping (reversing the order of) that pile
        if (npcDownPile.isEmpty()) {
            int size = npcUpPile.size();
            for (int i = 0; i < size; i++)
                npcDownPile.put(npcUpPile.draw());
        }

        npcUpPile.put(npcDownPile.draw());

        if (matchDetected())
            npcTriesToSnap();

        setChanged();
        notifyObservers();
    }

    /**
     * The player performs a snap!
     */
    public void playerSnaps() {
        if (matchDetected()) {
            playerDownPile = snap(playerDownPile, playerUpPile, npcUpPile);

            createMovableCard();
            setChanged();
            notifyObservers();

            checkWinner();
        }
    }

    /**
     * The NPC performs a snap!
     */
    private void npcSnaps() {
        if (matchDetected()) {
            npcDownPile = snap(npcDownPile, npcUpPile, playerUpPile);

            setChanged();
            notifyObservers();

            System.out.println("NPC SNAPS!"); // TODO: Replace with a "NPC snapped" window
        }
    }

    /**
     * To give the player a chance the win, the NPC will only snap a percentage
     * of the time (NPC_SNAPPING_PERCENTAGE).
     */
    private void npcTriesToSnap() {
        Random randObj = new Random();

        if (randObj.nextFloat() <= NPC_SNAPPING_PERCENTAGE) {
            npcSnaps();
        }
    }

    /**
     * Generic function to perform a snap (to avoid code duplication).
     */
    private Pile snap(Pile destination, Pile origin1, Pile origin2) {
        int i, size;
        Pile newDownPile = new Pile();

        // Move cards from the origin1's face-up pile to the newDownPile by
        // flipping (reversing the order of) the pile.
        size = origin1.size();
        for (i = 0; i < size; i++)
            newDownPile.put(origin1.draw());

        // Move cards from the origin2's face-up pile to the newDownPile by
        // flipping (reversing the order of) the pile.
        size = origin2.size();
        for (i = 0; i < size; i++)
            newDownPile.put(origin2.draw());

        // Move cards from the destination's face-down pile to the newDownPile.
        for (Card card : destination) {
            newDownPile.put(card);
        }

        return newDownPile;
    }

    /**
     * If the movable card updates this updates too.
     */
    @Override
    public void update(Observable observable, Object message) {
        setChanged();
        notifyObservers();
    }
}
