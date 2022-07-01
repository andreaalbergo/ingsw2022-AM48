package it.polimi.ingsw.server.servermessages;

/**
 * message for setting the game mode
 */
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
