package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.server.messages.Answer;
import it.polimi.ingsw.server.messages.CustomMessage;

public class BoardHandler {
    //private final Server server;
    private final Controller controller;
    private final Board board;
    private boolean gameStarted;
    private int numberOfPlayers;
    private int currentPlayer;
    private boolean isStarted;

    public Controller getController() {
        return controller;
    }

    public BoardHandler(/*Server server*/) {
        //this.server = server;
        gameStarted = false;
        board = new Board();
        controller = new Controller();// gli dovremo passare questo boardhandler e l'istanza di Board che creiamo;

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
