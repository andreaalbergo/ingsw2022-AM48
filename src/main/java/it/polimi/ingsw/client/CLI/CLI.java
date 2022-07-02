package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.client.ClientView;
import it.polimi.ingsw.client.CommandHandler;
import it.polimi.ingsw.client.CommandParser;
import it.polimi.ingsw.client.ConnectionSocket;
import it.polimi.ingsw.client.actions.ChoiceAssistantCard;
import it.polimi.ingsw.client.gameBoard.GameBoard;
import it.polimi.ingsw.client.messages.ChooseDetails;
import it.polimi.ingsw.client.messages.ChooseMode;
import it.polimi.ingsw.client.messages.NumberOfPlayers;
import it.polimi.ingsw.costanti.Constants;
import it.polimi.ingsw.costanti.Move;
import it.polimi.ingsw.exceptions.DuplicateNicknameException;
import it.polimi.ingsw.exceptions.InvalidSelection;
import it.polimi.ingsw.model.AssistantCard;
import it.polimi.ingsw.model.Mode;
import it.polimi.ingsw.model.Tower;
import it.polimi.ingsw.model.Wizard;
import it.polimi.ingsw.server.servermessages.*;
import it.polimi.ingsw.server.servermessages.gamemessages.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.PrintStream;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class CLI is used by the user who starts the Eryantis app and chooses this type of view of the game, which is plain
 * text.
 */
public class CLI implements Runnable, PropertyChangeListener {
    private final Scanner in;
    private final ClientView clientView;
    private final CommandHandler handler;
    private final PrintStream out;
    private boolean active;
    private ConnectionSocket socket;
    public final Logger logger = Logger.getLogger(getClass().getName());
    private final PropertyChangeSupport listeners = new PropertyChangeSupport(this);
    private GameBoard gameBoard;


    /**
     * Constructor CLI to create its instance.
     */
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
     * @param args of type String[] - arguments for command line of main method.
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

    /**
     * Method run to make the CLI active for the user.
     */
    @Override
    public void run() {
        startCLI();
        while(isActiveGame()){
            if(clientView.getPhase() >= 3 ){
                in.reset();
                String received = in.nextLine();
                System.out.println("ACTION: " + received);
                listeners.firePropertyChange("action",null, received);
            }
        }
        in.close();
        out.close();
    }

    /**
     * Method isActiveGame is a getter of type synchronized.
     *
     * @return of boolean.
     */
    public synchronized boolean isActiveGame() {
        return active;
    }

    /**
     * Method setActive is a setter.
     *
     * @param active of type boolean
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Method startCLI is used by run method to make the new user choose, after ip address and port, a nickname.
     */
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

    /**
     * Method choosePlayerNumber is used when the host player has chosen its nickname. It can be 2 or 3 players.
     */
    public void choosePlayerNumber(){
        int numberOfPlayers;
        while (true){
            try {
                System.out.println("Insert here: ");
                numberOfPlayers = Integer.parseInt(in.nextLine());
                break;
            } catch (NumberFormatException exception){
                System.out.println("The input is invalid...it must be a number between 2 and 3");
            }
        }
        //TODO
        logger.log(Level.INFO,"Stai mandandao messaggio settando " + numberOfPlayers + "giocatori");
        socket.send(new NumberOfPlayers(numberOfPlayers));
        clientView.setPhase(1);
    }

    /**
     * Method chooseWizard is used when the match is started and players need to choose one of the available wizards.
     *
     * @param availableWizards of List<> - the list of available wizards.
     * @return of type Wizard.
     */
    public Wizard chooseWizard(List<Wizard> availableWizards){
        Wizard wizard;
        while (true){
            try {
                System.out.println("Choose your Wizard: ");
                wizard = Wizard.parseInput(in.nextLine());
                if(availableWizards.contains(wizard)){
                    return wizard;
                } else {
                    System.out.println("Wizard not available!");
                }
            } catch (IllegalArgumentException e){
                System.out.println("The input is invalid... Choose a Wizard not a random object dumbo");
            }
        }

    }

    /**
     * Method chooseTower is used after the player has chosen the wizard.
     *
     * @param remainingTowers of type List<> - remainingTowers.
     * @return of type Tower - the chosen tower.
     */
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

