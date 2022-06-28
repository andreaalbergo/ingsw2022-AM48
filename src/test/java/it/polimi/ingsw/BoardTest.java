package it.polimi.ingsw;

import it.polimi.ingsw.model.*;
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
        testBoard.createNewPlayer(new Player("pinco", 12, Wizard.WITCH, Tower.WHITE));
        testBoard.createNewPlayer(new Player("penguin", 54, Wizard.EMIR, Tower.BLACK));
        testBoard.createNewPlayer(new Player("swagger", 76, Wizard.DRUID, Tower.GREY));
    }

    @Test
    void testSetCurrentPlayer() {
        assertNull(testBoard.getCurrentPlayer());
        testBoard.setCurrentPlayer(testBoard.getActivePlayers().get(0));
        assertEquals("pinco", testBoard.getCurrentPlayer().getNickname());
        assertEquals(testBoard.getCurrentPlayer(), testBoard.getPlayerFromGivenNickname("PinCO"));
        assertEquals(testBoard.getCurrentPlayer(), testBoard.getPlayerFromGivenID(12));
        testBoard.setCurrentPlayer(null);
    }

    @Test
    void testSetPlayerOrderTurn() {
        testBoard.getActivePlayers().get(2).setChosenCard(6);
        testBoard.getActivePlayers().get(0).setChosenCard(5);
        testBoard.getActivePlayers().get(1).setChosenCard(10);
        testBoard.setPlayerOrderTurn();

        for(Player player : testBoard.getPlayersTurnOrder()) {
            System.out.println("player: "+player.getNickname());
        }
        System.out.println("first current player: "+testBoard.getCurrentPlayer().getNickname());
        testBoard.setNextPlayer();
        System.out.println("second current player: "+testBoard.getCurrentPlayer().getNickname());
        testBoard.setNextPlayer();
        System.out.println("third current player: "+testBoard.getCurrentPlayer().getNickname());
        testBoard.setNextPlayer();
        System.out.println("new current player: "+testBoard.getCurrentPlayer().getNickname());
    }
}