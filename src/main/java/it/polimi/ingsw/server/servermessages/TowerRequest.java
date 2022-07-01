package it.polimi.ingsw.server.servermessages;

import it.polimi.ingsw.model.Tower;

import java.util.ArrayList;
import java.util.List;

/**
 * message sent for set a tower color to a player
 */
public class TowerRequest implements Answer{

    private final String tower;

    private final String message;

    private List<Tower> remaining_towers= new ArrayList<>();

    public void setRemaining_towers(List<Tower> remaining_towers) {
        this.remaining_towers = remaining_towers;
    }

    /**
     * class constructor
     * @param tower chosen tower
     * @param message message
     */
    public TowerRequest(String tower, String message) {
        this.tower = tower;
        this.message = message;
    }

    public TowerRequest(String message) {
        this.tower = null;
        this.message = message;
    }

    public String getTower() {
        return tower;
    }

    @Override
    public Object getMessage() {
        return message;
    }
}
