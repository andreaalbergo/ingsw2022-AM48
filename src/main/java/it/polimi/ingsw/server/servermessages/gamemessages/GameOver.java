package it.polimi.ingsw.server.servermessages.gamemessages;

import it.polimi.ingsw.server.servermessages.Answer;

/**
 * Class Answer
 */
public class GameOver implements Answer {

    private final String reason;

    public String getReason() {
        return reason;
    }

    public GameOver() {
        reason = null;
    }

    public GameOver(String reason){
        this.reason = reason;
    }



    @Override
    public Object getMessage() {
        return "GAME OVER";
    }
}
