package it.polimi.ingsw.client.messages;

import it.polimi.ingsw.client.actions.UserCommand;
import it.polimi.ingsw.client.messages.Message;

import java.io.Serializable;

public class SerializedMessage implements Serializable {
    public final Message message;
    public final UserCommand command;

    public SerializedMessage(Message message){
        this.message = message;
        this.command = null;
    }

    public SerializedMessage(UserCommand command){
        this.message = null;
        this.command = command;
    }




}
