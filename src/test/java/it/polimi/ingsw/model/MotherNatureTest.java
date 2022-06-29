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

    @Test
    void getPosition() {
        assertEquals(0, motherNature.getPosition());
    }

    @Test
    void setPosition1() {
        motherNature.move(4, 0);
        assertEquals(4, motherNature.getPosition());
    }

    @Test
    void setPosition2(){
        motherNature.move(4, 0);
        motherNature.move(5, 0);
        motherNature.move(4, 0);
        motherNature.move(2, 0);
        assertEquals(3, motherNature.getPosition());
    }

    @Test
    void setPosition3() {
        motherNature.move(5, 0);
        motherNature.move(5, 0);
        assertEquals(10, motherNature.getPosition());
    }

    @Test
    void setPosition4() {
        motherNature.move(5, 0);
        assertEquals(5, motherNature.getPosition());
    }

    @Test
    void setPosition5() {
        motherNature.move(5, 0);
        motherNature.move(5, 0);
        motherNature.move(2, 0);
        assertEquals(0, motherNature.getPosition());
    }

    @Test
    void setPosition6() {
        motherNature.move(3, 0);
        motherNature.move(4, 0);
        assertEquals(7, motherNature.getPosition());
    }

}
