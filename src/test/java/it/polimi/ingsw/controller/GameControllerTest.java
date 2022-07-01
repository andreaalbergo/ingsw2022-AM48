package it.polimi.ingsw.controller;

import it.polimi.ingsw.client.actions.EndTurn;
import it.polimi.ingsw.client.actions.MoveMotherNature;
import it.polimi.ingsw.client.actions.MoveStudentToDiningRoom;
import it.polimi.ingsw.client.actions.MoveStudentToIsland;
import it.polimi.ingsw.exceptions.GameOverException;
import it.polimi.ingsw.exceptions.InvalidSelection;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.server.BoardHandler;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.MultiplayerServer;
import it.polimi.ingsw.server.SocketServer;
import it.polimi.ingsw.server.servermessages.Answer;
import it.polimi.ingsw.server.servermessages.CustomMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    //EdoardoBoz: don't know how to test correctly without create the server

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

    @Test
    void setMode(){

        controller.setMode(true);
        assertTrue(controller.isExpert());

    }

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
    //java.lang.NullPointerException:
    // Cannot invoke "java.io.ObjectOutputStream.reset()" because "this.outputStream" is null

    @Test
    void changeRound(){

        board.getBoardManager().createCloudList();
        board.getBoardManager().getClouds().get(0).emptyCloud(player1.getSchoolBoard());
        board.getBoardManager().getClouds().get(1).emptyCloud(player2.getSchoolBoard());

        //controller.changeRound();

        //assertFalse(board.getBoardManager().getClouds().get(1).getCloudCells().isEmpty());

    }
    //java.lang.NullPointerException:
    // Cannot invoke "java.io.ObjectOutputStream.reset()" because "this.outputStream" is null

    @Test
    void setWizard(){

        controller.setWizard(Wizard.EMIR, 1);

        //assertEquals(Wizard.EMIR, board.getActivePlayers().get(1).getChosenWizard());

    }

    @Test
    void setTower(){

        controller.setTower(Tower.WHITE, 1);

        assertEquals(Tower.WHITE, player1.getTower());

    }



}