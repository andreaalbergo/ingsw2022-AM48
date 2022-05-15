package it.polimi.ingsw;

import it.polimi.ingsw.model.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorTest {

    @Test
    void getIndex() {
        assertEquals(4, Color.PINK_FAIRIES.getColorIndex());
    }

}
