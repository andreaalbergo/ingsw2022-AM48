package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Board;

/**
 * TurnHandler is a class that handles every players' turn of the current game round. So it checks for the right inputs
 * and also checks if the player did the last move.
 */
public class TurnHandler {
    private ExpertPhaseHandler expertPhase;
    private Board model;
    private int phase;

    public TurnHandler(boolean isExpert) {
        if(isExpert)
            expertPhase = new ExpertPhaseHandler();
    }
}
