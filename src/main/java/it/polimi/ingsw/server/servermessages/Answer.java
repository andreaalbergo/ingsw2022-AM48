package it.polimi.ingsw.server.servermessages;

import java.io.Serializable;

/**
 * Interface implented by the messages of the server
 */
public interface Answer extends Serializable {
    Object getMessage();
}
