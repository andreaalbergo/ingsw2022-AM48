package it.polimi.ingsw.server;

import it.polimi.ingsw.client.actions.ChoiceAssistantCard;
import it.polimi.ingsw.client.actions.UserCommand;
import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.exceptions.GameOverException;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.server.servermessages.*;
import it.polimi.ingsw.server.servermessages.gamemessages.GameOver;
import it.polimi.ingsw.server.servermessages.gamemessages.StartTurnMessage;
import it.polimi.ingsw.server.servermessages.gamemessages.WinMessage;

import java.beans.PropertyChangeSupport;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BoardHandler {
    private final MultiplayerServer server;
    private final GameController controller;

    public Board game() {
        return board;
    }

    private final Board board;
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
        }
        //le isole si settano con la creazione del board
        board.getBoardManager().createCloudList();
        setClouds();
    }

    public void setClouds() {
        for (Cloud cloud : board.getBoardManager().getClouds()){
            try {
                cloud.fillStudents();
            } catch (GameOverException e) {
                sendAll(new GameOver("The bag is empty so the game is ending..."));
            }
        }
    }



    public void setExpertMode(boolean expertMode) {
        isExpertMode = expertMode;
    }

    public boolean isExpertMode() {
        return isExpertMode;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public void unregisterPlayer(Integer idClient) {
       Player player = board.getActivePlayers().get(idClient);
    }

    private void chooseAssistantCard(){
        Player player = board.getCurrentPlayer();
        ChooseAssistantCard request = new ChooseAssistantCard(player.getNickname() + " please choose your assistant card from one of the list below:\n\n", player.getNickname(), player.getAssistantCards());
        sendtoPlayer(request,player.getPlayerID());
    }


    public void sendtoPlayer(Answer answer, Integer id) {
        server.getIdtoClientMap().get(id).send(answer);
    }

    public void sendAll(CustomMessage customMessage) {
        for(Player player: board.getActivePlayers())
            server.getIdtoClientMap().get(player.getPlayerID()).send(customMessage);
    }
    public void sendAll(Answer message) {
        for(Player player: board.getActivePlayers())
            server.getIdtoClientMap().get(player.getPlayerID()).send(message);
    }


    //STO METODO FUNZIONA MA STAMPA DUE VOLTE...
    public void sendAllExcept(Answer answer, int idClient) {
        for (Player player: board.getActivePlayers()) {
            if(server.getNametoIdMap().get(player.getNickname()) != idClient){
                server.getIdtoClientMap().get(player.getPlayerID()).send(answer);
            }
        }
    }


    public void setupPlayer(String nickname, Integer iDclient) {
        board.createNewPlayer(new Player(nickname,iDclient));
    }

    public void setupPlayerSchoolBoard(Player player){
        player.createSchoolBoard(isExpertMode,numberOfPlayers);
        try {
            board.getBoardManager().getBag().setupSchoolEntrance(player.getSchoolBoard());
        } catch (GameOverException e) {
            sendAll(new GameOver("The bag is empty so the game is ending..."));
        }
    }


    public void endGame(String s) {
        Client winner = checkWinner();
        sendtoPlayer(new WinMessage(), winner.getIdClient());
        sendAllExcept(new GameOver(s),winner.getIdClient());
    }

    public void endGame(String s, int id) {
        Client winner = server.getIdtoClientMap().get(id);
        sendtoPlayer(new WinMessage(), winner.getIdClient());
        sendAllExcept(new GameOver(s),winner.getIdClient());

    }

    public Client checkWinner() {
        Player winner = board.getActivePlayers().get(0);
        for (Player player : board.getActivePlayers()){
            if(player.getSchoolBoard().getTowers() < winner.getSchoolBoard().getTowers())
                winner=player;
        }
        return server.getIdtoClientMap().get(winner.getPlayerID());
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setup() {
        if (!isStarted()) {
            startGame();
        }
        logger.log(Level.INFO,"Sei nel setup");
        SetDatails request = new SetDatails("Please choose your Wizard and your Tower.");
        request.addRemaining(Wizard.getAvailable(), Tower.available());
        if (numberOfPlayers == 2 && Tower.available().size() > 1) {
            //logger.log(Level.INFO,"Sei nel setup con 2 giocatori");
            String player = board.getActivePlayers().get(numberOfPlayers - Tower.available().size() + 1).getNickname();
            logger.log(Level.INFO,"Devo mandare messaggio a " + player);
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
        board.setCurrentPlayer(board.getActivePlayers().get(rnd.nextInt(numberOfPlayers)));
        logger.log(Level.INFO,"Inizia a scegliere " + board.getCurrentPlayer());
        phase = 2;

        for(Player player : board.getActivePlayers()){
            sendAll(new MatchStarted(board.getBoardManager().getClouds(),board.getBoardManager().getIslands(),player.getSchoolBoard().getEntrance(), player.getNickname()));
        }

        sendtoPlayer(new StartTurnMessage(true),board.getCurrentPlayerIndex());
        sendAllExcept(new StartTurnMessage(), board.getCurrentPlayerIndex());
        sendAll(new CustomMessage("The first round is starting, brace yourself...", false));
        sendtoPlayer( new ChooseAssistantCard("You have been picked to start the turn, choose an Assistant Card for the first round\n>",
                        board.getCurrentPlayer().getNickname(),
                        board.getCurrentPlayer().getAssistantCards()),
                board.getCurrentPlayerIndex());
        sendAllExcept(new CustomMessage(board.getCurrentPlayer().getNickname() + " is choosing his assistant card...please wait",false), board.getCurrentPlayerIndex());
        //game().setNextPlayer();

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
            controllerListener.firePropertyChange("ChooseAssistantCard",null,((ChoiceAssistantCard) command).getCard());
        }
            if(board.getPlayerAssistantCardHashMap().size() < numberOfPlayers){
                chooseAssistantCard();
                //sendAllExcept(new CustomMessage(board.getCurrentPlayer().getNickname() + " is choosing his card..."), board.getCurrentPlayerIndex());
                //game().setNextPlayer();
            } else if (board.getPlayerAssistantCardHashMap().size() == numberOfPlayers-1) {
                chooseAssistantCard();
                //sendAllExcept(new CustomMessage(board.getCurrentPlayer().getNickname() + " is choosing his card..."),board.getCurrentPlayerIndex());
                phase = 3;
                //setClouds();
            }

    }



}