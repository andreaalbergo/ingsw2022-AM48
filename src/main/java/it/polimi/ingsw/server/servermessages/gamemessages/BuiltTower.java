package it.polimi.ingsw.server.servermessages.gamemessages;

import it.polimi.ingsw.server.servermessages.Answer;

public class BuiltTower implements Answer {

    private final Integer id_island;

    private final Integer remaining_towers;

    private final String player;


    public Integer getId_island() {
        return id_island;
    }

    public Integer getRemaining_towers() {
        return remaining_towers;
    }

    public String getPlayer() {
        return player;
    }

    public BuiltTower(Integer id_island, Integer remaining_towers, String player) {
        this.id_island = id_island;
        this.remaining_towers = remaining_towers;
        this.player = player;
    }


    @Override
    public Object getMessage() {
        return player + " has built a tower on island " + id_island + ", he has " +
                remaining_towers + " remaining towers";
    }
}
