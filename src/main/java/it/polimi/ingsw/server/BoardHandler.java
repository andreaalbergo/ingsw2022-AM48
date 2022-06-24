package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Tower;
import it.polimi.ingsw.model.Wizard;
import it.polimi.ingsw.server.servermessages.*;
import java.beans.PropertyChangeSupport;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BoardHandler {
    private final MultiplayerServer server;
    private final GameController controller;
    private final Board board;
    private int numberOfPlayers;

    private final Random rnd = new Random();


    private final PropertyChangeSupport controllerListener = new PropertyChangeSupport(this);

    private boolean isExpertMode;
    //private int currentPlayer;
    private boolean isStarted;
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
        //TODO
        //call end game
    }

    /*
    //FUNZIONA MA MI FA SCEGLIERE DUE VOLTE MA SOLO ALL'HOST E NON ALL'ALTRO PLAYER
    public void setupWizard() {
        if(!isStarted)
            startGame();
        RequestWizard wizardReq = new RequestWizard("Please choose a wizard: ");
        wizardReq.updateRemaining(Wizard.getAvailable());
        if(numberOfPlayers==2 && Wizard.getAvailable().size()>2){
            String player = board.getPlayerFromGivenID(numberOfPlayers - Wizard.getAvailable().size() + 2).getNickname();
            sendAllExcept(new CustomMessage("User "+ player +" is choosing his wizard"),server.getNametoIdMap().get(player));
            sendtoPlayer(wizardReq, server.getNametoIdMap().get(player));
        } else if (numberOfPlayers==3 && Wizard.getAvailable().size()>1) {
            String player = board.getPlayerFromGivenID(numberOfPlayers - Tower.available.size() + 1).getNickname();
            sendtoPlayer(wizardReq,server.getNametoIdMap().get(player));
            sendAllExcept(new CustomMessage(player + "is choosing his wizard"),server.getNametoIdMap().get(player));
        } else if(Wizard.getAvailable().size() == 4 - numberOfPlayers){
        }
    }

    /*
    public boolean setupTower() {

        if(!isStarted)
            startGame();

        TowerRequest request = new TowerRequest("Please choose a tower: ");
        request.setRemaining_towers(Tower.available);
        if(Tower.available.size()>1) {
            if(numberOfPlayers == 2){
                String player = board.getPlayerFromGivenID(numberOfPlayers - Tower.available.size() + 1).getNickname();
                sendtoPlayer(request, server.getNametoIdMap().get(player));
                sendAllExcept(new CustomMessage(player + "is choosing his Tower"), server.getNametoIdMap().get(player));
                return true;
            } else if (numberOfPlayers == 3) {
                String player = board.getPlayerFromGivenID(numberOfPlayers - Tower.available.size()).getNickname();
                sendtoPlayer(request, server.getNametoIdMap().get(player));
                sendAllExcept(new CustomMessage(player + "is choosing his Tower"), server.getNametoIdMap().get(player));
                return true;
            }else if(Wizard.getAvailable().size() == 4 - numberOfPlayers){
                return false;
            }


        }

        return false;
    }
*/
    private void chooseAssistantCard(){
        Player player = board.getCurrentPlayer();
        ChooseAssistantCard request = new ChooseAssistantCard("Please choose of these cards in your Deck: \n", player.getNickname(), board.getCurrentPlayer().getAssistantCards());
        sendtoPlayer(request,player.getPlayerID());
    }


    public void sendtoPlayer(Answer answer, Integer id) {
        server.getIdtoClientMap().get(id).send(answer);
    }

    public void sendAll(CustomMessage customMessage) {
        for(Player player: board.getActivePlayers())
            server.getIdtoClientMap().get(player.getPlayerID()).send(customMessage);
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

    public void endGame(String s) {
        //TODO
    }

    public void endGame(Integer player){
        //TODO
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
            logger.log(Level.INFO,"Sei nel setup con 2 giocatori");
            String player = board.getActivePlayers().get(numberOfPlayers - Tower.available().size() + 1).getNickname();
            logger.log(Level.INFO,"Devo mandare messaggio a " + player);
            sendtoPlayer(request, server.getNametoIdMap().get(player));
            sendAllExcept(new CustomMessage(player + "is making his choice"), server.getNametoIdMap().get(player));
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
                sendAllExcept(new CustomMessage("User " + nickname + " is choosing his tower and Wizard"),server.getNametoIdMap().get(nickname));
                return;
            }
        }
        board.setCurrentPlayer(board.getActivePlayers().get(rnd.nextInt(numberOfPlayers)));
        logger.log(Level.INFO,"Inizia a scegliere " + board.getCurrentPlayer());
    }





}