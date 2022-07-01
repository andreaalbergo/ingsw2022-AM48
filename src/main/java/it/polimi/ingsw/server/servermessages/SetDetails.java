package it.polimi.ingsw.server.servermessages;

import it.polimi.ingsw.model.Tower;
import it.polimi.ingsw.model.Wizard;

import java.util.ArrayList;
import java.util.List;

/**
 * server message for set the details of a player
 */
public class SetDetails implements Answer{
    @Override
    public Object getMessage() {
        return "Set Details";
    }

    private final int num_players;

    private final String message;

    private final Wizard wizard;

    private final Tower tower;

    private List<Wizard> remainingWizards = new ArrayList<>();

    private List<Tower> remainingTowers = new ArrayList<>();

    private final String name;

    public int getNum_players() {
        return num_players;
    }

    /**
     * class constructor
     * @param num_players number of players
     * @param message message
     */
    public SetDetails(int num_players, String message) {
        this.num_players = num_players;
        this.message = message;
        this.wizard = null;
        this.tower= null;
        name = null;
    }

    public Wizard getWizard() {
        return wizard;
    }

    public Tower getTower() {
        return tower;
    }

    /**
     * class constructor
     * @param message message
     * @param wizard chosen wizard
     * @param tower chosen tower
     */
    public SetDetails(String message, Wizard wizard, Tower tower) {
        this.message = message;
        this.wizard = wizard;
        this.tower = tower;
        name = null;
        num_players = 0;
    }

    /**
     * class constructor
     * @param wizard chosen wizard
     * @param tower chosen tower
     * @param name nickname
     * @param num_players number of players
     */
    public SetDetails(Wizard wizard, Tower tower, String name,int num_players) {
        this.wizard = wizard;
        this.tower = tower;
        this.name = name;
        message = "Sto mandando il set details a tutti";
        this.num_players = num_players;
    }

    public String getName() {
        return name;
    }

    public void addRemaining(List<Wizard> wizards, List<Tower> towers){
        remainingWizards = wizards;
        remainingTowers = towers;
    }

    public List<Wizard> getRemainingWizards() {
        return remainingWizards;
    }

    public List<Tower> getRemainingTowers() {
        return remainingTowers;
    }
}
