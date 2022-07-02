package it.polimi.ingsw.server.servermessages;

/**
 * Connection Message is one of the Answer messages and signals the client
 * that the connection was successful
 */
public class ConnectionMessage implements Answer {

    private final boolean check;
    private final String message;

    /**
     * Constructor for the Message
     *
     * @param check boolean
     * @param message String
     *
     */
    public ConnectionMessage(boolean check, String message) {
        this.check = check;
        this.message = message;
    }

    /**
     * isCheck verifìes if the connection was successful
     *
     * @return boolean
     */
    public boolean isCheck() {
        return check;
    }

    @Override
    public Object getMessage() {
        return message;
    }
}
