package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.GameOverException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {

    Board board;
    SchoolBoard schoolBoard;
    Bag bag;

    @BeforeEach
    void initialization(){
        board = new Board();
        schoolBoard = new SchoolBoard("test",2, false);
        bag = new Bag(board);
    }

    @RepeatedTest(10)
    void RandomColorNotNull() throws GameOverException {
        int colorIndex;

        colorIndex = bag.getRandomColorFromBag();
        System.out.println(colorIndex);

        assertNotNull(colorIndex);
    }

    @Test
    void RandomColorRangeExpected() throws GameOverException {
        boolean included = false;

        int colorIndex = bag.getRandomColorFromBag();

        if(colorIndex >= 0 && colorIndex <= 4){
            included = true;
        }

        assertTrue(included);
    }

    @Test
    void setupSchoolEntrance() throws GameOverException {

        bag.setupSchoolEntrance(schoolBoard);
        ArrayList<Color> entrance = schoolBoard.getEntrance();

        assertEquals(7, entrance.size());
    }

    @Test
    void setUpSchoolEntranceFalse() throws GameOverException {

        bag.setupSchoolEntrance(schoolBoard);
        ArrayList<Color> entrance = schoolBoard.getEntrance();

        assertFalse(entrance.isEmpty());

        for(Color c : entrance){
            System.out.println(c);
        }
    }

    @Test
    void isBagEmptyFalse(){

        boolean result = bag.isBagEmpty();

        assertFalse(result);
    }

    @Test
    void isBagEmptyTrue() throws GameOverException {

        ArrayList<Integer> allStudents = new ArrayList<>();

        for(int i = 0; i < 120; i++){
            allStudents.add(bag.getRandomColorFromBag());
        }

        boolean result = bag.isBagEmpty();

        assertTrue(result);
    }

}

//mvn clean test