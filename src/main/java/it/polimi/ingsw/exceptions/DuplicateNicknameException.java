package it.polimi.ingsw.exceptions;

/**
 * Class DuplicateNameException is sent when a player tries to use the same nickname as a player that is alreasdy logged in
 *
 * @author Andrea Albergo
 */
public class DuplicateNicknameException extends Exception{

    public String getMessage(){
        return "The nickname you chose has already been picked";
    }
}
