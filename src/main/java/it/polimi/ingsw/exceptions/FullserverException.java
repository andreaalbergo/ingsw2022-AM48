package it.polimi.ingsw.exceptions;

/**
 * FullserverException signals when the game is already full
 *
 * @author Andrea Albergo
 */
public class FullserverException extends Exception{

    public String getMessage(){
        return "The Server is full";
    }


}
