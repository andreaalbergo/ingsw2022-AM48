package it.polimi.ingsw.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Player class is an abstract representation of the player in model structure.
 */
public class Player implements Serializable {
    private final String nickname;
    private final int playerID;
    private AssistantCard chosenCard;
    private final List<AssistantCard> assistantCardList;
    private int coins;
    private boolean turn;
    private Wizard chosenWizard;
    private Tower tower;
    private SchoolBoard schoolBoard;

    /**
     * Constructor Player that creates its instance.
     *
     * @param nickname of type String.
     * @param playerID of type int.
     * @param chosenWizard of type Wizard.
     * @param tower of type Tower.
     */
    public Player(String nickname, int playerID, Wizard chosenWizard, Tower tower) {
        this.nickname = nickname;
        this.assistantCardList = new LinkedList<>();
        Collections.addAll(assistantCardList, AssistantCard.values());
        this.coins = 1;
        turn = playerID == 1;
        this.chosenWizard = chosenWizard;
        this.tower = tower;
        this.playerID = playerID;

    }

    /**
     * Constructor Player that creates its instance. (Overloading for the class BoardHandler).
     *
     * @param nickname of type String.
     * @param playerID of type int.
     */
    public Player(String nickname, int playerID) {
        this.nickname = nickname;
        this.assistantCardList = new LinkedList<>();
        Collections.addAll(assistantCardList, AssistantCard.values());
        this.coins = 1;
        this.playerID = playerID;

    }

    /**
     * Method getTower is a getter.
     *
     * @return of type Tower.
     */
    public Tower getTower() { return tower; }

    /**
     * Method setTower is a setter.
     *
     * @param tower of type Tower.
     */
    public void setTower(Tower tower){
        this.tower = tower;
    }

    /**
     * Method setWizard is a setter.
     *
     * @param wizard of type Wizard.
     */
    public void setWizard(Wizard wizard){
        this.chosenWizard = wizard;
    }

    /**
     * Method getPlayerID is a getter.
     *
     * @return of type int.
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     * Method getChosenCard is a getter.
     *
     * @return of type AssistantCard.
     */
    public AssistantCard getChosenCard() {
        return chosenCard;
    }

    /**
     * This method creates an instance of SchoolBoard linked to Player that owns it.
     *
     * @param mode specifies the rules the current game is following.
     * @param numberofPlayers specifies the number of players for this game.
     */
    public void createSchoolBoard(boolean mode, int numberofPlayers){
        this.schoolBoard = new SchoolBoard(nickname, numberofPlayers, mode);
    }


    /**
     * Returns a string containing the nickname of the player.
     *
     * @return a string with the nickname.
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Return the List of the assistant cards the player has in his deck.
     *
     * @return List of Assistant Cards.
     */
    public List<AssistantCard> getAssistantCards() {
        return assistantCardList;
    }

    /**
     * Returns the number of coins the player has at the moment.
     *
     * @return an integer.
     */
    public int getCoins() {
        return coins;
    }


    /**
     * Returns a boolean that signals if the player is the actual one that has to play now.
     *
     * @return a boolean taken from the variable turn.
     */
    public boolean isTurn() {
        return turn;
    }

    /**
     * This method is used to set the Round variable to 1 or 0 when the turn changes.
     *
     *@param turn     specification (1 your turn, 0 ended your turn).
     */
    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    /**
     * This Method is used to know the Wizard chosen by the player in question.
     *
     * @return String containing the Type of Wizard chosen by the Player.
     */
    public String getChosenWizard() {
        return chosenWizard.toString();
    }

    /**
     * This method updates the number of coins of the player.
     *
     * @param newCoins number of coins player has to collect after moving students to the dining room.
     */
    public void addCoins(int newCoins) {
        this.coins += newCoins;
    }

    /**
     * After the player intends to use his coins the method in question subtract the spent amount of coins.
     *
     * @param coins the player is spending "coins" amount for some action.
     */
    public void removeCoin(int coins) {
        this.coins -= coins;
    }

    /**
     * This method is used to get hold of the Player's SchoolBoard.
     *
     * @return the SchoolBoard reference for this player.
     */
    public SchoolBoard getSchoolBoard() {
        return schoolBoard;
    }

    /**
     * Method setChosenCard is a setter.
     *
     * @param chosenCard of type AssistantCard.
     */
    public void setChosenCard(AssistantCard chosenCard) {
        System.out.println("I've set your card :)");
        this.chosenCard = chosenCard;
    }
}
