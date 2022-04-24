package it.polimi.ingsw;

public class Board {
    private final int gameId;
    private static int numberOfPlayers;
    private static boolean isExpertMode;
    private final BoardManager boardManager;
    //characterCards here?


    public Board(int numberOfPlayers, boolean isExpertMode) {
        this.gameId = 1; //for now One Game in the Server
        Board.numberOfPlayers = numberOfPlayers;
        Board.isExpertMode = isExpertMode;
        this.boardManager = new BoardManager();
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
