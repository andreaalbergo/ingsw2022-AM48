package it.polimi.ingsw;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Player {
    private final String nickname;
    private int idPlayerForTurn;
    private final List<AssistantCard> assistantCardList;
    private int coins;
    private boolean turn;
    private final Wizard chosenWizard;
    private final TowersColor towersColor;
    private SchoolBoard schoolBoard;
    private int numberOfPlayers;
    private boolean mode;

    public Player(String nickname, int idPlayerForTurn, boolean turn, Wizard chosenWizard, int numberOfPlayers, TowersColor towersColor, boolean mode) {
        this.nickname = nickname;
        this.idPlayerForTurn = idPlayerForTurn;
        this.assistantCardList = new LinkedList<>();
        Collections.addAll(assistantCardList, AssistantCard.values());
        this.coins = 1;
        this.turn = false;
        this.chosenWizard = chosenWizard;
        this.schoolBoard = new SchoolBoard(nickname, numberOfPlayers, mode);
        this.towersColor = towersColor;
        this.numberOfPlayers = numberOfPlayers;
        this.mode = mode;
    }


    public String getNickname() {
        return nickname;
    }

    public List<AssistantCard> getAssistantCards() {
        return assistantCardList;
    }

    public int getCoins() {
        return coins;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public String getChosenWizard() {
        return chosenWizard.toString();
    }

    public void setIdPlayerForTurn(int idPlayerForTurn) {
        this.idPlayerForTurn = idPlayerForTurn;
    }

    public void addCoins(int newCoins) {
        this.coins += newCoins;
    }

    public void removeCoin(int coins) {
        this.coins -= coins;
    }

    public void assignTurn() {
        this.turn = true;
    }

    public int getIdPlayerForTurn() {
        return idPlayerForTurn;
    }

    public SchoolBoard getSchoolBoard() {
        return schoolBoard;
    }

}
