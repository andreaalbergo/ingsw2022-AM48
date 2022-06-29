package it.polimi.ingsw.exceptions;

public class GameOverException extends Exception{

    public String getMessage(){
        return "the Game is ending...we have a winner";
    }
}
