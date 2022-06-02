package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.server.BoardHandler;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * GameController class is the main controller class, it calls and manages some handlers, like roundHandler, in order to
 * manage various states of the game itself.
 *
 * @author David Barb
 * @see PropertyChangeListener
 */
public class GameController implements PropertyChangeListener {
    private final Board model;
    private final BoardHandler boardHandler;
    private final PropertyChangeSupport listeners = new PropertyChangeSupport(this);
    private final RoundHandler roundHandler;

    public GameController(Board model, BoardHandler boardHandler) {
        this.model = model;
        this.boardHandler = boardHandler;
        roundHandler = new RoundHandler(this, new TurnHandler(boardHandler.isExpertMode(), model));
    }

    public Board getBoard(){
        return model;
    }

    /**
     * @see PropertyChangeListener#propertyChange(PropertyChangeEvent)
     * */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()) {
            case "wizardSelection" -> listeners.firePropertyChange("wizardSelection", null,
                    evt.getNewValue());
            case "towerSelection" -> listeners.firePropertyChange("towerSelection", null,
                    evt.getNewValue());
            case "startGame" -> listeners.firePropertyChange("startGame", null,
                    evt.getNewValue());
            case "towerPlacement" -> placeTower(/*(towerPlaceAction) evt.getNewValue()*/);
            case "roundManagement" -> listeners.firePropertyChange("roundManagement", null,
                    evt.getNewValue());
            case "turnManagement" -> listeners.firePropertyChange("turnManagement", null,
                    evt.getNewValue());
            case "gameOver" -> listeners.firePropertyChange("gameOver", null, evt.getNewValue());
            default -> System.err.println("Unrecognized message!");
        }
    }

    public boolean placeTower(/*towerPlaceAction msg*/){
        return true;
    }

    public void nextRound() {
        if(roundHandler.getGameRound()==1) {
            //here State random player chosen and draw assistant card to first turn order
        } else if (roundHandler.getGameRound()==10){
            gameOver();
        } else {
            //iterateTurn();
            //method to make all player choose assistant card
        }
    }



    public void gameOver(){
        System.out.println("Last round finished, Game Over!!!");
        //propertyChange(); an object to end game
    }
}
