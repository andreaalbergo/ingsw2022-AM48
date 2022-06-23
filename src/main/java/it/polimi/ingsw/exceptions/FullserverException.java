package it.polimi.ingsw.exceptions;

public class FullserverException extends Exception{

    public String getMessage(){
        return "The Server is full";
    }


}
