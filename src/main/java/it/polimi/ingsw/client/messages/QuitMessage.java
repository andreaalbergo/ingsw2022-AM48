package it.polimi.ingsw.client.messages;

public class QuitMessage implements Message{

    private final String message;

    public QuitMessage(String message) {
        this.message = message;
    }
}
