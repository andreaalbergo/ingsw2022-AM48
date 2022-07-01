package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorTest {

    /**
     * checks if the color corresponds to the index
     */
    @Test
    void getIndex1() {
        assertEquals(3, Color.PINK_FAIRIES.getColorIndex());
    }

    /**
     * checks if the color corresponds to the index
     */
    @Test
    void getIndex2() {
        assertEquals(2, Color.YELLOW_GNOMES.getColorIndex());
    }

    /**
     * checks if the color corresponds to the index
     */
    @Test
    void getIndex3() {
        assertEquals(1, Color.RED_DRAGONS.getColorIndex());
    }

    /**
     * checks if the index corresponds to the color
     */
    @Test
    void getColorFromIndex1(){
        assertEquals(Color.PINK_FAIRIES, Color.colorFromIndex(3));
    }

    /**
     * checks if the index corresponds to the color
     */
    @Test
    void getColorFromIndex2(){
        assertEquals(Color.YELLOW_GNOMES, Color.colorFromIndex(2));
    }

    /**
     * checks if the index corresponds to the color
     */
    @Test
    void getColorFromIndex3(){
        assertEquals(Color.RED_DRAGONS, Color.colorFromIndex(1));
    }

}
