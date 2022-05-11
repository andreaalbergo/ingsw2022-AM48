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
    IslandTile testIsland;


    /** Method initialization that initializes values. */
    @BeforeEach
    void initialization() {
        testIsland = new IslandTile(-1);
    }

    /**
     * Method test TBD
     */
    @Test
    void testFirstIsland() {
        assertNull(testIsland.getIslandOwner());
        assertEquals(0, testIsland.howManyTowers());
        testIsland.setIslandOwner(new Player("putin", 14, Wizard.WIZARD4, TowersColor.GRAY));
        assertEquals(1, testIsland.howManyTowers());
    }
}