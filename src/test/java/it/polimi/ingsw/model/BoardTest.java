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
        player1 = new Player("pinco", 0, Wizard.WITCH, Tower.WHITE);
        player2 = new Player("penguin", 1, Wizard.EMIR, Tower.BLACK);
        player3 = new Player("swagger", 2, Wizard.DRUID, Tower.GREY);
        testBoard.createNewPlayer(player1);
        testBoard.createNewPlayer(player2);
        testBoard.createNewPlayer(player3);
        boardManager = testBoard.getBoardManager();
    }

    /**
     * checks the current player without setting it
     */
    @Test
    void testSetCurrentPlayer1() {
        assertNull(testBoard.getCurrentPlayer());
    }

    /**
     * check current player with setting
     */
    @Test
    void testSetCurrentPlayer2() {
        testBoard.setCurrentPlayer(testBoard.getActivePlayers().get(0));
        assertEquals("pinco", testBoard.getCurrentPlayer().getNickname());
    }

    /**
     * checks that a player named "Pinco" is equal to a player with the same letters but with caps lock
     */
    @Test
    void testSetCurrentPlayer3(){
        assertNotEquals(testBoard.getCurrentPlayer(), testBoard.getPlayerFromGivenNickname("PinCO"));
    }

    /**
     * checks that a current player is equals to the current player got by id
     */
    @Test
    void testCurrentPlayer4(){
        System.out.println(testBoard.getActivePlayers().get(0));
        testBoard.setCurrentPlayer(testBoard.getActivePlayers().get(0));
        assertEquals(testBoard.getCurrentPlayer(), testBoard.getActivePlayers().get(0));
        testBoard.setCurrentPlayer(testBoard.getActivePlayers().get(0));
    }

    /**
     * checks the turn order of the players
     */
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

    /**
     * checks that getPlayerFromGivenNickname works
     */
    @Test
    void getPlayerFromGivenNickname(){

        assertEquals(player1, testBoard.getPlayerFromGivenNickname("pinco"));

    }

    /**
     * checks that getPlayerFromGivenId works
     */
    @Test
    void getPlayerFromGivenID(){

        assertEquals(player1, testBoard.getPlayerFromGivenID(0));

    }

    /**
     * test that the method pickFirstPlayer() works
     */
    @Test
    void pickFirstPlayerToStart(){

        boolean confirmed = false;

        testBoard.pickFirstPlayerToStart();

        if(player2 == testBoard.getCurrentPlayer() || player1 == testBoard.getCurrentPlayer() || player3 == testBoard.getCurrentPlayer()){
            confirmed = true;
        }

        assertTrue(confirmed);

    }


    /**
     * checks that with the method setNextPlayer() the current player changes
     */
    @Test
    void setNextPlayer(){

        boolean confirmed = false;

        player1.setChosenCard(AssistantCard.TEN);
        player2.setChosenCard(AssistantCard.THREE);
        player3.setChosenCard(AssistantCard.FIVE);
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