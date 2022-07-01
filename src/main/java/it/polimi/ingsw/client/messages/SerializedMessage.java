package it.polimi.ingsw.client.messages;

import it.polimi.ingsw.client.actions.UserCommand;

import java.io.Serializable;

/**
 * Class SerializedMessage is a way to serialize and send a custom message to the socket server. To know more check
 * class Serializable.
 */
public class SerializedMessage implements Serializable {
    public final Message message;
    public final UserCommand command;

    /**
     * Constructor SerializedMessage to create its own instance.
     *
     * @param message of type Message.
     */
    public SerializedMessage(Message message){
        this.message = message;
        this.command = null;
    }

    /**
     * Constructor SerializedMessage to create its own instance. (Overloading).
     *
     * @param command of type UserCommand.
     */
    public SerializedMessage(UserCommand command){
        this.message = null;
        this.command = command;
    }




}
