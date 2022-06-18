package it.polimi.ingsw.client.messages;

import it.polimi.ingsw.model.Mode;

public class ChooseMode implements Message{
    private final Boolean mode;

    public ChooseMode(Boolean mode) {
        this.mode = mode;
    }

    public Boolean getMode() {
        return mode;
    }
}
