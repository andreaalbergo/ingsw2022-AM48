package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {

    Board board = new Board(2,true);
    SchoolBoard schoolBoard = new SchoolBoard("test", board.getNumberOfPlayers(), board.isExpertMode());
    Bag bag = new Bag(board);

    /*@Test
    void extractPawnToCloud() {

    }
    */

    @Test
    void drawStudentsToSchoolEntrance() {
        bag.setupSchoolEntrance(schoolBoard);
        ArrayList<Color> entrance = schoolBoard.getEntrance();
        for(Color c : entrance){
            System.out.println(c);
        }
        assertFalse(entrance.isEmpty());
    }

    @Test
    void setupSchoolEntrance() {
        bag.setupSchoolEntrance(schoolBoard);
        ArrayList<Color> entrance = schoolBoard.getEntrance();
        assertEquals(7,entrance.size());
        assertFalse(entrance.isEmpty());
        for(Color c : entrance){
            System.out.println(c);
        }
    }
}
