package it.polimi.ingsw.client;

import it.polimi.ingsw.client.actions.UserCommand;
import it.polimi.ingsw.costanti.Constants;

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

    private static final String RED = Constants.ANSI_RED;

    private static final String RST = Constants.ANSI_RESET;

    public CommandParser(ConnectionSocket socket, ClientView view) {
        this.socket = socket;
        this.view = view;
        verifier = new InputCheck(socket, view);
    }

    public synchronized boolean action(String input) {
        System.out.println("Mi è arrivato: "+ input);
        String[] action = input.split(" ");
        String command = action[0];
        System.out.println("Action 1: "+ action[1]);
        UserCommand response;

        try{
            switch (command.toUpperCase()){
                case "MOVEMOTHERNATURE" -> response = verifier.moveMotherNature(action);
                case "MOVESTUDENTTO" -> response = verifier.moveStudent(action);
                //case "BUYCARD" -> response = verifier.
                case "PICKCLOUD" -> response = verifier.pickCloud(action);
                case "QUIT" -> {
                    verifier.quit();
                    return true;
                }
                default -> {
                    System.out.println("The input you just gave doesn't make sense to me :( ... try again, look at the " +
                            "command list on the right");
                    return false;
                }

            }
        }catch (NumberFormatException e){
            System.out.println(RED + "PSS PSS insert a number this time"+ RST);
            return false;
        }
        if( response != null){
            socket.send(response);
            return true;
        }

        return false;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(!view.isInputEnabler()){
            System.out.println(RED + "NOT YOUR TURN" + RST);
        }else if (action(evt.getNewValue().toString())){
            view.setInputEnabler(false);
        }else
            view.setInputEnabler(true);
    }
}
