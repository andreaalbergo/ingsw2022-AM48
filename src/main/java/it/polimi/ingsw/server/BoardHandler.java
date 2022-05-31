package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Tower;
import it.polimi.ingsw.model.Wizard;
import it.polimi.ingsw.server.messages.Answer;
import it.polimi.ingsw.server.messages.CustomMessage;

import java.beans.PropertyChangeSupport;

public class BoardHandler {
    private final MultiplayerServer server;
    private final GameController controller;
    private final Board board;
    private int numberOfPlayers;

    private final PropertyChangeSupport controllerListener = new PropertyChangeSupport(this);

    private boolean isExpertMode;
    //private int currentPlayer;
    private boolean isStarted;

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

    public void addPlayer(String nickname, int clientID){}

    public void unregisterPlayer(Integer idClient) {
    }

    public void setup() {
    }

    public void sendAll(CustomMessage customMessage) {
    }

    public void sendAllExcept(Answer answer, Integer idClient) {

    }


    public void setupPlayer(String nickname, Integer iDclient) {
        board.createNewPlayer(new Player(nickname,iDclient, Wizard.WIZARD1, Tower.BLACK));//Per ora ho messo wizard e torri gia pre impostate mo cambio

    }

    public void endGame(String s) {
    }

    public void endGame(Integer player){

    }

    public boolean isStarted() {
        return isStarted;
    }
}
