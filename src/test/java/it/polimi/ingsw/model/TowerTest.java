package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TowerTest {

    /**
     * checks if the list is created correctly
     */
    @Test
    void createList(){
        Tower.setList();
        assertFalse(Tower.available().isEmpty());
    }

    /**
     * checks if the choose of the towers works
     */
    @Test
    void freeList(){
        Tower.setList();
        Tower.choose(Tower.BLACK);
        Tower.choose(Tower.WHITE);
        Tower.choose(Tower.GREY);

        assertTrue(Tower.available().isEmpty());
    }

    /**
     * checks if a tower is already picked
     */
    @Test
    void alreadyPicked(){
        Tower.setList();
        Tower.choose(Tower.WHITE);

        assertTrue(Tower.isAlreadyPicked(Tower.WHITE));
    }

    /**
     * checks if the input stings corresponds to the tower
     */
    @Test
    void stringToTower1(){
        assertEquals(Tower.WHITE, Tower.parseInput("WHITE"));
    }

    /**
     * checks if the input stings corresponds to the tower
     */
    @Test
    void stringToTower2(){
        assertEquals(Tower.BLACK, Tower.parseInput("BLACK"));
    }


}