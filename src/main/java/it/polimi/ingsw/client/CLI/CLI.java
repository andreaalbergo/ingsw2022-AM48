package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.client.ClientView;
import it.polimi.ingsw.client.CommandHandler;
import it.polimi.ingsw.client.CommandParser;
import it.polimi.ingsw.client.ConnectionSocket;
import it.polimi.ingsw.client.messages.ChooseMode;
import it.polimi.ingsw.client.messages.ChooseTowerColor;
import it.polimi.ingsw.client.messages.ChooseWizard;
import it.polimi.ingsw.client.messages.NumberOfPlayers;
import it.polimi.ingsw.costanti.Constants;
import it.polimi.ingsw.model.Mode;
import it.polimi.ingsw.model.Tower;
import it.polimi.ingsw.model.Wizard;
import it.polimi.ingsw.server.messages.RequestWizard;
import it.polimi.ingsw.server.messages.SetMode;
import it.polimi.ingsw.server.messages.SetPlayersRequest;
import it.polimi.ingsw.server.messages.TowerRequest;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CLI implements Runnable, PropertyChangeListener {

    private final Scanner in;

    private final ClientView clientView;
    private final CommandHandler handler;
    private final PrintStream out;
    private boolean active;
    private ConnectionSocket socket;

    public final Logger logger = Logger.getLogger(getClass().getName());
    private final PropertyChangeSupport listeners = new PropertyChangeSupport(this);



    public CLI() {
        in = new Scanner(System.in);
        out = new PrintStream(System.out);
        clientView = new ClientView(this);
        handler = new CommandHandler(clientView,this);
        active = true;
    }

    public static void main(String[] args) {
        System.out.println("WELCOME TO ERYANTIS");
        System.out.println("The following game was developed by:");
        System.out.println("Andrea Albergo, Loredan Barb, Edoardo Bozzini\n\n");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert the server's IP address (es. 127.0.0.1) -> ");
        String ipAddress = scanner.nextLine();
        System.out.println("Insert the port that the server is listening on (es. 1234) -> ");
        int port = scanner.nextInt();
        Constants.setPort(port);
        Constants.setAddress(ipAddress);
        CLI cli = new CLI();
        cli.run();
    }

    @Override
    public void run() {
        startCLI();
        while(isActiveGame()){
            if(clientView.getPhase() == 5 /* >  fase dopo aver fatto le scelte che differenziano il player dagl'altri, lo zero l'ho messo solo per ora*/){
                in.reset();
                String received = in.nextLine();
                System.out.println("Check da run di CLI: " + received);
                listeners.firePropertyChange("action",null, received);

            }
        }
        in.close();
        out.close();
    }

    public boolean isActiveGame() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private void startCLI() {
        String player = null;
        while (player == null){
            System.out.println("Insert your Nickname here ->");
            player = in.nextLine();
            System.out.println("You chose "+ player);
        }
        socket = new ConnectionSocket();
        clientView.setName(player);
        try{
            if(!socket.registration(player, handler, clientView)){
                System.err.println("There is no server with those specifications...");
                CLI.main(null);
            }else System.out.println("\n> Socket Connection completed succesully < ");
        } catch (Exception e) {
            startCLI();
        }
        listeners.addPropertyChangeListener("action", new CommandParser(socket,clientView));
    }

    public void choosePlayerNumber(){
        int numberofplayers;
        while (true){
            try {
                System.out.println("Insert here: ");
                //logger.log(Level.SEVERE,"Insert here: ");
                numberofplayers = Integer.parseInt(in.nextLine());
                break;
            } catch (NumberFormatException exception){
                System.out.println("The input is invalid...");
            }
        }
        logger.log(Level.INFO,"Stai mandandao messaggio con " + numberofplayers + "giocatori");
        socket.send(new NumberOfPlayers(numberofplayers));
        clientView.setPhase(1);

    }

    public void chooseWizard(){
        Wizard wizard;
        while (true){
            try {
                System.out.println("Choose your Wizard: ");
                wizard = Wizard.parseInput(in.nextLine());
                if(Wizard.available.contains(wizard)){
                    socket.send(new ChooseWizard(wizard));
                    clientView.setPhase(4);
                    break;
                }else {
                    System.out.println("The Wizard is not available");
                }

            } catch (IllegalArgumentException exception){
                System.out.println("The input is invalid... Choose a Wizard not a random object dumbo");

            }
        }

    }

    public void chooseTower(){
        Tower tower;
        while (true){
            try {
                System.out.println("Choose your Tower's Color: ");
                tower = Tower.parseInput(in.nextLine());
                if(Tower.available.contains(tower)){
                    socket.send(new ChooseTowerColor(tower));
                    clientView.setPhase(3);
                    break;
                }else {
                    System.out.println("The Tower's Color you picked is not available");
                }

            } catch (IllegalArgumentException exception){
                System.out.println("The input is invalid... Choose a Tower not a random object dumbo");

            }
        }

    }

    public void chooseMode(){
        while(true){
            try{
                logger.log(Level.INFO,"Sei nella choosemode");
                System.out.println("(WRITE ALL CAPS)->");
                String choice = in.nextLine();
                choice = choice.toUpperCase();
                System.out.println("You selected the " + choice.toUpperCase() + " mode");
                if(choice.equals(Mode.EXPERT.toString())){
                    socket.send(new ChooseMode(true));
                } else if (choice.equals(Mode.NORMAL.toString())){
                    socket.send(new ChooseMode(false));
                }
                clientView.setPhase(2);
                break;
            }catch (IllegalArgumentException exception){
                System.out.println("Choose a mode (expert or normal) not a random thing.....dumbo");
            }
        }
    }

    public void initialSetup(String value){
        switch (value) {
            case "SetPlayersRequest" -> {
                //logger.log(Level.SEVERE,"Ok stai elaborando nell'initial setup");
                System.out.println("Choose the number of players [2 or 3]");
                choosePlayerNumber();
            }
            case "RequestWizard" -> {
                System.out.println(((RequestWizard)clientView.getAnswer()).getMessage() + "\nRemaining Wizards: ");
                Wizard.available.forEach(wizard -> System.out.print(wizard + " ,"));
                System.out.print(".\n");
                chooseWizard();
            }
            case "TowerRequest" -> {
                System.out.println(((TowerRequest)clientView.getAnswer()).getMessage() + "\nRemaining Towers: ");
                Tower.available.forEach(tower -> System.out.print( tower + " ,"));
                System.out.print(".\n");
                chooseTower();
            }
            case "SetMode" -> {
                System.out.println(((SetMode)clientView.getAnswer()).getMessage());
                chooseMode();
            }
            case "ChooseAssistandCard" -> {
                System.out.println(((SetMode)clientView.getAnswer()).getMessage());

            }
        }
    }


    /**
     * Waits for the arrival of the Server Answer that is sent here (after it was processed) by the CommandHandler;
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String command = (evt.getNewValue() != null) ? evt.getNewValue().toString() : null;
        switch (evt.getPropertyName()) {
            case "setup" -> {
                assert command != null;
                logger.log(Level.SEVERE,"Ho ricevuto il comando: " + command);
                initialSetup(command);
            }

        }
    }
}
