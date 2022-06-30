package it.polimi.ingsw.model;

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
    Player player1;
    Player player2;
    Player player3;
    BoardManager boardManager;

    /** Method initialization that initializes values. */
    @BeforeEach
    void initialization() {
        testBoard = new Board();
        player1 = new Player("pinco", 1, Wizard.WITCH, Tower.WHITE);
        player2 = new Player("penguin", 2, Wizard.EMIR, Tower.BLACK);
        player3 = new Player("swagger", 3, Wizard.DRUID, Tower.GREY);
        testBoard.createNewPlayer(player1);
        testBoard.createNewPlayer(player2);
        testBoard.createNewPlayer(player3);
        boardManager = testBoard.getBoardManager();
    }

    @Test
    void testSetCurrentPlayer1() {
        assertNull(testBoard.getCurrentPlayer());
    }

    @Test
    void testSetCurrentPlayer2() {
        testBoard.setCurrentPlayer(testBoard.getActivePlayers().get(0));
        assertEquals("pinco", testBoard.getCurrentPlayer().getNickname());
    }

    @Test
    void testSetCurrentPlayer3(){
        assertNotEquals(testBoard.getCurrentPlayer(), testBoard.getPlayerFromGivenNickname("PinCO"));
    }

    @Test
    void testCurrentPlayer4(){
        testBoard.setCurrentPlayer(testBoard.getActivePlayers().get(0));
        assertEquals(testBoard.getCurrentPlayer(), testBoard.getPlayerFromGivenID(1));
        testBoard.setCurrentPlayer(null);
    }

    @Test
    void testSetPlayerOrderTurn() {
        testBoard.getActivePlayers().get(2).setChosenCard(AssistantCard.SIX);
        testBoard.getActivePlayers().get(0).setChosenCard(AssistantCard.FIVE);
        testBoard.getActivePlayers().get(1).setChosenCard(AssistantCard.TEN);
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

    @Test
    void getPlayerFromGivenNickname(){

        assertEquals(player1, testBoard.getPlayerFromGivenNickname("pinco"));

    }

    @Test
    void getPlayerFromGivenID(){

        assertEquals(player1, testBoard.getPlayerFromGivenID(1));

    }

    @Test
    void pickFirstPlayerToStart(){

        boolean confirmed = false;

        testBoard.pickFirstPlayerToStart();

        if(player2 == testBoard.getCurrentPlayer() || player1 == testBoard.getCurrentPlayer() || player3 == testBoard.getCurrentPlayer()){
            confirmed = true;
        }

        assertTrue(confirmed);

    }


    @Test
    void setNextPlayer(){

        boolean confirmed = false;

        testBoard.pickFirstPlayerToStart();
        testBoard.setPlayerOrderTurn();

        int first = testBoard.getCurrentPlayerIndex();
        System.out.println(first);
        testBoard.setNextPlayer();
        int second = testBoard.getCurrentPlayerIndex();
        System.out.println(second);

        assertNotEquals(first, second);
    }

    @Test
    void getBoardManager() {

        assertEquals(boardManager, testBoard.getBoardManager());
    }


}