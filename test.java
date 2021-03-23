import com.company.Player;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static com.company.Main.*;
import static org.junit.jupiter.api.Assertions.*;

public class test {

    /**
     * Tests whether a new deck contains 40 cards
     */
    @Test
    public void testNumberOfCards(){
        ArrayList<Integer> unshuffledDeck = deckCreator();
        assertEquals(40,unshuffledDeck.size());
    }

    /**Task 1 - Test
     * Tests whether a shuffle method actually shuffles and thereby changes the order of a deck
     */
    @Test
    public void testDeckShuffle(){
        ArrayList<Integer> unshuffledDeck = deckCreator();
        ArrayList<Integer> shuffledDeck = deckShuffler(unshuffledDeck);

        System.out.println(unshuffledDeck.toString());
        System.out.println(shuffledDeck.toString());

        assertFalse(Arrays.equals(unshuffledDeck.toArray(),
                shuffledDeck.toArray()));
    }

    /**Task 2 - Test
     * Tests whether the shuffled discard pile gets shuffled into the draw pile if the draw pile is empty
     */
    @Test
    public void testEmptyDrawPile(){
        ArrayList<Integer> testDeck = deckCreator();

        Player testPlayer = new Player(new ArrayList<>());
        testPlayer.setDiscardPile(testDeck);

        assertEquals(0,testPlayer.getDrawPile().size());
        assertTrue(testPlayer.canDraw());
    }

    /**Task 3 - Test 1
     * Tests whether a card with a higher value wins the round.
     */
    @Test
    public void testComparison(){
        ArrayList<Integer> deckPlayerOne = new ArrayList<>();
        deckPlayerOne.add(5);

        ArrayList<Integer> deckPlayerTwo = new ArrayList<>();
        deckPlayerTwo.add(3);

        Player playerOne = new Player(deckPlayerOne);
        Player playerTwo = new Player(deckPlayerTwo);

       assertEquals("Player one wins the game",playGame(playerOne,playerTwo));

    }
}
