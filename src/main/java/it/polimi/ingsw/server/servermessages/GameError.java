package it.polimi.ingsw.server.servermessages;

/**
 *
 */
public class GameError implements Answer {
    private final String message;
    private final Errors error;

    public GameError(Errors error) {
        message = null;
        this.error = error;
    }

    /**
     * Constructor
     *
     * @param error Errors
     * @param message String
     */
    public GameError(Errors error, String message){
        this.message = message;
        this.error = error;
    }

    @Override
    public Object getMessage() {
        return message;
    }

    public Errors getError() {
        return error;
    }
}
