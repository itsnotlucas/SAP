package com.company;

import java.util.ArrayList;

/**
 * A player class which can play the card game
 */
public class Player {
    private ArrayList<Integer> drawPile = new ArrayList<Integer>();
    private ArrayList<Integer> discardPile = new ArrayList<Integer>();

    /**
     * Constructor for a new player
     * @param startingDeck a card deck for the draw pile of the player
     */
    public Player(ArrayList<Integer> startingDeck) {
        this.drawPile = startingDeck;
    }

    /**
     * When used switches the draw pile with a shuffled version of the discard pile.
     * Afterwards clears the discard pile.
     */
    //Switches the shuffled discard pile to be the new draw pile and returns a boolean whether the switch was successful
    public void deckSwitcher() {
        this.drawPile = (ArrayList<Integer>) Main.deckShuffler(this.discardPile).clone();
        this.discardPile.clear();
    }

    /**
     * Checks whether a player has enough cards in his draw pile to play.
     * If not, then checks whether there are any cards in the discard pile.
     * If that is the case the method deckSwitcher() is used to shuffle and
     * switch the draw pile with the discard pile. If not, returns false.
     * @return a boolean value indicating whether a player can continue playing
     */
    public boolean canDraw() {
        //Player can maybe play a round, depending on the result of deck switcher
        if (!this.drawPile.isEmpty()) {
            return true;
        } else if (this.drawPile.isEmpty() && !this.discardPile.isEmpty()) {//player can play a round
            deckSwitcher();
            return true;
        } else {//Player has no cards left
            return false;
        }
    }

    /**
     * Getter for topmost card of the draw pile
     * @return Integer with the topmost element of the draw pile deck
     */
    public Integer getTopCard() {
        return this.drawPile.get(this.drawPile.size() - 1);
    }

    /**
     * Removes the topmost card from the draw pile deck
     */
    public void popTopCard() {
        this.drawPile.remove(this.drawPile.size() - 1);
    }


    /**
     * Getter for the draw pile deck
     * @return draw pile deck
     */
    public ArrayList<Integer> getDrawPile() {
        return this.drawPile;
    }

    /**
     * @return the size of the discard pile deck
     */
    public int getDiscardPileSize() {
        if (this.discardPile.isEmpty()) {
            return 0;
        } else {
            return this.discardPile.size();
        }
    }

    /**
     * Adds an element to the discard pile deck
     * @param wonCard Integer that will be added to the discard pile
     */
    public void discardAdder(Integer wonCard) {
        this.discardPile.add(wonCard);
    }

    /**
     * Setter for the discard pile deck
     * @param newDeck deck that replaces the current discard pile deck
     */
    public void setDiscardPile(ArrayList<Integer> newDeck){
        this.discardPile = (ArrayList<Integer>) newDeck.clone();
    }
}
