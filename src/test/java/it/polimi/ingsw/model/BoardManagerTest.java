package it.polimi.ingsw.model;

import it.polimi.ingsw.model.*;
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

    /**
     * checks if the students extracted on the islands corresponds to the rules
     * (1 student / island and maximus 2 students of the same color
     */
    @Test
    void testIslandStudentsRandomized() {

        assertEquals(12, testBoardManager.getIslands().size());

        for (IslandTile island : testBoardManager.getIslands()) {
            System.out.println("Island number "+ testBoardManager.getIslands().indexOf(island));
            for (int i = 0; i < 5; i++)
                System.out.println("Color of index "+i+" is: "+island.getStudents()[i]);
        }
    }

    /**
     * check if the cloud are being generated correctly and are empty
     */
    @Test
    void getCloudsEmpty(){

        Player player1 = new Player("Player1", 1, Wizard.EMIR,Tower.BLACK);
        Player player2 = new Player("Player2", 2,Wizard.DRUID, Tower.WHITE);

        testBoard.createNewPlayer(player1);
        testBoard.createNewPlayer(player2);

        testBoardManager.createCloudList();

        assertFalse(testBoardManager.getClouds().isEmpty());

    }

    /**
     * controls if the number of clouds created is equal to the number of players
     */
    @Test
    void getCloudsSize2(){

        Player player1 = new Player("Player1", 1, Wizard.EMIR,Tower.BLACK);
        Player player2 = new Player("Player2", 2,Wizard.DRUID, Tower.WHITE);

        testBoard.createNewPlayer(player1);
        testBoard.createNewPlayer(player2);

        testBoardManager.createCloudList();

        assertEquals(2, testBoardManager.getClouds().size());

    }

    /**
     * second check as before but with 3 players
     */
    @Test
    void getCloudsSize3(){

        Player player1 = new Player("Player1", 1, Wizard.EMIR, Tower.BLACK);
        Player player2 = new Player("Player2", 2, Wizard.DRUID, Tower.WHITE);
        Player player3 = new Player("Player3", 3, Wizard.WITCH, Tower.GREY);

        testBoard.createNewPlayer(player1);
        testBoard.createNewPlayer(player2);
        testBoard.createNewPlayer(player3);

        testBoardManager.createCloudList();

        assertEquals(3, testBoardManager.getClouds().size());

    }
}