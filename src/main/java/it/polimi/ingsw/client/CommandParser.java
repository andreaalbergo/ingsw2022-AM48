package it.polimi.ingsw.client;

import it.polimi.ingsw.client.actions.UserCommand;

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

    public synchronized boolean action(String input) {
        String[] action = input.split(" ");
        String command = action[0];
        UserCommand message;
        /*
        try{
            switch (command.toUpperCase()){
                //case "MOVEMOTHERNATURE" -> message = verifier.
            }
        }catch (){}
        */
        return false;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(!view.isInputEnabler()){
            System.out.println("NOT YOUR TURN");
        }else if (action(evt.getNewValue().toString())){
            view.setInputEnabler(false);
        }else view.setInputEnabler(true);
    }
}
