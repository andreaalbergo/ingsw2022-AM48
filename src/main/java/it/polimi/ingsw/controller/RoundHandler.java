package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.InvalidSelection;
import it.polimi.ingsw.model.AssistantCard;
import it.polimi.ingsw.model.Player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * RoundHandler is a class that handles the "Planning Phase" of every Eriantys round, so it handles the chosen assistant
 * cards to order the players' turns for the "Action Phase".
 *
 * @author David Barb
 */
public class RoundHandler implements PropertyChangeListener {
    private final GameController mainController;
    private int gameRound = 1;


    /**
     * Constructor RoundHandler to create its own instances.
     * @param mainController
     */
    public RoundHandler(GameController mainController) {
        this.mainController = mainController;
    }

    /**
     * Method getGameTurn give us back this game's turn.
     *
     * @return of type int - turn's game.
     */
    public int getGameRound() {
        return gameRound;
    }

    /**
     * Method iterateTurn increases the game's round by one. If current turn is 10, it means it's the last and after that
     * the model notifies the controller about reaching game over.
     */
    public void iterateRound() {
        if(gameRound!=10)
            gameRound++;
        else
            mainController.gameOver();
    }

    /**
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "endTurn" -> iterateRound();
        }
    }


}
