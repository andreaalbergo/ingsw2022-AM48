package it.polimi.ingsw;

import it.polimi.ingsw.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CloudTest {
    Board testBoard;

    /** Method initialization that initializes values. */
    @BeforeEach
    void initialization() {
        testBoard = new Board();
        testBoard.createNewPlayer(new Player("hammer", 12, Wizard.EMIR, Tower.WHITE));
        testBoard.createNewPlayer(new Player("penguin", 54, Wizard.DRUID, Tower.BLACK));
        testBoard.createNewPlayer(new Player("swagger", 76, Wizard.WITCH, Tower.GREY));
        testBoard.getBoardManager().createCloudList();
    }

    @Test
    void simpleTest() {
        assertEquals(3, testBoard.getBoardManager().getClouds().size());

    }
}
