package it.polimi.ingsw.server.servermessages;

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
