package it.polimi.ingsw.client.messages;

/**
 * Class QuitMessage is a message that can be sent by a client to its own socket server to quit gaming and leave the
 * lobby.
 */
public class QuitMessage implements Message{
    private final String message;

    /**
     * Constructor QuitMessage creates its instance.
     *
     * @param message of type String.
     */
    public QuitMessage(String message) {
        this.message = message;
    }

    /**
     * Method getMessage is a getter.
     *
     * @return of type String.
     */
    public String getMessage() {
        return message;
    }
}
