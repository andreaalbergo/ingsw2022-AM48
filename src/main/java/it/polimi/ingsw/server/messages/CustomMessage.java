package it.polimi.ingsw.server.messages;

public class CustomMessage implements Answer {

    private final String message;

    private boolean enabler;

    public CustomMessage(String message) {
        this.message = message;
    }

    @Override
    public Object getMessage() {
        return message;
    }

    public boolean isEnabled() {
        return enabler;
    }
}
