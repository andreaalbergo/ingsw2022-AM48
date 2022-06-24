package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.client.ClientView;
import it.polimi.ingsw.client.CommandHandler;
import it.polimi.ingsw.client.CommandParser;
import it.polimi.ingsw.client.ConnectionSocket;
import it.polimi.ingsw.client.messages.ChooseDetails;
import it.polimi.ingsw.client.messages.ChooseMode;
import it.polimi.ingsw.client.messages.NumberOfPlayers;
import it.polimi.ingsw.costanti.Constants;
import it.polimi.ingsw.exceptions.DuplicateNicknameException;
import it.polimi.ingsw.model.Mode;
import it.polimi.ingsw.model.Tower;
import it.polimi.ingsw.model.Wizard;
import it.polimi.ingsw.server.servermessages.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.PrintStream;
import java.util.List;
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

    /**
     * main method of class CLI, it runs after choosing option "1" in Eriantys app, it asks for IP address, then port to
     * create a socket connection; after that nickname and if first player (as host of the lobby) choose number of
     * players and game mode. Finally, before starting the game every player need to choose a tower, a wizard and first
     * assistant card to draw for first turn sorting.
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(
                        "      ___           ___           ___           ___           ___           ___                       ___     \n" +
                        "     /\\  \\         /\\  \\         |\\__\\         /\\  \\         /\\__\\         /\\  \\          ___        /\\  \\    \n" +
                        "    /::\\  \\       /::\\  \\        |:|  |       /::\\  \\       /::|  |        \\:\\  \\        /\\  \\      /::\\  \\   \n" +
                        "   /:/\\:\\  \\     /:/\\:\\  \\       |:|  |      /:/\\:\\  \\     /:|:|  |         \\:\\  \\       \\:\\  \\    /:/\\ \\  \\  \n" +
                        "  /::\\~\\:\\  \\   /::\\~\\:\\  \\      |:|__|__   /::\\~\\:\\  \\   /:/|:|  |__       /::\\  \\      /::\\__\\  _\\:\\~\\ \\  \\ \n" +
                        " /:/\\:\\ \\:\\__\\ /:/\\:\\ \\:\\__\\     /::::\\__\\ /:/\\:\\ \\:\\__\\ /:/ |:| /\\__\\     /:/\\:\\__\\  __/:/\\/__/ /\\ \\:\\ \\ \\__\\\n" +
                        " \\:\\~\\:\\ \\/__/ \\/_|::\\/:/  /    /:/~~/~    \\/__\\:\\/:/  / \\/__|:|/:/  /    /:/  \\/__/ /\\/:/  /    \\:\\ \\:\\ \\/__/\n" +
                        "  \\:\\ \\:\\__\\      |:|::/  /    /:/  /           \\::/  /      |:/:/  /    /:/  /      \\::/__/      \\:\\ \\:\\__\\  \n" +
                        "   \\:\\ \\/__/      |:|\\/__/     \\/__/            /:/  /       |::/  /     \\/__/        \\:\\__\\       \\:\\/:/  /  \n" +
                        "    \\:\\__\\        |:|  |                       /:/  /        /:/  /                    \\/__/        \\::/  /   \n" +
                        "     \\/__/         \\|__|                       \\/__/         \\/__/                                   \\/__/    \n"
                +"The following game was developed by:\t\t\t\t   "
                +Constants.ANSI_RESET+"\n"+Constants.ANSI_BACKGROUND_BLACK+Constants.ANSI_RED
                +"Andrea Albergo, "+Constants.ANSI_BLUE+"Loredan Barb, "
                +Constants.ANSI_YELLOW+"Edoardo Bozzini."+Constants.ANSI_RESET+"\n"
        );
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert the server's IP address (E.g. \"127.0.0.1\") -> ");
        String ipAddress = scanner.nextLine();
        System.out.println("Insert the port that the server is listening on (E.g. \"1234\") -> ");
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
            if(clientView.getPhase() >= 4  /* >  fase dopo aver fatto le scelte che differenziano il player dagl'altri, lo zero l'ho messo solo per ora*/){
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
        } catch (DuplicateNicknameException e) {
            startCLI();
        }
        listeners.addPropertyChangeListener("action", new CommandParser(socket,clientView));
    }

    public void choosePlayerNumber(){
        int numberOfPlayers;
        while (true){
            try {
                System.out.println("Insert here: ");
                //logger.log(Level.SEVERE,"Insert here: ");
                numberOfPlayers = Integer.parseInt(in.nextLine());
                break;
            } catch (NumberFormatException exception){
                System.out.println("The input is invalid...it must be a number between 2 and 3");
            }
        }
        logger.log(Level.INFO,"Stai mandandao messaggio settando " + numberOfPlayers + "giocatori");
        socket.send(new NumberOfPlayers(numberOfPlayers));
        clientView.setPhase(1);

    }

    public Wizard chooseWizard(List<Wizard> availableWizards){
        Wizard wizard;
        while (true){
            try {
                System.out.println("Choose your Wizard: ");
                //System.out.println("Here's the list of available wizards: "+Wizard.getAvailable());
                wizard = Wizard.parseInput(in.nextLine());
                if(availableWizards.contains(wizard)){
                    return wizard;
                } else {
                    System.out.println("Wizard not available!");
                    //then I should create a class to reflush buffer and clean it without deleting all the entire stream
                }
            } catch (IllegalArgumentException e){
                System.out.println("The input is invalid... Choose a Wizard not a random object dumbo");
            }
        }

    }

    public Tower chooseTower(List<Tower> remainingTowers){
        Tower tower;
        while (true){
            try {
                System.out.println("Choose your Tower's Color: ");
                tower = Tower.parseInput(in.nextLine());
                if(remainingTowers.contains(tower)){
                    return tower;
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
            case "SetDatails" -> {
                System.out.println((clientView.getAnswer()).getMessage() + "\nRemaining Wizards: ");
                ((SetDatails) clientView.getAnswer()).getRemainingWizards().forEach(n -> System.out.print(n + ", "));
                System.out.print(".\n");
                Wizard wizard = chooseWizard(((SetDatails) clientView.getAnswer()).getRemainingWizards());
                System.out.println("\nRemaining Towers: ");
                ((SetDatails) clientView.getAnswer()).getRemainingTowers().forEach(n -> System.out.print(n + ", "));
                Tower tower = chooseTower(((SetDatails) clientView.getAnswer()).getRemainingTowers());
                socket.send(new ChooseDetails(tower,wizard));
            }

            case "SetMode" -> {
                System.out.println(((SetMode)clientView.getAnswer()).getMessage());
                chooseMode();
            }
            case "ChooseAssistandCard" -> {
                System.out.println(((SetMode)clientView.getAnswer()).getMessage() + "\n Available Cards: ");
                ((ChooseAssistantCard) clientView.getAnswer()).getAvailable_cards().forEach(n -> System.out.print(n + ", "));
                chooseAssistantCard();
            }
        }
    }

    private void chooseAssistantCard() {
        System.out.println("Select a card: ");
        System.out.println(">");

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
