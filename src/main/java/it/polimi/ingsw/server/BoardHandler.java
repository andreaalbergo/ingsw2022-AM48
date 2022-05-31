package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.server.messages.Answer;
import it.polimi.ingsw.server.messages.CustomMessage;

public class BoardHandler {
    private final MultiplayerServer server;
    private final GameController controller;
    private final Board board;
    private int numberOfPlayers;
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

    public void addPlayer(){}

    public void unregisterPlayer(Integer idClient) {
    }

    public void setup() {
    }

    public void sendAll(CustomMessage customMessage) {
    }

    public void sendAllExcept(Answer answer, Integer idClient) {

    }


    public void setupPlayer(String nickname, Integer iDclient) {
    }

    public void endGame(String s) {
    }

    public void endGame(Integer player){

    }

    public boolean isStarted() {
        return isStarted;
    }
}
