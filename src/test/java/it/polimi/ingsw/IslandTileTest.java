package it.polimi.ingsw;

import it.polimi.ingsw.model.IslandTile;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Tower;
import it.polimi.ingsw.model.Wizard;
import it.polimi.ingsw.model.IslandTile;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Wizard;
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
        testIsland = new IslandTile(-1);
    }

    /**
     * Method test TBD
     */
    @Test
    void testFirstIsland() {
        assertNull(testIsland.getIslandOwner());
        assertEquals(0, testIsland.howManyTowers());
        testIsland.setIslandOwner(new Player("putin", 14, Wizard.WIZARD4, Tower.GREY));
        assertEquals(1, testIsland.howManyTowers());
    }
}