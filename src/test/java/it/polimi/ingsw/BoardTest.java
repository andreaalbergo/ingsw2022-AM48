package it.polimi.ingsw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class BoardTest tests Board class
 *
 * @author David Barb
 * @see Board
 */
class BoardTest {
    Board testBoard;

    /** Method initialization initializes values. */
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
    void updatedGetNumberOfPlayers(){
        assertEquals(2, testBoard.getNumberOfPlayers());
        testBoard.setNumberOfPlayers(3);
        assertEquals(3, testBoard.getNumberOfPlayers());
        testBoard.setNumberOfPlayers(4);
        assertEquals(4, testBoard.getNumberOfPlayers());
    }

}