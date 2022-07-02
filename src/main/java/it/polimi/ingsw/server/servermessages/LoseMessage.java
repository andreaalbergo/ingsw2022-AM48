package it.polimi.ingsw.server.servermessages;

/**
 * LoseMessage is one of the Server answers, is used to signal the loss of one of teh players
 *
 * @author andrea albergo
 */
public class LoseMessage implements Answer {
    private final String winner;

    /**
     * Constructor
     *
     * @param winner String
     */
    public LoseMessage(String winner) {
        this.winner = winner;
    }


    @Override
    public String getMessage() {
        return winner;
    }
}
