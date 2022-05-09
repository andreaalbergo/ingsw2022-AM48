package it.polimi.ingsw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class BoardTest tests Board class.
 *
 * @author David Barb
 * @see Board
 */
class BoardTest {
    Board testBoard;

    /** Method initialization that initializes values. */
    @BeforeEach
    void initialization() {
        testBoard = new Board();
    }

    /**
     * Method updatedGetNumberOfPLayers, it gets the number of players after setting number of players, if not, get
     * default value(=2).
     *
     * TBD - it should check list of players updated with new given number, and more.
     */
    @Test
    @DisplayName("Test get number of players before and after setting new number of players")
    void updatedGetNumberOfPlayers() {
        assertFalse(testBoard.setNumberOfPlayers(0));
        assertEquals(2, testBoard.getNumberOfPlayers());
        assertTrue(testBoard.setNumberOfPlayers(3));
        assertEquals(3, testBoard.getNumberOfPlayers());
        assertTrue(testBoard.setNumberOfPlayers(4));
        assertFalse(testBoard.setNumberOfPlayers(-123));
        assertEquals(4, testBoard.getNumberOfPlayers());
        assertFalse(testBoard.setNumberOfPlayers(32));
    }

}