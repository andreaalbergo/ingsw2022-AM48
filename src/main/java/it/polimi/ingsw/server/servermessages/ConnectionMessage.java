package it.polimi.ingsw.server.servermessages;

public class ConnectionMessage implements Answer {

    private final boolean check;
    private final String message;

    public ConnectionMessage(boolean check, String message) {
        this.check = check;
        this.message = message;
    }

    public boolean isCheck() {
        return check;
    }

    @Override
    public Object getMessage() {
        return message;
    }
}
