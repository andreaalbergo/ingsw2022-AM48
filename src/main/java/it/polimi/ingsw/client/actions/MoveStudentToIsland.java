package it.polimi.ingsw.client.actions;

import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.IslandTile;

/**
 * MoveStudentToDiningRoom is used to create an action for the client in order to input the command of moving a student
 * from hall to specific island by given color chosen by the player.
 *
 * @author Andrea Albergo
 */
public class MoveStudentToIsland implements UserCommand{

    private final IslandTile islandTile;

    private final Color color;

    /**
     * Constructor MoveStudentToIsland to create its instance.
     * @param islandTile of type IslandTile - the chosen island.
     * @param color of type Color.
     */
    public MoveStudentToIsland(IslandTile islandTile, Color color) {
        this.islandTile = islandTile;
        this.color = color;
    }

    /**
     * Method getIslandTile is a getter.
     *
     * @return of type IslandTile.
     */
    public IslandTile getIslandTile() {
        return islandTile;
    }

    /**
     * Method getColor is a getter.
     *
     * @return of type Color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Method toString translates this method into String form, useful to handle actions in another client classes.
     *
     * @return of type String.
     */
    public String toString(){
        return "MoveStudentToIsland";
    }
}
