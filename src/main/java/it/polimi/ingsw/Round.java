package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Round {
    private final Map<String,Integer> chosenCards;
    private final ArrayList<Player> turnOrder;
    private int turnNumber;

    public Round(){
        this.chosenCards = new HashMap<>();
        this.turnOrder = new ArrayList<>();
        this.turnNumber = 0;
    }
    //called in assignNextTurn()? maybe rename this class in checkAssistantCard, then update the "else" case
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

        this.turnOrder.get(0).assignTurn();
    }
    public void assignNextTurn() {
        this.turnNumber++;
    }
    public Player getCurrentPlayer(){
        return this.turnOrder.get(0);
    }
    public void setNextCurrentPlayer(){
        this.turnOrder.remove(0);
    }
    public Integer getCurrentPlayersAssistantCard(){
        return this.chosenCards.get(getCurrentPlayer().getNickname());
    }

    /*
    private void compareAssistantCard() {
        for (int i = 0; i+1 < Board.getNumberOfPlayers(); i++) {
            for (int j = i+1; j < Board.getNumberOfPlayers(); j++){
                if(chosenCards.get(i).getValue() < chosenCards.get(j).getValue()) {
                    if(chosenCards.get(i).getValue() < indexmin) {
                        indexmin = i;
                    }
                    if(chosenCards.get(j).getValue() > indexmax) {
                        indexmax = j;
                    }
                }
                else{
                    if(chosenCards.get(j).getValue() < indexmin) {
                        indexmin = j;
                    }
                    if(chosenCards.get(i).getValue() > indexmax) {
                        indexmax = i;
                    }
                }
            }
        }
        assignRoundOrder();
    }
     //when a player has finished his turn (after chooseCloudTile() in BoardManager
    */
}
