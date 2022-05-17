package it.polimi.ingsw.client.messages;

import it.polimi.ingsw.model.Tower;

public class ChooseTowerColor implements Message{
    private final Tower tower;

    public ChooseTowerColor(Tower tower) {
        this.tower = tower;
    }

    public Tower getTower() {
        return tower;
    }
}
