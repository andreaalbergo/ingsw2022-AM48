package it.polimi.ingsw.client.actions;

import java.io.Serializable;

/**
 * UserCommand interface is an extension of Serializable to make the clients' inputs possible to read.
 *
 * @author Andrea Albergo
 */
public interface UserCommand extends Serializable {

     String toString();
}
