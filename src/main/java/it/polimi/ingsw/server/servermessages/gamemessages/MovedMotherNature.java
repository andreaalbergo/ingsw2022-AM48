package it.polimi.ingsw.server.servermessages.gamemessages;

import it.polimi.ingsw.model.IslandTile;
import it.polimi.ingsw.server.servermessages.Answer;

public class MovedMotherNature implements Answer {

    private final int steps;

    private final IslandTile islandTile;

    public MovedMotherNature(int steps, IslandTile islandTile) {
        this.steps = steps;
        this.islandTile = islandTile;
    }

    @Override
    public Object getMessage() {
        return "You moved mother nature ahead of " + steps + " steps, on island " + islandTile;
    }
}
