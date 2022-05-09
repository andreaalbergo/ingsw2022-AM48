package it.polimi.ingsw;

/**
 * Board class contains the main logic of "Eryantis", which is divided into some areas. The first one, the most
 * important one, is the player who has a SchoolBoard, a set of 10 different assistant cards (and a counter of coins if
 * game mode is set to "expert"). Second one is the BoardManager, which contains information about the status of the
 * islands, bag, clouds, mother nature, round of the game and current player's turn. Last area (TBD) should be the MVC
 * pattern creation that lets the current player choose its available moves.
 *
 * @author David Barb
 */
public class Board {
    private int numberOfPlayers;
    private BoardManager boardManager;

    /**
     * Constructor of public class Board and it creates a new game instance. DEFAULT value of number of
     * players is 2 and game mode "normal" (new simple board manager).
     */
    public Board() {
        this.numberOfPlayers = 2;
        /*
        this.boardManager = new SimpleBoardManager(this.numberOfPlayers);*/
    }

    /**
     * Method getNumberOfPlayers gets the number of players active in the game.
     *
     * @return int - the number of players playing the game.
     */
    public int getNumberOfPlayers() { return this.numberOfPlayers; }

    /**
     * Method getBoardManager gets boardManager of current game.
     *
     * @return BoardManager - boardManager of active game.
     */
    public BoardManager getBoardManager() { return this.boardManager; }

    /**
     * Method setNumberOfPlayers that sets the number of players playing the game; int value MUST BE between 2 and 4.
     * This method updates also number of players in class SimpleBoardManager or its decorator.
     *
     * @param numberOfPlayers of type int - number of players playing the game.
     */
    public void setNumberOfPlayers(int numberOfPlayers) {
        if (numberOfPlayers<2 || numberOfPlayers>4)
            throw new RuntimeException("Number of players MUST BE between 2 or 4 to start a game");
        else {
            this.numberOfPlayers = numberOfPlayers;
            //this.boardManager.setNumberOfPlayers(numberOfPlayers);
        }
    }

    /**
     * Method setExpertMode that changes game mode from "normal" to "expert" mode. So it decorates
     * the simpleBoardManager.
     */
    public void setExpertMode() {
        this.boardManager = new ExpertBoardManager(new SimpleBoardManager(numberOfPlayers));
    }
}

