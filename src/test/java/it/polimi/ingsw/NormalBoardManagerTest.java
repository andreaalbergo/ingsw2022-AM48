package it.polimi.ingsw;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.IslandTile;
import it.polimi.ingsw.model.NormalBoardManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class NormalBoardManagerTest tests NormalBoardManager class.
 *
 * @author Edoardo Bozzini, David Barb
 * @see NormalBoardManager
 */
class NormalBoardManagerTest {
    Board testBoard;
    NormalBoardManager testNormalBoardManager;

    @BeforeEach
    void initialization() {
        testBoard = new Board();
        testNormalBoardManager = testBoard.getBoardManager();
    }

    @Test
    void testIslandStudentsRandomized() {
        assertEquals(12, testNormalBoardManager.getIslands().size());
        for (IslandTile island : testNormalBoardManager.getIslands()) {
            System.out.println("Island number "+ testNormalBoardManager.getIslands().indexOf(island));
            for (int i = 0; i < 5; i++)
                System.out.println("Color of index "+i+" is: "+island.getStudents()[i]);
        }
    }
}