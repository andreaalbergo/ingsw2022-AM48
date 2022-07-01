package it.polimi.ingsw.controller;


import it.polimi.ingsw.model.*;
import it.polimi.ingsw.server.BoardHandler;
import it.polimi.ingsw.server.MultiplayerServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    MultiplayerServer server;
    BoardHandler boardHandler;
    Board board;
    GameController controller;
    Player player1, player2;

    @BeforeEach
    void initialization() {
        board = new Board();
        player1 = new Player("Player1", 1, Wizard.EMIR, Tower.WHITE);
        player2 = new Player("Player2", 2, Wizard.DRUID, Tower.BLACK);
        board.createNewPlayer(player1);
        board.createNewPlayer(player2);
        board.setCurrentPlayer(player1);

        server = new MultiplayerServer(1234);
        boardHandler = new BoardHandler(server);
        controller = new GameController(board, boardHandler);

        player1.createSchoolBoard(false, 2);
        player2.createSchoolBoard(false, 2);
    }

    /**
     * checks that setMode works
     */
    @Test
    void setMode(){

        controller.setMode(true);
        assertTrue(controller.isExpert());

    }

    /**
     * should check if the round is set correctly
     */
    @Test
    void startRound(){

        player1.setChosenCard(AssistantCard.THREE);
        player2.setChosenCard(AssistantCard.EIGHT);

        //controller.startRound();
        /*
        List<Player> turnOrder = board.getPlayersTurnOrder();
        boolean result = false;

        if(turnOrder.get(0) == player1){
            result = true;
        }
        else {
            if (turnOrder.get(0) == player2) {
                result = true;
            }
        }

        assertTrue(result);
         */

    }

    /**
     * should check if the round is changed
     */
    @Test
    void changeRound(){

        board.getBoardManager().createCloudList();
        board.getBoardManager().getClouds().get(0).emptyCloud(player1.getSchoolBoard());
        board.getBoardManager().getClouds().get(1).emptyCloud(player2.getSchoolBoard());

        //controller.changeRound();

        //assertFalse(board.getBoardManager().getClouds().get(1).getCloudCells().isEmpty());

    }

    /**
     * should check if the wizard is set for the player
     */
    @Test
    void setWizard(){

        controller.setWizard(Wizard.EMIR, 1);

        //assertEquals(Wizard.EMIR, board.getActivePlayers().get(1).getChosenWizard());

    }

    /**
     * should check if the tower is set for the player
     */
    @Test
    void setTower(){

        controller.setTower(Tower.WHITE, 1);

        assertEquals(Tower.WHITE, player1.getTower());

    }

}