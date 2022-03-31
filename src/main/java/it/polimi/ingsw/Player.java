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
    private final String chosenWizard;

    public Player(String nickname, int idPlayerForTurn, boolean turn, String chosenWizard) {
        this.nickname = nickname;
        this.idPlayerForTurn = idPlayerForTurn;
        this.assistantCardList = new LinkedList<>();
        Collections.addAll(assistantCardList, AssistantCard.values());
        this.coins = 1;
        this.turn = turn;
        this.chosenWizard = chosenWizard;
    }


    public String getNickname() {
        return nickname;
    }

    public List getAssistantCards() {
        return assistantCardList;
    }

    public int getCoins() {
        return coins;
    }

    public boolean isTurn() {
        return turn;
    }

    public String getChosenWizard() {
        return chosenWizard;
    }

    public void setIdPlayerForTurn(int idPlayerForTurn) {
        this.idPlayerForTurn = idPlayerForTurn;
    }

    public void addCoins(int newcoins) {
        this.coins += newcoins;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public int getIdPlayerForTurn() {
        return idPlayerForTurn;
    }
}
