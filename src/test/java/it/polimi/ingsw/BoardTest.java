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
        testBoard.createNewPlayer(new Player("pinco", 12, Wizard.WIZARD1, TowersColor.WHITE));
        testBoard.createNewPlayer(new Player("penguin", 54, Wizard.WIZARD2, TowersColor.BLACK));
        testBoard.createNewPlayer(new Player("swagger", 76, Wizard.WIZARD4, TowersColor.GRAY));
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
        System.out.println("Game's turn: "+testBoard.getGameTurn());
        testBoard.setNextPlayer();
        System.out.println("new current player: "+testBoard.getCurrentPlayer().getNickname());
        System.out.println("Next game's turn: "+testBoard.getGameTurn());
    }
}