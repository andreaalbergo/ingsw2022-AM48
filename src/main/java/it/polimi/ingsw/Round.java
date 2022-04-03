package it.polimi.ingsw;

import java.util.ArrayList;

public class Round {

    private ArrayList<AssistantCard> chosenCards = new ArrayList<AssistantCard>(3);
    private String[] turnOrder;
    private String currentPlayer;


    //public String getCurrentPlayer() {}

    public void chooseCard(String nickname, AssistantCard card) {}

    private void compareAssistantCard() {}

    //public String assignNextTurn(String currentPlayer) {}

    public void assignRoundOrder() {}

    public ArrayList<AssistantCard> getChosenCards() {
        return chosenCards;
    }
}
