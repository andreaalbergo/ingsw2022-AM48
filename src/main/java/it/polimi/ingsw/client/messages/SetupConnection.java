package it.polimi.ingsw.client.messages;

/**
 * Class SetupConnection is a message that is sent by ConnectionSocket class in order to create the new socket with
 * the new user logged in.
 */
public class SetupConnection implements Message {
    private final String nickname;

    /**
     * Constructor SetupConnection to create its own instance.
     *
     * @param nickname of type String.
     */
    public SetupConnection(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Method getNickname is a getter.
     *
     * @return of type String.
     */
    public String getNickname() {
        return nickname;
    }
}
