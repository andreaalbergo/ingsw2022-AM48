package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Bag;
import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.SchoolBoard;
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
    void RandomColorNotNull() {
        int colorIndex;

        colorIndex = bag.getRandomColorFromBag();
        System.out.println(colorIndex);

        assertNotNull(colorIndex);
    }

    @Test
    void RandomColorRangeExpected(){
        boolean included = false;

        int colorIndex = bag.getRandomColorFromBag();

        if(colorIndex >= 0 && colorIndex <= 4){
            included = true;
        }

        assertTrue(included);
    }

    @Test
    void setupSchoolEntrance() {

        bag.setupSchoolEntrance(schoolBoard);
        ArrayList<Color> entrance = schoolBoard.getEntrance();

        assertEquals(7, entrance.size());
    }

    @Test
    void setUpSchoolEntranceFalse(){

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
    void isBagEmptyTrue(){

        ArrayList<Integer> allStudents = new ArrayList<>();

        for(int i = 0; i < 120; i++){
            allStudents.add(bag.getRandomColorFromBag());
        }

        boolean result = bag.isBagEmpty();

        assertTrue(result);
    }

}

//mvn clean test