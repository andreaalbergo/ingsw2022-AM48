package it.polimi.ingsw.server;

import it.polimi.ingsw.Board;

public class BoardHandler {
    //private final Server server;
    //private final Controller controller;
    private final Board board;
    private boolean gameStarted;
    private int numberOfPlayers;

    public BoardHandler(/*Server server*/) {
        //this.server = server;
        gameStarted = false;
        board = new Board();

    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
}
