package it.polimi.ingsw;

public class Board {
    //private final int gameId;
    private int numberOfPlayers;
    private boolean expertMode;
    private BoardManager boardManager;

    public Board(int numberOfPlayers, boolean isExpertMode) {
        //this.gameId = 1; //for now one Game in the Server
        this.numberOfPlayers = numberOfPlayers;
        this.expertMode = isExpertMode;
/*
        if (!isExpertMode)
            this.boardManager = new SimpleBoardManager(numberOfPlayers,expertMode);
        else {
            this.boardManager = new ExpertBoardManager(new SimpleBoardManager(numberOfPlayers,expertMode));
        }

 */

    }

    /*public int getGameId() {
        return gameId;
    }

     */

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public boolean isExpertMode() {
        return expertMode;

    }

    public BoardManager getBoardManager () {
        return null;
    }

}

