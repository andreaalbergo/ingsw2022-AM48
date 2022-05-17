package it.polimi.ingsw.server.messages;

public class GameError implements Answer {
    private final String message;
    private final Errors error;

    public GameError(Errors error) {
        message = null;
        this.error = error;
    }

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
