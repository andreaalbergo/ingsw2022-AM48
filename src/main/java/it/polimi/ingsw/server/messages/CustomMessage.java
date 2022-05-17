package it.polimi.ingsw.server.messages;

public class CustomMessage implements Answer {

    private final String message;

    public CustomMessage(String message) {
        this.message = message;
    }

    @Override
    public Object getMessage() {
        return message;
    }
}
