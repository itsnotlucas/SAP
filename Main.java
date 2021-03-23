package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> shuffledDeck = deckShuffler(deckCreator());
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Press enter to start a new game");

        //Each player receives a stack of 20 cards from the shuffled main deck
        Player playerOne = new Player(new ArrayList<Integer>(shuffledDeck.subList(0, 20)));
        Player playerTwo = new Player(new ArrayList<Integer>(shuffledDeck.subList(20, 40)));

        //Prints the winner of the game
        System.out.println(playGame(playerOne, playerTwo));
    }

    /**
     * Creates a new card deck
     * @return a new deck with 40 cards and the numbers 1-10 each being present 4 times
     */
    public static ArrayList<Integer> deckCreator() {
        ArrayList<Integer> deck = new ArrayList<Integer>();

        for (int i = 1; i < 11; i++) {
            deck.add(i);
            deck.add(i);
            deck.add(i);
            deck.add(i);
        }
        return deck;
    }

    /**
     * Shuffles a given array using the .shuffle method from the Java Collections
     * @param deck A deck that should be shuffled
     * @return A card deck that has been shuffled
     */
    public static ArrayList<Integer> deckShuffler(ArrayList<Integer> deck) {
        ArrayList<Integer> helperArray = (ArrayList<Integer>) deck.clone();
        Collections.shuffle(helperArray);
        return helperArray;
    }

    /**
     * Plays a card game according to the rules of the game
     * @param playerOne one of the two players in the game
     * @param playerTwo one of the two players in the game
     * @return A string with the name of the winner
     */
    //Plays a card game and returns a String with the winner of the game
    public static String playGame(Player playerOne, Player playerTwo) {

        //Helper ArrayList that stores the Integers that were played in a stalemate round
        ArrayList<Integer> stalemateCards = new ArrayList<Integer>();

        //Checks whether both players have enough cards to start/continue playing
        while (playerOne.canDraw() && playerTwo.canDraw()) {
            Integer cardOne = playerOne.getTopCard();
            Integer cardTwo = playerTwo.getTopCard();

            int discardSizePlayerOne = playerOne.getDiscardPileSize();
            int discardSizePlayerTwo = playerTwo.getDiscardPileSize();

            int drawPileSizePlayerOne = playerOne.getDrawPile().size();
            int drawPileSizePlayerTwo = playerTwo.getDrawPile().size();

            System.out.println("Player 1 (" +
                    (drawPileSizePlayerOne + discardSizePlayerOne) + " cards): " + cardOne);
            System.out.println("Player 2 (" +
                    (drawPileSizePlayerTwo + discardSizePlayerTwo) + " cards): " + cardTwo);


            if (cardOne > cardTwo) {
                //Player One wins a round
                playerOne.discardAdder(cardOne);
                playerOne.discardAdder(cardTwo);

                playerOne.popTopCard();
                playerTwo.popTopCard();

                if (!stalemateCards.isEmpty()) {
                    for (Integer card : stalemateCards) playerOne.discardAdder(card);
                    stalemateCards.clear();
                }
                System.out.println("Player 1 wins this round" + "\n");
            } else if (cardOne < cardTwo) {
                //Player two wins a round
                playerTwo.discardAdder(cardOne);
                playerTwo.discardAdder(cardTwo);

                playerOne.popTopCard();
                playerTwo.popTopCard();

                if (!stalemateCards.isEmpty()) {
                    for (Integer card : stalemateCards) playerTwo.discardAdder(card);
                    stalemateCards.clear();
                }

                System.out.println("Player 2 wins this round" + "\n");
            } else{//In case of a stalemate the given cards are added to the stalemate list and removed from the respective draw piles
                stalemateCards.add(cardOne);
                stalemateCards.add(cardTwo);

                playerOne.popTopCard();
                playerTwo.popTopCard();

                System.out.println("No winner in this round" + "\n");
            }
        }
        if (playerOne.canDraw()){
            return "Player one wins the game";
        }else{
            return "Player two wins the game";
        }

    }

}

