package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.GameOverException;
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
    Player player1;


    /** Method initialization that initializes values. */
    @BeforeEach
    void initialization() {
        player1 = new Player("nick", 1, Wizard.DRUID, Tower.BLACK);
        testIsland = new IslandTile(-1);
    }

    /**
     * Method test TBD
     */
    @Test
    void testFirstIsland1() {
        assertEquals(0, testIsland.howManyTowers());
    }

    @Test
    void testFirstIsland2() {
        assertNull(testIsland.getIslandOwner());
    }

    @Test
    void testTowers() throws GameOverException {
        testIsland.setIslandOwner(new Player("nick", 14, Wizard.SAMURAI, Tower.GREY));
        assertEquals(1, testIsland.howManyTowers());
    }

    @Test
    void setIslandOwner() throws GameOverException {
        testIsland.setIslandOwner(player1);
        assertEquals(player1, testIsland.getIslandOwner());
    }

    @Test
    void mergeIsland() throws GameOverException {

        IslandTile islandTile1 = new IslandTile(1);
        System.out.println(islandTile1.getStudents()[1]);
        IslandTile islandTile2 = new IslandTile(1);
        int sum = islandTile1.getStudents()[1] + islandTile2.getStudents()[1];
        islandTile1.setIslandOwner(player1);
        islandTile2.setIslandOwner(player1);
        islandTile1.mergeIslands(islandTile2);
        System.out.println(islandTile1.getStudents()[1]);

        assertEquals(sum, islandTile1.getStudents()[1]);


    }
}