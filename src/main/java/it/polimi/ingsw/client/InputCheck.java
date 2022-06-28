package it.polimi.ingsw.client;

import it.polimi.ingsw.client.actions.ChoiceAssistantCard;
import it.polimi.ingsw.client.actions.MoveMotherNature;
import it.polimi.ingsw.client.actions.UserCommand;
import it.polimi.ingsw.client.messages.QuitMessage;
import it.polimi.ingsw.costanti.Constants;

public class InputCheck {

    private final ConnectionSocket socket;

    private final ClientView view;

    private static final String RED = Constants.ANSI_RED;

    private static final String RST = Constants.ANSI_RESET;


    public InputCheck(ConnectionSocket socket, ClientView view) {
        this.socket = socket;
        this.view = view;
    }

    public MoveMotherNature moveMotherNature(String[] in){
        MoveMotherNature action = null;
        try {
            action = new MoveMotherNature(in);
            int number_of_steps = Integer.parseInt(in[1]);
            if(number_of_steps < 0 || number_of_steps > view.getChosenCard().getNumber_of_steps()){
                System.err.println("The number of steps you chose is invalid");
                return null;
            }else
                return action;
        }catch(NumberFormatException e){
            System.out.println(RED + "Please input a number next time..." + RST);
            return null;
        }
    }

    public void quit() {
        socket.send(new QuitMessage("Player " + view.getNickname() + "left the game...the match is ending, we'll miss ya!!"));
        System.err.println(RED + "Disconnecting from server :( ... Come back soon" + RST);
        System.exit(0);
    }

    public UserCommand moveStudent(String[] action) {
        //DA implementare
        return null;
    }
}
