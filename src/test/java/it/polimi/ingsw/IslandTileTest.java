package it.polimi.ingsw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class IslandTileTest tests IslandTile class.
 *
 * @author David Barb
 * @see IslandTile
 */
class IslandTileTest {
    List<IslandTile> islands = new ArrayList<>();

    /** Method initialization that initializes values. */
    @BeforeEach
    void initialization() {
        for (int i = 0; i < 12; i++) {
            islands.add(new IslandTile());
        }
    }

    /**
     * Method test TBD, because I need simpleBoardManager completed in order to handle a list of islandTiles
     */
    @Test
    void testFirstIsland() {
        assertNull(islands.get(0).getIslandOwner());
        assertTrue(islands.get(0).isMotherPresent());
        assertEquals(0, islands.get(0).howManyTowers());
        for (int i = 0; i < 5; i++) {
            assertEquals(0, islands.get(0).getColorStudent(i));
        }
    }
/*
    @Test
    void testSecondIsland(){


        assertNull(testIslandTwo.getIslandOwner());
        assertFalse(testIslandTwo.isMotherPresent());

        for (int i = 0; i < 5; i++) {
            System.out.println(testIslandTwo.getColorStudent(i));
        }
    }*/

}