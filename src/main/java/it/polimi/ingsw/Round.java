package it.polimi.ingsw;

import java.util.ArrayList;

public class Round {

    private ArrayList<AssistantCard> chosenCards;
    private ArrayList<String> turnOrder;
    private Player currentPlayer;
    private int numberOfPlayers;


    public void Round(int numberOfPlayers, ArrayList<AssistantCard> chosenCards, ArrayList<String> turnOrder){
        this.numberOfPlayers=numberOfPlayers;
        if(numberOfPlayers == 2) {
            chosenCards = new ArrayList<AssistantCard>(2);
            turnOrder = new ArrayList<String>(2);
        }
        if(numberOfPlayers == 3) {
            chosenCards = new ArrayList<AssistantCard>(3);
            turnOrder = new ArrayList<String>(3);
        }
    }

    public void chooseCard(String nickname, AssistantCard card) {
    }

        //NEED A WAY TO ASSOCIATE NICKNAME TO INDEX WORKING ONLY IN THIS CLASS
    private void compareAssistantCard() {

        ArrayList<Integer> turnOrderIndex = new ArrayList<Integer>(numberOfPlayers);


        //for (int i = 0; i+1 < numberOfPlayers; i++) {
            //for (int j = i+1; j < numberOfPlayers; j++){

                //if(chosenCards.get(i).getNumber_of_steps() < chosenCards.get(j).getNumber_of_steps()){

                //}
            //}
        //}
    }

    //NEED TO COMPLETE PREVIOUS CLASS
    //public String assignNextTurn(String currentPlayer) {}
    public void assignRoundOrder() {}

    public ArrayList<AssistantCard> getChosenCards() {
        return chosenCards;
    }
}
