package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Board class contains the main logic of "Eryantis", which is divided into some areas. The first one, the most
 * important one, is the player who has a SchoolBoard, a set of 10 different assistant cards (and a counter of coins if
 * game mode is set to "expert"). Second one is the BoardManager, which contains information about the status of the
 * islands, bag, clouds, mother nature, round of the game and current player's round.
 *
 * @author David Barb
 */
public class Board {
    private final List<Player> activePLayers = new ArrayList<>();
    private final List<Player> playersTurnOrder = new ArrayList<>();
    private Player currentPlayer;
    private int currentPlayerIndex;
    private int gameTurn;
    private final NormalBoardManager boardManager;

    /**
     * Constructor Board creates a new game instance.
     */
    public Board() {
        gameTurn = 1;
        boardManager = new NormalBoardManager();
    }

    public void createNewPlayer(Player player) {
        activePLayers.add(player);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getPlayerFromGivenNickname(String nickname) {
        for(Player player : activePLayers)
            if(player.getNickname().equalsIgnoreCase(nickname))
                return player;
        return null;
    }

    public Player getPlayerFromGivenID(int id) {
        for(Player player : activePLayers)
            if(player.getPlayerID()==id)
                return player;
        return null;
    }

    public int getGameTurn() {
        return gameTurn;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        currentPlayerIndex = playersTurnOrder.indexOf(currentPlayer);
    }

    /**
     * Method pickFirstPlayerToStart that chooses a player randomly from the list in order to be the first one to begin
     * the game by drawing an assistant card. Then proceed to the next player in order clockwise, as the game rules say.
     */
    public void pickFirstPlayerToStart() {
        Random random = new Random();
        currentPlayerIndex = random.nextInt(activePLayers.size());
        currentPlayer = activePLayers.get(currentPlayerIndex);
    }

    /**
     * Method setPlayerOrderTurn that AFTER ALL THE PLAYERS drew one assistant card, we sort them in ascending order by
     * their assistant card value. Then it can be possible to start the turn by setting first player in activePLayers
     * list as currentPLayer.
     */
    public void setPlayerOrderTurn() {
        int maxValue = activePLayers.get(0).getChosenCard();
        int minValue = maxValue;

        for (Player player : activePLayers) {
            if (player.getChosenCard() >= maxValue) {
                playersTurnOrder.add(player);
                maxValue = player.getChosenCard();
            } else if(player.getChosenCard() < minValue) {
                playersTurnOrder.add(0, player);
                minValue = player.getChosenCard();
            } else {
                playersTurnOrder.add(1, player);
            }
        }
    }

    /**
     * Method setNextPlayer that changes current player's pointer in playersTurnOrder list to the next one in the list;
     * if the pointer has reached the last element, a new turn is starting.
     */
    public void setNextPlayer() {
        if (currentPlayerIndex == playersTurnOrder.size()-1) {
            currentPlayerIndex = 0;
            setNextTurn();
        } else {
            currentPlayerIndex++;
            setCurrentPlayer(playersTurnOrder.get(currentPlayerIndex));
        }
    }

    public void setNextTurn() {
        if(gameTurn!=10)
            gameTurn++;
        //else: call gameOver; it is the job of the controller I think.
    }
}

