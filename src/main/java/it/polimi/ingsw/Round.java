package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Round {
    private final Map<String,Integer> chosenCards;
    private final ArrayList<Player> turnOrder;
    private final ArrayList<Player> nextChooseCardOrder;
    private int turnNumber;

    public Round(){
        this.chosenCards = new HashMap<>();
        this.turnOrder = new ArrayList<>();
        this.nextChooseCardOrder = new ArrayList<>();
        this.turnNumber = 0;
    }
    //remake chooseAssistantCard in base of nextChooseCardOrder
    public void chooseAssistantCard(Player player, AssistantCard chosenAssistantCard) {
        if(player.getAssistantCards().contains(chosenAssistantCard)){
            player.getAssistantCards().remove(chosenAssistantCard);
            if(this.turnNumber==0)
                chosenCards.putIfAbsent(player.getNickname(), chosenAssistantCard.getNumber_of_steps());
            else
                chosenCards.replace(player.getNickname(), chosenAssistantCard.getNumber_of_steps());
        } else
            System.out.println("ERROR!!!! CARD IS ALREADY USED BEFORE, CAN'T USE IT TWICE!!!!");
    }

    //BARB: remember to overload to expertBoardManager
    public void assignTurnOrder(SimpleBoardManager simpleBoardManager) {
        int tmpHighestCardValue = 0;

        for(Player player: simpleBoardManager.getPlayers()) {
            if(this.chosenCards.get(player.getNickname()) > tmpHighestCardValue) {
                tmpHighestCardValue = this.chosenCards.get(player.getNickname());
                turnOrder.add(0, player);
            } else
                turnOrder.add(player);
        }

        this.turnOrder.get(0).assignPlayerTurn();
    }

    public ArrayList<Player> getTurnOrder() {
        return turnOrder;
    }
    private void assignNextTurn() {
        this.turnNumber++;
    }
    public Player getCurrentPlayer(){
        return this.turnOrder.get(0);
    }
    public void setNextCurrentPlayer(){
        this.turnOrder.get(0).removePlayerTurn();
        if(!this.turnOrder.isEmpty()) {
            this.turnOrder.get(1).assignPlayerTurn();
            this.nextChooseCardOrder.add(0, this.turnOrder.get(0));
            this.turnOrder.remove(0);
        } else
            assignNextTurn();
    }
    public Integer getCurrentPlayersAssistantCard(){
        return this.chosenCards.get(getCurrentPlayer().getNickname());
    }

    public int getTurnNumber() {
        return turnNumber;
    }
}