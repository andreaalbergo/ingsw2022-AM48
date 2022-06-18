package it.polimi.ingsw.server.messages;

import it.polimi.ingsw.model.Tower;
import it.polimi.ingsw.model.Wizard;

import java.util.ArrayList;
import java.util.List;

public class TowerRequest implements Answer{

    private final String tower;

    private final String message;

    private List<Tower> remaining_towers= new ArrayList<>();

    public void setRemaining_towers(List<Tower> remaining_towers) {
        this.remaining_towers = remaining_towers;
    }

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
        return tower;
    }
}
