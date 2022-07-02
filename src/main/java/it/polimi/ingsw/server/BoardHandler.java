package it.polimi.ingsw.server;

import it.polimi.ingsw.client.actions.ChoiceAssistantCard;
import it.polimi.ingsw.client.actions.UserCommand;
import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.exceptions.GameOverException;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.server.servermessages.*;
import it.polimi.ingsw.server.servermessages.gamemessages.GameOver;
import it.polimi.ingsw.server.servermessages.gamemessages.MovedMotherNature;
import it.polimi.ingsw.server.servermessages.gamemessages.StartTurnMessage;
import it.polimi.ingsw.server.servermessages.gamemessages.WinMessage;

import java.beans.PropertyChangeSupport;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * BoardHandler handles the board (game) and the player on the server sides, invoking the game controller for changes
 *
 * @author andrea albergo
 */
public class BoardHandler {
    private final MultiplayerServer server;
    private final GameController controller;

    public Board game() {
        return board;
    }

    private final Board board;

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    private int numberOfPlayers;

    private final Random rnd = new Random();


    private final PropertyChangeSupport controllerListener = new PropertyChangeSupport(this);

    private boolean isExpertMode;
    //private int currentPlayer;
    private boolean isStarted;

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    private int phase = 0;
    private Logger logger =  Logger.getLogger(getClass().getName());

    public GameController getController() {
        return controller;
    }

    /**
     * BoardHandler constructor
     *
     * @param server Multiplayer server
     */
    public BoardHandler(MultiplayerServer server) {
        this.server = server;
        isStarted = false;
        board = new Board();
        controller = new GameController(board, this);
        controllerListener.addPropertyChangeListener(controller);
    }

    /**
     * startGame method notifies the server that notifies all players in the lobby that a new game is started, also the
     * controller is notified by this event.
     */
    public void startGame(){
        isStarted = true;
        phase = 1;
        for(Player player : board.getActivePlayers() ){
            setupPlayerSchoolBoard(player);
            System.out.println(player.getNickname()+ " ha questa entrance " + player.getSchoolBoard().getEntrance());
        }
        //le isole si settano con la creazione del board
        board.getBoardManager().createCloudList();
        setClouds();
    }

    /**
     * Sets the clouds that are than sent to the client for them to be printed in the GameBoard class
     */
    public void setClouds() {
        for (Cloud cloud : board.getBoardManager().getClouds()){
            try {
                //TODO CHIAMA BAG PER SETTARE LE CLOUDS
                //board.getBoardManager().getBag().setupCloud(cloud,board.getActivePlayers().size());
                //cloud.fillStudents();
                if(board.getActivePlayers().size()==3) {
                    for (int i = 0; i < 4; i++) {
                        cloud.addStudentToCloud(Color.colorFromIndex(board.getBoardManager().getBag().getRandomColorFromBag()));
                    }
                } else {
                    for (int i = 0; i < 3; i++) {
                        cloud.addStudentToCloud(Color.colorFromIndex(board.getBoardManager().getBag().getRandomColorFromBag()));
                    }
                }
                System.out.println("Ho settato le clouds " + cloud.getCloudCells().get(0) + cloud.getCloudCells().get(1) + cloud.getCloudCells().get(2));
            } catch (GameOverException e) {
                sendAll(new GameOver("The bag is empty so the game is ending..."));
            }
        }

    }


    /**
     * Distinguishes normal and expert mode
     *
     * @param expertMode boolean
     */
    public void setExpertMode(boolean expertMode) {
        isExpertMode = expertMode;
    }

    /**
     * getter
     *
     * @return boolean
     */
    public boolean isExpertMode() {
        return isExpertMode;
    }

    /**
     * sets the number of players for the game
     *
     * @param numberOfPlayers int
     */
    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    /**
     * Closes the communication with one the players
     *
     * @param idClient Integer
     */
    public void unregisterPlayer(Integer idClient) {
       Player player = board.getActivePlayers().get(idClient);
    }

