package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MotherNatureTest {

    @Test
    void getPosition() {
        MotherNature motherNature = new MotherNature(0);
        assertTrue( motherNature.getPosition() == 0 );
    }

    @Test
    void setPosition() {
        MotherNature motherNature = new MotherNature(0);
        motherNature.setPosition(4);
        assertTrue( motherNature.getPosition() == 4 );
    }
}