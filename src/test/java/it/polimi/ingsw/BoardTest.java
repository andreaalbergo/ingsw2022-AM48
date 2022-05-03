package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Board board = new Board(2,false);
    @Test
    void getNumberOfPlayers() {
        assertEquals(2,this.board.getNumberOfPlayers());
    }

    @Test
    void isExpertMode() {
        assertFalse(this.board.isExpertMode());
    }

    @Test
    void getBoardManager() {
        //assertNotNull(board.getBoardManager());
        assertNull(this.board.getBoardManager());
    }
}