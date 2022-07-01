package it.polimi.ingsw.model;

import it.polimi.ingsw.model.MotherNature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MotherNatureTest {

    MotherNature motherNature;

    @BeforeEach
    void initialization(){
        motherNature = new MotherNature();
    }

    /**
     * checks that the starting position is 0
     */
    @Test
    void getPosition() {
        assertEquals(0, motherNature.getPosition());
    }

    /**
     * checks that mother nature moves by the expected steps
     */
    @Test
    void setPosition1() {
        motherNature.move(4, 0);
        assertEquals(4, motherNature.getPosition());
    }

    /**
     * this tst moves mother nature by a number of island > 12 to check if restarts from 0
     */
    @Test
    void setPosition2(){
        motherNature.move(4, 0);
        motherNature.move(5, 0);
        motherNature.move(4, 0);
        motherNature.move(2, 0);
        assertEquals(3, motherNature.getPosition());
    }

    /**
     * another test for the movement of mother nature
     */
    @Test
    void setPosition3() {
        motherNature.move(5, 0);
        motherNature.move(5, 0);
        assertEquals(10, motherNature.getPosition());
    }

    /**
     * another test for the movement of mother nature
     */
    @Test
    void setPosition4() {
        motherNature.move(5, 0);
        assertEquals(5, motherNature.getPosition());
    }

    /**
     * check if the island id restarts from 0
     */
    @Test
    void setPosition5() {
        motherNature.move(5, 0);
        motherNature.move(5, 0);
        motherNature.move(2, 0);
        assertEquals(0, motherNature.getPosition());
    }

    /**
     * last test of movement
     */
    @Test
    void setPosition6() {
        motherNature.move(3, 0);
        motherNature.move(4, 0);
        assertEquals(7, motherNature.getPosition());
    }

}
