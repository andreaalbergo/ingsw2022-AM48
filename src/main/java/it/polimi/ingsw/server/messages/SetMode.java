package it.polimi.ingsw.server.messages;

public class SetMode implements Answer{

    private final String message;

    public SetMode(String message) {
        this.message = message;
    }

    @Override
    public Object getMessage() {
        return message;
    }
}
