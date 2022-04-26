package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {

    SchoolBoard schoolBoard = new SchoolBoard("test");
    Bag bag = new Bag();
    Board board = new Board(2,true);

    /*@Test
    void extractPawnToCloud() {

    }
    */

    @Test
    void drawStudentsToSchoolEntrance() {
        bag.drawStudentsToSchoolEntrance(schoolBoard);
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