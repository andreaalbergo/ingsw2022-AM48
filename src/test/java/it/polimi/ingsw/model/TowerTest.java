package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TowerTest {

    @Test
    void createList(){
        Tower.setList();
        assertFalse(Tower.available().isEmpty());
    }

    @Test
    void freeList(){
        Tower.setList();
        Tower.choose(Tower.BLACK);
        Tower.choose(Tower.WHITE);
        Tower.choose(Tower.GREY);

        assertTrue(Tower.available().isEmpty());
    }

    @Test
    void alreadyPicked(){
        Tower.setList();
        Tower.choose(Tower.WHITE);

        assertTrue(Tower.isAlreadyPicked(Tower.WHITE));
    }

    @Test
    void stringToTower1(){
        assertEquals(Tower.WHITE, Tower.parseInput("WHITE"));
    }

    @Test
    void stringToTower2(){
        assertEquals(Tower.BLACK, Tower.parseInput("BLACK"));
    }


}