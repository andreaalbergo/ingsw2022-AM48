package it.polimi.ingsw.client;

import it.polimi.ingsw.client.actions.*;
import it.polimi.ingsw.client.messages.QuitMessage;
import it.polimi.ingsw.costanti.Constants;
import it.polimi.ingsw.model.Cloud;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.IslandTile;

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
        System.out.println("I AM IN moveMotherNature");
        if(view.getTurnPhase() > 1){
            System.out.println("Not in the correct game phase to perform this command");
        }
        MoveMotherNature action;
        try {
            action = new MoveMotherNature(in);
            System.out.println("Stai dicendo che vuoi fare " + action.getNumber_of_steps() + " passi");
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
        if(view.getTurnPhase() != 2){
            System.out.println("Not in the correct game phase to perform this command");
        }
        String whereto = action[1].toUpperCase();
        if(whereto.equals("DININGROOM")){
            Color color = Color.parseInput(action[2]);
            return new MoveStudentToDiningRoom(color);
        }
        if(whereto.equals("ISLAND")){
            int index = Integer.parseInt(action[2]);
            Color color = Color.parseInput(action[3]);
            IslandTile islandTile = view.getIslands().get(index);
            System.out.println("Vuoi muoverlo nell'isola "+ islandTile.getIslandID());
            return new MoveStudentToIsland(islandTile,color);
        }
        return null;
    }

    public EndTurn pickCloud(String[] in) {
        if(view.getTurnPhase() != 3){
            System.out.println("Not in the correct game phase to perform this command");
            return null;
        }
        int index = Integer.parseInt(in[1]);
       try{
           Cloud cloud = view.getClouds().get(index - 1);
       } catch (IndexOutOfBoundsException e){
           System.out.println("The cloud that you selected...vanished, or was never there" +
                   ", you tell me...but you have to pick another one... :( ");
           return null;
       }
        if (view.getClouds().size() == 1){
            return new EndTurn(view.getClouds().get(0),true);
        }
        return new EndTurn(view.getClouds().get(index - 1));
    }
}
