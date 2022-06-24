package it.polimi.ingsw.controller;

/**
 * RoundHandler is a class that handles the "Planning Phase" of every Eriantys round, so it handles the chosen assistant
 * cards to order the players' turns for the "Action Phase".
 */
public class RoundHandler {
    private final GameController mainController;
    private final TurnHandler turn;
    private int gameRound = 1;

    public RoundHandler(GameController mainController, TurnHandler turn) {
        this.mainController = mainController;
        this.turn = turn;
    }

    /**
     * Method getGameTurn give us back this game's turn.
     *
     * @return of type int - turn's game.
     */
    public int getGameRound() {
        return gameRound;
    }

    /**
     * Method iterateTurn increases the game's round by one. If current turn is 10, it means it's the last and after that
     * the model notifies the controller about reaching game over.
     */
    public void iterateRound() {
        if(gameRound!=10)
            gameRound++;
        else
            mainController.gameOver();
    }
}
