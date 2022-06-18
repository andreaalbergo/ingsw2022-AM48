package it.polimi.ingsw.client;

import it.polimi.ingsw.client.actions.GameAction;
import it.polimi.ingsw.client.actions.MoveMotherNature;

public class InputCheck {

    private final ConnectionSocket socket;

    private final ClientView view;


    public InputCheck(ConnectionSocket socket, ClientView view) {
        this.socket = socket;
        this.view = view;
    }

    public GameAction moveMotherNature(String[] in){
        GameAction action = null;
        /*
        try {
            action = new MoveMotherNature();
        }

         */
        return action;
    }
}
