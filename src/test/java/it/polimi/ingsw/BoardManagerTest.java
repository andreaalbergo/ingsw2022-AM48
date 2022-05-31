package it.polimi.ingsw;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.BoardManager;
import it.polimi.ingsw.model.IslandTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class BoardManagerTest tests BoardManager class.
 *
 * @author Edoardo Bozzini, David Barb
 * @see BoardManager
 */
class BoardManagerTest {
    Board testBoard;
    BoardManager testBoardManager;

    @BeforeEach
    void initialization() {
        testBoard = new Board();
        testBoardManager = testBoard.getBoardManager();
    }

    @Test
    void testIslandStudentsRandomized() {
        assertEquals(12, testBoardManager.getIslands().size());
        for (IslandTile island : testBoardManager.getIslands()) {
            System.out.println("Island number "+ testBoardManager.getIslands().indexOf(island));
            for (int i = 0; i < 5; i++)
                System.out.println("Color of index "+i+" is: "+island.getStudents()[i]);
        }
    }
}