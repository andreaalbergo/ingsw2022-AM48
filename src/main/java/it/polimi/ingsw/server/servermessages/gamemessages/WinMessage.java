package it.polimi.ingsw.server.servermessages.gamemessages;

import it.polimi.ingsw.server.servermessages.Answer;

public class WinMessage implements Answer {

    public WinMessage() {

    }

    @Override
    public Object getMessage() {
        return "YOU WIN";
    }
}
