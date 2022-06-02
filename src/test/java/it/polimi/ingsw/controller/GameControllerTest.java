package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Board;
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

    @BeforeEach
    void initialization() {
        server = new MultiplayerServer(2222);
        boardHandler = new BoardHandler(server);
        board = new Board();
        controller = new GameController(board, boardHandler);
    }

    @Test
    void test(){

    }
}