    /**
     * Method chooseMode is used when the host player has set the number of players. Mode can be normal or expert.
     */
    public void chooseMode(){
        while(true){
            try{
                //TODO
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
                break;
            }catch (IllegalArgumentException exception){
                System.out.println("Choose a mode (expert or normal) not a random thing.....dumbo");
            }
        }
    }

    /**
     * Method initialSetup assembles all the previous methods to distinguish them by a Message in order to send it to
     * the socket server.
     *
     * @param value of type String.
     */
    public void initialSetup(String value){
        switch (value) {
            case "SetPlayersRequest" -> {
                System.out.println("Choose the number of players [2 or 3]");
                choosePlayerNumber();
            }
            case "SetDetails" -> {
                System.out.println((clientView.getAnswer()).getMessage() + "\nRemaining Wizards: ");
                ((SetDetails) clientView.getAnswer()).getRemainingWizards().forEach(n -> System.out.print(n + ", "));
                System.out.print(".\n");
                Wizard wizard = chooseWizard(((SetDetails) clientView.getAnswer()).getRemainingWizards());
                System.out.println("\nRemaining Towers: ");
                ((SetDetails) clientView.getAnswer()).getRemainingTowers().forEach(n -> System.out.print(n + ", "));
                Tower tower = chooseTower(((SetDetails) clientView.getAnswer()).getRemainingTowers());
                socket.send(new ChooseDetails(tower,wizard));
                clientView.setPhase(1);
            }

            case "SetMode" -> {
                System.out.println((clientView.getAnswer()).getMessage());
                chooseMode();
            }

        }
    }

    /**
     * Method chooseAssistantCard is used after all players have chosen wizard and tower.
     *
     * @param assistantCards of type List<> - the list of assistant card.
     */
    private void chooseAssistantCard(List<AssistantCard> assistantCards) {
        System.out.println("Select a card: ");
        System.out.println(">");
        AssistantCard assistantCard = null;
        try {
            assistantCard = AssistantCard.parseInput(in.nextLine());
            System.out.println("You selected card " + assistantCard.getValue() + " with steps: " +assistantCard.getNumber_of_steps());
        } catch (InvalidSelection e) {
            System.out.println("That's not a valid input, choose again");
            System.out.println(">");
        }
        while (!assistantCards.contains(assistantCard)){
            System.out.println("You already selected this card in the past, is not like you can use it twice...");
            System.out.println("Pick one of these: \n");
            ((ChooseAssistantCard) clientView.getAnswer()).getAvailable_cards().forEach(n -> System.out.print(n + ", "));
            System.out.println(".");
            try {
                assistantCard = AssistantCard.parseInput(in.nextLine());
                System.out.println("You selected card " + assistantCard.getValue() + " with steps: " +assistantCard.getNumber_of_steps());
            } catch (InvalidSelection e) {
                System.out.println("That's not a valid input, choose again");
                System.out.println(">");
            }
        }
        clientView.setChosenCard(assistantCard);
        socket.send(new ChoiceAssistantCard(assistantCard));
    }

