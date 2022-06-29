package it.polimi.ingsw.client.actions;

import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.IslandTile;

public class MoveStudentToIsland implements UserCommand{

    private final IslandTile islandTile;

    private final Color color;

    public MoveStudentToIsland(IslandTile islandTile, Color color) {
        this.islandTile = islandTile;
        this.color = color;
    }

    public IslandTile getIslandTile() {
        return islandTile;
    }

    public Color getColor() {
        return color;
    }

    public String toString(){
        return "MoveStudentToIsland";
    }
}
