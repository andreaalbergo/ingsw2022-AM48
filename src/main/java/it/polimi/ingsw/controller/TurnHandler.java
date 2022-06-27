package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Board;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * TurnHandler is a class that handles every players' turn of the current game round. So it checks for the right inputs
 * and also checks if the player did the last move.
 */
public class TurnHandler implements PropertyChangeListener {
    private ExpertPhaseHandler expertPhase;
    private Board model;

    private int phase;

    public TurnHandler(boolean isExpert, Board model) {
        this.model = model;
        if(isExpert)
            expertPhase = new ExpertPhaseHandler();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
