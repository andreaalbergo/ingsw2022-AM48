package it.polimi.ingsw.controller;

/**
 * RoundHandler is a class that handles the "Planning Phase" of every Eryantis round, so it handles the chosen assistant
 * cards to order the players' turns for the "Action Phase".
 */
public class RoundHandler {
    private final GameController mainController;
    private final TurnHandler turn;
    private int gameTurn = 1;

    public RoundHandler(GameController mainController, boolean isExpertMode) {
        this.mainController = mainController;
        turn = new TurnHandler(isExpertMode);
    }

    /**
     * Method iterateTurn increases the game's turn by one. If current turn is 10, it means it's the last and after that
     * the model notifies the controller about reaching game over.
     */
    public void iterateTurn() {
        if(gameTurn!=10)
            gameTurn++;
        else
            mainController.gameOver();
    }
}
