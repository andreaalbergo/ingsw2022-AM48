package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Board class contains the main logic of "Eriantys", which is divided into some areas. The first one, the most
 * important one, is the player who has a SchoolBoard, a set of 10 different assistant cards (and a counter of coins if
 * game mode is set to "expert"). Second one is the BoardManager, which contains information about the status of the
 * islands, bag, clouds, mother nature, round of the game and current player's round.
 *
 * @author David Barb
 */
public class Board {
    private final List<Player> activePlayers = new ArrayList<>();
    private final List<Player> playersTurnOrder = new ArrayList<>();
    private Player currentPlayer;
    private int currentPlayerIndex;

    private boolean startedRound;

    private HashMap <Player, AssistantCard> playerAssistantCardHashMap;

    private final BoardManager boardManager;

    /**
     * Constructor Board creates a new game instance.
     */
    public Board() {
        boardManager = new BoardManager(this);
        playerAssistantCardHashMap = new HashMap<>();
    }


    public boolean isStartedRound() {
        return startedRound;
    }

    public void setStartedRound(boolean startedRound) {
        this.startedRound = startedRound;
    }

    public void setPlayerAssistantCardHashMap(AssistantCard assistantCard) {
        playerAssistantCardHashMap.put(currentPlayer, assistantCard);
        currentPlayer.setChosenCard(assistantCard);
        currentPlayer.getAssistantCards().remove(assistantCard);
    }

    public void resetAssistantCards() {
        playerAssistantCardHashMap.clear();
    }

    public void resetMovedStudents(){
        for(Player player : activePlayers){
            player.getSchoolBoard().setMovedstudents(0);
        }
    }

    public HashMap<Player, AssistantCard> getPlayerAssistantCardHashMap() {
        return playerAssistantCardHashMap;
    }

    /**
     * Method createNewPlayer adds a player to activePlayers list. This list MUST BE minimum size of 2 and maximum size
     * of 3.
     *
     * @param player of type Player.
     */
    public void createNewPlayer(Player player) {
        activePlayers.add(player);
    }

    /**
     * Method getCurrentPlayer gives us the player that is having its turn during the game.
     *
     * @return of type Player - reference of current player.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Method getPlayerFromGivenNickname searches for the player with same nickname.
     *
     * @param nickname of type String.
     * @return of type Player - player with same nickname.
     */
    public Player getPlayerFromGivenNickname(String nickname) {
        for(Player player : activePlayers)
            if(player.getNickname().equalsIgnoreCase(nickname))
                return player;
        return null;
    }

    /**
     * Method getPlayerFromGivenID tries to find the player with corresponding ID.
     *
     * @param id of type int.
     * @return of type Player - player with same id param.
     */
    public Player getPlayerFromGivenID(int id) {
        for(Player player : activePlayers)
            if(player.getPlayerID()==id)
                return player;
        return null;
    }

    /**
     * Method getBoardManager that gives us the board manager.
     *
     * @return of type BoardManager.
     */
    public BoardManager getBoardManager() {
        return boardManager;
    }

    /**
     * Method getActivePlayers gives us back the list of players active in the game.
     *
     * @return of type List - activePlayers.
     */
    public List<Player> getActivePlayers() {
        return activePlayers;
    }

    /**
     * getPlayersTurnOrder
     *
     * @return of type List<> - the order of players' turn.
     */
    public List<Player> getPlayersTurnOrder() {
        return playersTurnOrder;
    }

    /**
     * Method that sets the new current player that is determined by the list playersTurnOrder.
     *
     * @param currentPlayer of type Player.
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        currentPlayerIndex = currentPlayer.getPlayerID();
    }

    /**
     * Method pickFirstPlayerToStart that chooses a player randomly from the list in order to be the first one to begin
     * the game by drawing an assistant card. Then proceed to the next player in order clockwise, as the game rules say.
     */
    public void pickFirstPlayerToStart() {
        Random random = new Random();
        currentPlayerIndex = random.nextInt(activePlayers.size());
        currentPlayer = activePlayers.get(currentPlayerIndex);
        //need a method to take next player clockwise for the first time picking an assistant card
    }

    /**
     * Method setPlayerOrderTurn that AFTER ALL THE PLAYERS drew one assistant card, we sort them in ascending order by
     * their assistant card value. Then it can be possible to start the turn by setting first player in activePLayers
     * list as currentPLayer.
     */
    public void setPlayerOrderTurn() {
        int maxValue = activePlayers.get(0).getChosenCard().getValue();
        int minValue = maxValue;

        for (Player player : activePlayers) {
            if (player.getChosenCard().getValue() >= maxValue) {
                playersTurnOrder.add(player);
                maxValue = player.getChosenCard().getValue();
            } else if(player.getChosenCard().getValue() < minValue) {
                playersTurnOrder.add(0, player);
                minValue = player.getChosenCard().getValue();
            } else {
                playersTurnOrder.add(1, player);
            }
        }
        setCurrentPlayer(getPlayersTurnOrder().get(0));
    }

    /**
     * Method setNextPlayer that changes current player's pointer in playersTurnOrder list to the next one in the list;
     * if the pointer has reached the last element, a new turn is starting.
     */
    public void setNextPlayer() {
        System.out.println("The current player was "+ currentPlayer.getNickname() + ", with ID: "+currentPlayer.getPlayerID());
        if(playersTurnOrder.isEmpty()){
            currentPlayerIndex = currentPlayer.getPlayerID();
            if(activePlayers.size() == 2){
                if(currentPlayerIndex == 1){
                    currentPlayerIndex = 0;
                    setCurrentPlayer(activePlayers.get(currentPlayerIndex));
                } else if (currentPlayerIndex == 0) {
                    currentPlayerIndex = 1;
                    setCurrentPlayer(activePlayers.get(currentPlayerIndex));
                }
            }
            if(activePlayers.size() == 3){
                if(currentPlayerIndex == 2){
                    currentPlayerIndex = 0;
                    setCurrentPlayer(activePlayers.get(currentPlayerIndex));
                }else{
                    currentPlayerIndex++;
                    setCurrentPlayer(activePlayers.get(currentPlayerIndex));
                }
            }
            System.out.println("The current player now is "+ currentPlayer.getNickname() + ", with ID: "+currentPlayer.getPlayerID());
        }
        else if (currentPlayerIndex == playersTurnOrder.size()-1) {
            currentPlayerIndex = 0;
            setCurrentPlayer(activePlayers.get(currentPlayerIndex));
            System.out.println("The current player now is "+ currentPlayer.getNickname() + ", with ID: "+currentPlayer.getPlayerID());
        } else {
            currentPlayerIndex++;
            setCurrentPlayer(activePlayers.get(currentPlayerIndex));
            System.out.println("The current player now is "+ currentPlayer.getNickname() + ", with ID: "+currentPlayer.getPlayerID());
        }
    }

    public int getCurrentPlayerIndex(){
        return currentPlayer.getPlayerID();
    }
}

