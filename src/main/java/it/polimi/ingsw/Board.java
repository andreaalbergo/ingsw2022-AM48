package it.polimi.ingsw;

public class Board {
    private final int gameId;
    private static int numberOfPlayers;
    private static boolean isExpertMode;
    private final BoardManager boardManager;

    public Board(int numberOfPlayers, boolean isExpertMode) {
        this.gameId = 1; //for now one Game in the Server
        Board.numberOfPlayers = numberOfPlayers;
        Board.isExpertMode = isExpertMode;

        if(!isExpertMode)
            this.boardManager = new SimpleBoardManager(this);
        else {
            this.boardManager = new ExpertBoardManager(new SimpleBoardManager(this));
        }

    }

    public int getGameId() {
        return gameId;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public boolean isExpertMode() {
        return isExpertMode;
    }

    public BoardManager getBoardManager() {
        return boardManager;
    }
}
