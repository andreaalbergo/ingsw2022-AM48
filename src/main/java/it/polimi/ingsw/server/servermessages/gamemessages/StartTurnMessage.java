package it.polimi.ingsw.server.servermessages.gamemessages;

import it.polimi.ingsw.server.servermessages.Answer;

public class StartTurnMessage implements Answer {
    public StartTurnMessage() {
    }

    @Override
    public Object getMessage() {
        return "Turn Started";
    }
}
