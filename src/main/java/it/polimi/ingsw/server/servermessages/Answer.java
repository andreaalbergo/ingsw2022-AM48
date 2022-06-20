package it.polimi.ingsw.server.servermessages;

import java.io.Serializable;

public interface Answer extends Serializable {
    Object getMessage();
}
