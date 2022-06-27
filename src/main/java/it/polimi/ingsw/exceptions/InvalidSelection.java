package it.polimi.ingsw.exceptions;

public class InvalidSelection extends Exception{

    public String getMessage(){
        return "Your choice is invalid try again";
    }
}
