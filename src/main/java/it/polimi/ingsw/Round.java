package it.polimi.ingsw;

import java.util.ArrayList;

public class Round {

    private ArrayList<AssistantCard> chosenCards;
    private ArrayList<String> turnOrder;
    private Player currentPlayer;
    private ArrayList<Player> players;
    int indexmin = 11, indexmax = 0; //used in compareAssistantCards and assignRoundOrder()
    int turnNumber = 0;             //for keeping track when turnNumber == numberOfPlayers
    int roundNumber = 1;            //can be useless
    int assistantCardChosen = 0;


    public Round(){

        this.chosenCards = new ArrayList<>();
        this.turnOrder = new ArrayList<>();
        this.currentPlayer = null;
        this.players = new ArrayList<>(Board.getNumberOfPlayers());

    }

    //called in assignNextTurn()?
    public void chooseCard(AssistantCard card) throws Exception {

        for(AssistantCard a : chosenCards){
            if(a == card){
                throw new Exception("Error, this card has already been played");
            }
        }

        chosenCards.add(card);
        assistantCardChosen += 1;

        if(assistantCardChosen == Board.getNumberOfPlayers()){
            compareAssistantCard();
            assistantCardChosen = 0;
        }

    }

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

    public void assignNextTurn() {

        currentPlayer.setTurn(false);
        players.get(turnNumber).setTurn(true);
        turnNumber += 1;

        if(turnNumber >= Board.getNumberOfPlayers()) {
            turnNumber = 0;
            roundNumber += 1;
            //BoardManager.drawFromBagToClouds();
        }

    } //when a player has finished his turn (after chooseCloudTile() in BoardManager

    public void assignRoundOrder() {

        for(Player p : players) {

            if (indexmin == p.getIdPlayerForTurn()) {

                turnOrder.add(p.getNickname());

            }
        }

        if(Board.getNumberOfPlayers() == 2) {

            for (Player p : players) {

                if (indexmax == p.getIdPlayerForTurn()) {

                    turnOrder.add(p.getNickname());

                }
            }
        }

        else{
            for(Player p : players) {

                if (indexmin != p.getIdPlayerForTurn() && indexmax != p.getIdPlayerForTurn()) {

                    turnOrder.add(p.getNickname());

                }

                if(indexmax == p.getIdPlayerForTurn()){

                    turnOrder.add(p.getNickname());

                }

            }

            for(Player p : players) {

                if(indexmax == p.getIdPlayerForTurn()){

                    turnOrder.add(p.getNickname());

                }
            }
        }
    }

    public ArrayList<AssistantCard> getChosenCards() {
        return chosenCards;
    }
}
