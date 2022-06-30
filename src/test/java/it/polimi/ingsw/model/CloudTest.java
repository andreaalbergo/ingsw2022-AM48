package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.GameOverException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CloudTest {
    Board testBoard;
    Player player1;
    Player player2;
    Player player3;

    /** Method initialization that initializes values. */
    @BeforeEach
    void initialization() {
        testBoard = new Board();
        testBoard.createNewPlayer(player1 = new Player("hammer", 12, Wizard.EMIR, Tower.WHITE));
        testBoard.createNewPlayer(player2 = new Player("penguin", 54, Wizard.DRUID, Tower.BLACK));
        testBoard.createNewPlayer(player3 = new Player("swagger", 76, Wizard.WITCH, Tower.GREY));
        testBoard.getBoardManager().createCloudList();
    }

    @Test
    void simpleTest() {

        assertEquals(3, testBoard.getBoardManager().getClouds().size());

    }

    @Test
    void emptyCloud() throws GameOverException {

        SchoolBoard schoolBoard = new SchoolBoard("hammer", 3, false);

        ArrayList<Color> entrance;

        entrance = schoolBoard.getEntrance();
        System.out.println(entrance);

        testBoard.getBoardManager().getClouds().get(0).fillStudents();
        testBoard.getBoardManager().getClouds().get(0).emptyCloud(schoolBoard);

        entrance = schoolBoard.getEntrance();
        System.out.println(entrance);

        assertFalse(entrance.isEmpty());
    }

}
