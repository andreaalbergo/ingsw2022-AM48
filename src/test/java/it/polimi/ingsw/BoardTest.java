package it.polimi.ingsw;

import org.junit.jupiter.api.BeforeEach;
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

    @Test
    void simpleTest() {
        assertEquals(1, testBoard.getGameTurn());
        for (int i = 0; i < 10; i++) {
            testBoard.setNextTurn();
        }
        assertEquals(10, testBoard.getGameTurn());
    }

}