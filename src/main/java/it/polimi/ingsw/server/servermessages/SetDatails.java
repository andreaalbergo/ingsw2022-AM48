package it.polimi.ingsw.server.servermessages;

import it.polimi.ingsw.model.Tower;
import it.polimi.ingsw.model.Wizard;

import java.util.ArrayList;
import java.util.List;

public class SetDatails implements Answer{
    @Override


    public Object getMessage() {
        return message;
    }

    private final String message;

    private final Wizard wizard;

    private final Tower tower;

    private List<Wizard> remainingWizards = new ArrayList<>();

    private List<Tower> remainingTowers = new ArrayList<>();

    private final String name;

    public SetDatails(String message) {
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

    public SetDatails(String message, Wizard wizard, Tower tower) {
        this.message = message;
        this.wizard = wizard;
        this.tower = tower;
        name = null;
    }

    public SetDatails(Wizard wizard, Tower tower, String name) {
        this.wizard = wizard;
        this.tower = tower;
        this.name = name;
        message = "\n";
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
