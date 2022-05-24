package it.polimi.ingsw.client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This class is used to transform the input from the client to easy instances of "Message" to be sent to the Server
 *
 * @author Andrea Albergo
 */
public class CommandParser implements PropertyChangeListener {

    private final ConnectionSocket socket;

    private final ClientView view;

    private final InputCheck verifier;

    public CommandParser(ConnectionSocket socket, ClientView view) {
        this.socket = socket;
        this.view = view;
        verifier = new InputCheck(socket, view);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
