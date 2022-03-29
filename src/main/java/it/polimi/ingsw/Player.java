package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String nickname;
    private int idPlayerForTurn;
    private List assistantCards;
    private int coins;
    private boolean turn;
    private String chosenWizard;

    public Player(String nickname, int idPlayerForTurn, boolean turn, String chosenWizard) {
        this.nickname = nickname;
        this.idPlayerForTurn = idPlayerForTurn;
        this.assistantCards = new ArrayList<AssistantCard>();
        this.coins = 1;
        this.turn = turn;
        this.chosenWizard = chosenWizard;
    }


    public String getNickname() {
        return nickname;
    }

    public List getAssistantCards() {
        return assistantCards;
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
}
