package it.polimi.ingsw.server.servermessages.gamemessages;

import it.polimi.ingsw.server.servermessages.Answer;

public class StartTurnMessage implements Answer {


    public Boolean getEnabler() {
        return enabler;
    }
    private final Boolean enabler;

    public StartTurnMessage() {
        enabler = false;
    }

    public StartTurnMessage(boolean enabler) {
        this.enabler = enabler;
    }

    @Override
    public Object getMessage() {
        return "Turn Started";
    }
}
