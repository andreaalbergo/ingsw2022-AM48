package it.polimi.ingsw.server.servermessages;

public class SetPlayersRequest implements Answer {

    private String message;


    public SetPlayersRequest(String message){
        this.message = message;
    }

    @Override
    public Object getMessage() {
        return message;
    }
}
