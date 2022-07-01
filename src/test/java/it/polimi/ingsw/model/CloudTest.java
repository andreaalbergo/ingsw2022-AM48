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
    Bag bag;

    /** Method initialization that initializes values. */
    @BeforeEach
    void initialization() {
        testBoard = new Board();
        testBoard.createNewPlayer(player1 = new Player("hammer", 12, Wizard.EMIR, Tower.WHITE));
        testBoard.createNewPlayer(player2 = new Player("penguin", 54, Wizard.DRUID, Tower.BLACK));
        testBoard.createNewPlayer(player3 = new Player("swagger", 76, Wizard.WITCH, Tower.GREY));
        testBoard.getBoardManager().createCloudList();
        bag = new Bag(testBoard);
    }

    /**
     * checks the size of clouds if correspond to the number of players
     */
    @Test
    void simpleTest() {

        assertEquals(3, testBoard.getBoardManager().getClouds().size());

    }

    /**
     * checks that emptyClouds() works moving stodents from cloud to entrance
     * @throws GameOverException if there are no more students in the bag
     */
    @Test
    void emptyCloud() throws GameOverException {

        SchoolBoard schoolBoard = new SchoolBoard("hammer", 3, false);

        ArrayList<Color> entrance;

        entrance = schoolBoard.getEntrance();
        System.out.println(entrance);

        int studentsToExtract;
        if(schoolBoard.getNumberOfPlayers() == 2){
            studentsToExtract = 3;
        }
        else{
            studentsToExtract = 4;
        }

        for(int i = 0; i < studentsToExtract; i++) {
            testBoard.getBoardManager().getClouds().get(0).addStudentToCloud(Color.colorFromIndex(bag.getRandomColorFromBag()));
        }
        testBoard.getBoardManager().getClouds().get(0).emptyCloud(schoolBoard);

        entrance = schoolBoard.getEntrance();
        System.out.println(entrance);

        assertFalse(entrance.isEmpty());
    }

}