    /**
     *method used to communicate to the player the choice of the player
     *
     */
    private void chooseAssistantCard(){
        Player player = board.getCurrentPlayer();
        ChooseAssistantCard request = new ChooseAssistantCard(player.getNickname() + " please choose your assistant card from one of the list below:\n\n", player.getNickname(), player.getAssistantCards());
        sendtoPlayer(request,player.getPlayerID());
    }

    /**
     * Sends an answer to a specific player
     *
     * @param answer Answer
     * @param id Integer
     */
    public void sendtoPlayer(Answer answer, Integer id) {
        server.getIdtoClientMap().get(id).send(answer);
    }

    /**
     * A message is sent to all the active player
     * An answer is sent to all the active player
     *
     * @param customMessage CustomMessage
     */
    public void sendAll(CustomMessage customMessage) {
        for(Player player: board.getActivePlayers())
            server.getIdtoClientMap().get(player.getPlayerID()).send(customMessage);
    }

    /**
     *
     *  An answer is sent to all the active player
     * @param message Answer
     */
    public void sendAll(Answer message) {
        for(Player player: board.getActivePlayers())
            server.getIdtoClientMap().get(player.getPlayerID()).send(message);
    }

    /**
     * Sends an answer to all except for the player with IdClient
     *
     * @param answer Answer
     * @param idClient int
     */
    public void sendAllExcept(Answer answer, int idClient) {
        for (Player player: board.getActivePlayers()) {
            if(server.getNametoIdMap().get(player.getNickname()) != idClient){
                server.getIdtoClientMap().get(player.getPlayerID()).send(answer);
            }
        }
    }

    /**
     * Setups a player into the game
     *
     * @param nickname String
     * @param iDclient Integer
     */
    public void setupPlayer(String nickname, Integer iDclient) {
        board.createNewPlayer(new Player(nickname,iDclient));
    }

    /**
     * Setups a new Schoolboard for the player into the board
     *
     * @param player Player
     */
    public void setupPlayerSchoolBoard(Player player){
        player.createSchoolBoard(isExpertMode,numberOfPlayers);
        try {
            board.getBoardManager().getBag().setupSchoolEntrance(player.getSchoolBoard());
        } catch (GameOverException e) {
            sendAll(new GameOver("The bag is empty so the game is ending..."));
        }
    }


    /**
     * closes the connection with a String and sends win message to the winner
     * @param s
     */
    public void endGame(String s) {
        Client winner = checkWinner();
        sendtoPlayer(new WinMessage(), winner.getIdClient());
        sendAllExcept(new GameOver(s),winner.getIdClient());

    }

    /**
     * closes the connection with a String and sends win message to the winner, identified
     *
     * @param s String
     * @param id int
     */
    public void endGame(String s, int id) {
        Client winner = server.getIdtoClientMap().get(id);
        sendtoPlayer(new WinMessage(), winner.getIdClient());
        sendAllExcept(new GameOver(s),winner.getIdClient());

    }


    /**
     * method checks if there is a winner
     *
     * @return Client
     */
    public Client checkWinner() {
        Player winner = board.getActivePlayers().get(0);
        for (Player player : board.getActivePlayers()){
            if(player.getSchoolBoard().getTowers() < winner.getSchoolBoard().getTowers())
                winner=player;
        }
        return server.getIdtoClientMap().get(winner.getPlayerID());
    }

    /**
     * signals if the game is started or not
     *
     * @return booleans
     */
    public boolean isStarted() {
        return isStarted;
    }

