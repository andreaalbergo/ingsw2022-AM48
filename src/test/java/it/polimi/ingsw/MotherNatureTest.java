package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MotherNatureTest {

    @Test
    void getPosition() {
        MotherNature motherNature = new MotherNature();
        assertEquals(0, motherNature.getPosition());
    }

    @Test
    void setPosition() {
        MotherNature motherNature = new MotherNature();
        motherNature.move(4);
        assertEquals(5, motherNature.getPosition());
        motherNature.move(3);
        assertEquals(8, motherNature.getPosition());
        motherNature.move(5);
        assertEquals(1,motherNature.getPosition());
    }
}