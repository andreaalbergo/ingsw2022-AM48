package it.polimi.ingsw.client.messages;

import it.polimi.ingsw.model.Tower;
import it.polimi.ingsw.model.Wizard;

public class ChooseDetails implements Message{

    private final Tower tower;

    private final Wizard wizard;

    public ChooseDetails(Tower tower, Wizard wizard) {
        this.tower = tower;
        this.wizard = wizard;
    }

    public Tower getTower() {
        return tower;
    }

    public Wizard getWizard() {
        return wizard;
    }
}
