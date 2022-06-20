package it.polimi.ingsw.server.servermessages;

public class LoseMessage implements Answer {
    private final String winner;

    public LoseMessage(String winner) {
        this.winner = winner;
    }


    @Override
    public String getMessage() {
        return winner;
    }
}
