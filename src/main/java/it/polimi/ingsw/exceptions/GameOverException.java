package it.polimi.ingsw.exceptions;

/**
 * GameOverException signals an occurance that leads to the end of the game
 *
 * @author Andrea Albergo
 */
public class GameOverException extends Exception{

    public String getMessage(){
        return "the Game is ending...we have a winner";
    }
}
