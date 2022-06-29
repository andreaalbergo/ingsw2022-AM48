package it.polimi.ingsw.server.servermessages.gamemessages;

import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.server.servermessages.Answer;

public class ProfessorAnswer implements Answer {

    private final Color color;

    private final boolean gained;

    public ProfessorAnswer(Color color, boolean gained) {
        this.color = color;
        this.gained = gained;
    }

    public Color getColor() {
        return color;
    }

    public boolean isGained() {
        return gained;
    }

    @Override
    public Object getMessage() {
        return "ProfessorAnswer";
    }
}
