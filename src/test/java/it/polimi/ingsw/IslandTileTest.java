package it.polimi.ingsw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class IslandTileTest tests IslandTile class.
 *
 * @author David Barb
 * @see IslandTile
 */
class IslandTileTest {
    IslandTile testIsland;

    /** Method initialization that initializes values. */
    @BeforeEach
    void initialization() {
        testIsland = new IslandTile();
    }

    /**
     * Method test TBD, because I need simpleBoardManager completed in order to handle a list of islandTiles
     */
    @Test
    void testFirstIsland() {
        assertNull(testIsland.getIslandOwner());
        assertTrue( testIsland.isMotherPresent());
        assertEquals(1, testIsland.howManyTowers());
        for (int i = 0; i < 5; i++) {
            assertEquals(0, testIsland.getColorStudent(i));
        }

    }

}