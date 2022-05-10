package it.polimi.ingsw;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Player {
    private final String nickname;
    private final int playerID;
    private int idPlayerForTurn;
    private int chosenCard;
    private final List<AssistantCard> assistantCardList;
    private int coins;
    private boolean turn;
    private final Wizard chosenWizard;
    private final TowersColor towersColor;
    private SchoolBoard schoolBoard;


    public int getPlayerID() {
        return playerID;
    }

    public Player(String nickname, int playerID, Wizard chosenWizard, TowersColor towersColor) {
        this.nickname = nickname;
        this.assistantCardList = new LinkedList<>();
        Collections.addAll(assistantCardList, AssistantCard.values());
        this.coins = 1;
        if (playerID == 1)
            turn = true;
        else
            turn = false;
        this.chosenWizard = chosenWizard;
        this.towersColor = towersColor;
        this.playerID = playerID;

    }

    /**
     * This method creates an Istance of SchoolBoard linked to Player that owns it
     *
     * @param mode specifies the rules the current game is following
     * @param numberofPlayers specifies the number of players for this game
     */
    public void createSchoolBoard(boolean mode, int numberofPlayers){
        this.schoolBoard = new SchoolBoard(nickname, numberofPlayers, mode);
    }


    /**
     * Returns a string containing the nickname of the player
     * @return a string with the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Return the List of the assistant cards the player has in his deck
     * @return List of Assistant Cards
     */
    public List<AssistantCard> getAssistantCards() {
        return assistantCardList;
    }

    /**
     * Returns the number of coins the player has at the moment
     * @return an integer
     */
    public int getCoins() {
        return coins;
    }


    /**
     * Returns a boolean that signals if the player is the actual one that has to play now
     * @return a boolean taken from the variable turn
     */
    public boolean isTurn() {
        return turn;
    }

    /**
     * This method is used to set the Turn variable to 1 or 0 when the turn changes
     * @param turn     specification (1 your turn, 0 ended your turn)
     */
    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    /**
     * This Method is used to know the Wizard chosen by the player in question
     * @return String containing the Type of Wizard chosen by the Player
     */
    public String getChosenWizard() {
        return chosenWizard.toString();
    }

    /**
     * This methods sets the order number to which the player will be considered active for the turn
     * @param idPlayerForTurn is a number from 1 to the number of players playing the game
     */
    public void setIdPlayerForTurn(int idPlayerForTurn) {
        this.idPlayerForTurn = idPlayerForTurn;
    }

    /**
     * This method updates the number of coins of the player
     * @param newCoins number of coins player has to collect after moving students to the dining room
     */
    public void addCoins(int newCoins) {
        this.coins += newCoins;
    }

    /**
     * After the player intends to use his coins the method in question subtract the spent amount of coins
     * @param coins the player is spending "coins" amount for some action
     */
    public void removeCoin(int coins) {
        this.coins -= coins;
    }

    /**
     * This method return the order in which this player will play
     * @return an integer from 1 to the max number of players
     */
    public int getIdPlayerForTurn() {
        return idPlayerForTurn;
    }

    /**
     * This method is used to get hold of the Player's Schoolboard
     * @return the SchoolBoard reference for this player
     */
    public SchoolBoard getSchoolBoard() {
        return schoolBoard;
    }


}
