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
        motherNature.setPosition(4);
        assertEquals(4, motherNature.getPosition());
    }
}