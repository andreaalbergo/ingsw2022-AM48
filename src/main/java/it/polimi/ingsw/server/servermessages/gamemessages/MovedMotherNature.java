package it.polimi.ingsw.server.servermessages.gamemessages;

import it.polimi.ingsw.model.IslandTile;
import it.polimi.ingsw.server.servermessages.Answer;

public class MovedMotherNature implements Answer {

    private final int steps;

    private final Integer islandTile;



    private final boolean check;

    public MovedMotherNature(int steps, Integer islandTile, boolean check) {
        this.steps = steps;
        this.islandTile = islandTile;
        this.check = check;
    }

    public int getSteps() {
        return steps;
    }

    public Integer getIslandTile() {
        return islandTile;
    }

    public boolean isCheck() {
        return check;
    }

    @Override
    public Object getMessage() {
        return "You moved mother nature ahead of " + steps + " steps, on island " + islandTile;
    }
}
