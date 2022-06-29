package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorTest {

    @Test
    void getIndex1() {
        assertEquals(3, Color.PINK_FAIRIES.getColorIndex());
    }

    @Test
    void getIndex2() {
        assertEquals(2, Color.YELLOW_GNOMES.getColorIndex());
    }

    @Test
    void getIndex3() {
        assertEquals(1, Color.RED_DRAGONS.getColorIndex());
    }

    @Test
    void getColorFromIndex1(){
        assertEquals(Color.PINK_FAIRIES, Color.colorFromIndex(3));
    }

    @Test
    void getColorFromIndex2(){
        assertEquals(Color.YELLOW_GNOMES, Color.colorFromIndex(2));
    }

    @Test
    void getColorFromIndex3(){
        assertEquals(Color.RED_DRAGONS, Color.colorFromIndex(1));
    }

}
