package it.polimi.ingsw.exceptions;

public class DuplicateNicknameException extends Exception{

    public String getMessage(){
        return "The nickname you chose has already been picked";
    }
}
