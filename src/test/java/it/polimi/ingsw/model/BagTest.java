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

    /**
     * initialize, set board, schoolboard and bag (boardManager is inside board constructor)
     */
    @BeforeEach
    void initialization(){
        board = new Board();
        schoolBoard = new SchoolBoard("test",2, false);
        bag = new Bag(board);
    }

    /**
     * check if is extracted a random color
     * @throws GameOverException if there aren't more students in the bag
     */
    @RepeatedTest(10)
    void RandomColorNotNull() throws GameOverException {
        int colorIndex;

        colorIndex = bag.getRandomColorFromBag();
        System.out.println(colorIndex);

        assertNotNull(colorIndex);
    }

    /**
     * controls that the value returned by getRandomColorFromBag is in the correct range (0 to 4)
     * @throws GameOverException if there aren't more students in the bag
     */
    @Test
    void RandomColorRangeExpected() throws GameOverException {
        boolean included = false;

        int colorIndex = bag.getRandomColorFromBag();

        if(colorIndex >= 0 && colorIndex <= 4){
            included = true;
        }

        assertTrue(included);
    }

    /**
     * controls if the set up of the entrance works
     * @throws GameOverException thrown by setUpSchoolEntrance()
     */
    @Test
    void setupSchoolEntrance() throws GameOverException {

        bag.setupSchoolEntrance(schoolBoard);
        ArrayList<Color> entrance = schoolBoard.getEntrance();

        assertEquals(7, entrance.size());
    }

    /**
     * same method as before
     * @throws GameOverException thrown by setUpSchoolEntrance()
     */
    @Test
    void setUpSchoolEntranceFalse() throws GameOverException {

        bag.setupSchoolEntrance(schoolBoard);
        ArrayList<Color> entrance = schoolBoard.getEntrance();

        assertFalse(entrance.isEmpty());

        for(Color c : entrance){
            System.out.println(c);
        }
    }

    /**
     * controls that the bag is full
     */
    @Test
    void isBagEmptyFalse(){

        boolean result = bag.isBagEmpty();

        assertFalse(result);
    }

    /**
     * controls if the bag empties correctly
     * @throws GameOverException if there are no more students in the bag
     */
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