    /**
     * used at the start to setup the first round of the game
     */
    public synchronized void setup() {
        if (!isStarted()) {
            startGame();
        }
        SetDetails request = new SetDetails(numberOfPlayers, "Please choose your Wizard and your Tower.");
        request.addRemaining(Wizard.getAvailable(), Tower.available());
        if (numberOfPlayers == 2 && Tower.available().size() > 1) {
            String player = board.getActivePlayers().get(numberOfPlayers - Tower.available().size() + 1).getNickname();
            sendtoPlayer(request, server.getNametoIdMap().get(player));
            sendAllExcept(new CustomMessage(player + " is making his choice",false), server.getNametoIdMap().get(player));
            return;
        } else if (numberOfPlayers == 3 && !Tower.available().isEmpty()) {
            String nickname = board.getActivePlayers().get(numberOfPlayers - Tower.available().size()).getNickname();
            if (Tower.available().size() == 1) {
                board.getPlayerFromGivenNickname(nickname).setTower(Tower.available().get(0));
                board.getPlayerFromGivenNickname(nickname).setWizard(Wizard.getAvailable().get(0));
                Tower.choose(Tower.available().get(0));
                Wizard.choose(Wizard.getAvailable().get(0));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    logger.log(Level.SEVERE, e.getMessage(), e);
                    Thread.currentThread().interrupt();
                }
            } else {
                server.getIdtoClientMap().get(server.getNametoIdMap().get(nickname)).send(request);
                sendAllExcept(new CustomMessage("User " + nickname + " is choosing his tower and Wizard",false),server.getNametoIdMap().get(nickname));
                return;
            }
        }
        System.out.println("Entrambi hanno scelto");
        int random_index = rnd.nextInt(1,numberOfPlayers + 1) - 1;
        board.setCurrentPlayer(board.getActivePlayers().get(random_index));
        System.out.println("Inizia a scegliere" + board.getCurrentPlayer().getNickname());
        phase = 2;
        for(Player player : board.getActivePlayers()){
            sendAll(new MatchStarted(board.getBoardManager().getClouds(),board.getBoardManager().getIslands(),player.getSchoolBoard().getEntrance(), player.getNickname()));
        }


        System.out.println("Sto dicendo di iniziare il turno a " + board.getCurrentPlayer().getPlayerID());
        sendtoPlayer(new StartTurnMessage(true),board.getCurrentPlayerIndex());
        sendAllExcept(new StartTurnMessage(), board.getCurrentPlayerIndex());
        sendAll(new CustomMessage("The first round is starting, brace yourself...", false));
        sendtoPlayer( new ChooseAssistantCard("You have been picked to start the turn, choose an Assistant Card for the first round\n>",
                        board.getCurrentPlayer().getNickname(),
                        board.getCurrentPlayer().getAssistantCards()),
                board.getCurrentPlayerIndex());
        sendAllExcept(new CustomMessage(board.getCurrentPlayer().getNickname() + " is choosing his assistant card...please wait",false), board.getCurrentPlayerIndex());

    }



    public void makeAction(UserCommand command, String type) {
        logger.log(Level.INFO, "Stai facendo questa azione dal make action: " + type);
        String commandtype = command.toString();
        switch (type){
            case "RoundDecider" -> startRoundPhase(command);
            case "turnController" -> controllerListener.firePropertyChange(commandtype,null,command);
            default -> sendtoPlayer(new GameError(Errors.INVALIDMOVE, "The command you gave is invalid"),
                    board.getCurrentPlayerIndex());
        }
    }

    private void startRoundPhase(UserCommand command) {
        synchronized (this){
            controllerListener.firePropertyChange("ChoiceAssistantCard",null,((ChoiceAssistantCard) command).getCard());
        }
        if(numberOfPlayers == 2){
            phase = 3;
        }
        System.out.println("La Map ora è di " + board.getPlayerAssistantCardHashMap().size() + ", e la fase è " + phase);
            if(board.getPlayerAssistantCardHashMap().size() < numberOfPlayers){
                chooseAssistantCard();
            } else if (board.getPlayerAssistantCardHashMap().size() == numberOfPlayers-1) {
                chooseAssistantCard();
                phase = 3;
            }

    }


}