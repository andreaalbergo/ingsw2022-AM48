package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.server.BoardHandler;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GameController implements PropertyChangeListener {
    private final Board model;
    private final BoardHandler boardHandler;
    private final PropertyChangeSupport listeners = new PropertyChangeSupport(this);

    public GameController(Board model, BoardHandler boardHandler) {
        this.model = model;
        this.boardHandler = boardHandler;
    }

    public Board getBoard(){
        return model;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void nextRound() {
        if(model.getGameTurn()==1) {
            //here State random player chosen and draw assistant card to first turn order
        } else if (model.getGameTurn()==10){
            gameOver();
        } else {
            //iterateTurn();
            //method to make all player choose assistant card
        }
    }



    public void gameOver(){
        System.out.println("Last round finished, Game Over!!!");
        //then method to close game, to notify server that notifies to ALL players endGame
    }
}
