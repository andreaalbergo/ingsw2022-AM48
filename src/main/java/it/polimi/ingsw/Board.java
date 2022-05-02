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
            this.boardManager = new SimpleBoardManager();
        else
            this.boardManager = new ExpertBoardManager(new SimpleBoardManager());
    }

    public int getGameId() {
        return gameId;
    }

    public static int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public static boolean isExpertMode() {
        return isExpertMode;
    }
}
