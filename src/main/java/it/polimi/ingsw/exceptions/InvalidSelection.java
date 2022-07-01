package it.polimi.ingsw.exceptions;

/**
 * Signals an Input that doesn't respect the needed criteria
 *
 * @author Andrea Albergo
 */
public class InvalidSelection extends Exception{

    public String getMessage(){
        return "Your choice is invalid try again";
    }
}
