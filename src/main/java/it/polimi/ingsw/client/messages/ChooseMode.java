package it.polimi.ingsw.client.messages;

import it.polimi.ingsw.model.Mode;

public class ChooseMode implements Message{
    private final Mode mode;

    public ChooseMode(Mode mode) {
        this.mode = mode;
    }

    public Mode getMode() {
        return mode;
    }
}