    /**
     * Method gamePhase is needed to the game states in order to keep changing its state after every player's single
     * input command.
     *
     * @param value of type String.
     */
    public void gamePhase(String value){
        switch (value){
            case "chooseAssistantCard" -> {
                System.out.println(clientView.getAnswer().getMessage() + "\n Available Cards: ");
                ((ChooseAssistantCard) clientView.getAnswer()).getAvailable_cards().forEach(n -> System.out.print(n + ", "));
                System.out.println(".");
                chooseAssistantCard(((ChooseAssistantCard)clientView.getAnswer()).getAvailable_cards());
                clientView.setPhase(3);
                clientView.setTurnPhase(0);
            }

            case "movedMotherNature" -> {
                MovedMotherNature message = (MovedMotherNature)clientView.getAnswer();
                if(message.getSteps() != 0){
                    clientView.setTurnPhase(2);
                    clientView.setPhase(4);
                    gameBoard.setMotherNaturePosition(message.getIslandTile());
                }
                clientView.setInputEnabler(message.isCheck());

            }

            case "startTurnMessage" -> {
                System.out.println((clientView.getAnswer()).getMessage());
                System.out.println("Ok il tuo enabler è " + ((StartTurnMessage)clientView.getAnswer()).getEnabler());
                //clientView.setPhase(3);
                clientView.setInputEnabler(((StartTurnMessage)clientView.getAnswer()).getEnabler());
                clientView.setTurnPhase(1);
            }

            case "winMessage" ->{
                System.out.println((clientView.getAnswer()).getMessage());
                System.out.println("Congratulations " + clientView.getNickname());
                System.exit(0);
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
            //TODO
            case "setup" -> {
                assert command != null;
                logger.log(Level.SEVERE,"Ho ricevuto il comando: " + command);
                initialSetup(command);
            }
            case "chooseAssistantCard" -> {
                assert command != null;
                logger.log(Level.INFO,"CHOOSE ASSISTANT CARD COMMAND: " + command);
                gamePhase("chooseAssistantCard");
            }

            case "MovedMotherNature" -> {
                logger.log(Level.INFO,"MOVE MOTHERNATURE CHECK: " + command);
                gamePhase("movedMotherNature");
            }

            case "gameError" -> {
                assert command != null;
                logger.log(Level.INFO,"ERRORE CHECK: " + command);
                errorHandling((GameError) evt.getNewValue());

            }
            case "startTurnMessage" -> {
                assert command != null;
                logger.log(Level.INFO,"INIZIA TURNO CHECK: " + command);
                gameBoard.printCLI();
                gamePhase("startTurnMessage");
            }
            case "gameOver" -> {
                assert command != null;//TODO
                logger.log(Level.INFO, String.valueOf(((GameOver) evt.getNewValue()).getMessage()));
                String reason = ((GameOver) evt.getNewValue()).getReason();
                if(reason != null){
                    System.out.println(reason);
                }
                System.exit(0);
            }

            case "winMessage" ->{
                assert command != null;
                gamePhase("winMessage");
            }
            case "moveMessage" -> updateCLI((MoveMessage) evt.getNewValue());
        }
    }

    /**
     * Method updateCLI is used to handle every player's turn actions and update the model through the server socket.
     *
     * @param message of type MoveMessage.
     */
    private void updateCLI(MoveMessage message) {
        Move move = message.getMessage();
        if(Objects.equals(move.getId(), clientView.getNickname())){
            clientView.setInputEnabler(true);
        }

        if(move.getCloudList()!=null && Objects.equals(move.getId(), clientView.getNickname())){
            clientView.setInputEnabler(false);
            clientView.setClouds(move.getCloudList());
        }else if(move.getCloudList()!=null)
            clientView.setClouds(move.getCloudList());
        if(move.getIslandTiles()!=null) {
            clientView.setIslands(move.getIslandTiles());
            gameBoard.setArchipelagoGrid(move.getIslandTiles());
        }
        if (move.getEntrance()!=null)
            clientView.updateEntrance(move.getId(), move.getEntrance());
        if(move.getDiningRoom()!=null)
            clientView.updateDining(move.getId(), move.getDiningRoom());

        if(move.getMoved_students() != null){
            if (move.getMoved_students() == 4 && gameBoard.getNumberOfPlayers() == 3 ) clientView.setTurnPhase(3);
            if (move.getMoved_students() == 3 && gameBoard.getNumberOfPlayers() == 2) clientView.setTurnPhase(3);
        }
        gameBoard.printCLI();


        System.out.println("La fase è " + clientView.getTurnPhase());
    }

    /**
     * Method errorHandling is used to handle every possible error committed by the users like bad typing at the bad
     * time, exc...
     *
     * @param error of type GameError.
     */
    private void errorHandling(GameError error) {
        switch (error.getError()) {
            case INVALIDINPUT,INVALIDMOVE,NOTYOURTURN,ALREADYCHOSEN,OUTOFBOUNDINPUT -> {
                if (error.getMessage() != null) {
                    System.out.println(error.getMessage());
                }
                clientView.setTurnActive(true);
                clientView.setInputEnabler(true);

            }
            case SERVER_IS_FULL -> {
                System.out.println("Sorry the server is FULL already, closing connection now");
                System.exit(0);
            }
            default -> System.out.println("Generic error!");

        }
    }

    /**
     * Method getGameBoard is a getter.
     *
     * @return of type GameBoard.
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * Method createGameBoard creates a new GameBoard instance by giving it the number of players.
     *
     * @param size of type int - the number of players.
     */
    public void createGameBoard(int size) {
        gameBoard = new GameBoard(size);
    }
}
