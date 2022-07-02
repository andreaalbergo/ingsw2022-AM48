package it.polimi.ingsw.server.servermessages;

/**
 * CustomMessage is one of the Server answers, is used to signals events that don't fit in
 * a specific category
 *
 * @author andrea albergo
 */
public class CustomMessage implements Answer {

    private final String message;

    private boolean enabler = false;

    public CustomMessage(String message) {
        this.message = message;
    }

    public CustomMessage(String message, boolean enabler) {
        this.message = message;
        this.enabler = enabler;
    }

    @Override
    public Object getMessage() {
        return message;
    }

    public boolean isEnabled() {
        return enabler;
    }
}
