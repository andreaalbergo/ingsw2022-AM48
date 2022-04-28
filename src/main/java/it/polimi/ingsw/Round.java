package it.polimi.ingsw;

import java.util.ArrayList;

public class Round {

    private ArrayList<AssistantCard> chosenCards;
    private ArrayList<String> turnOrder;
    private Player currentPlayer;
    private ArrayList<Player> players;
    int indexmin = 11, indexmax = 0; //used in compareAssistantCards and assignRoundOrder()


    public void Round(int numberOfPlayers, ArrayList<AssistantCard> chosenCards, ArrayList<String> turnOrder){

        this.chosenCards = new ArrayList<AssistantCard>();
        this.turnOrder = new ArrayList<String>();

    }

    public void chooseCard(AssistantCard card) throws Exception {

        chosenCards.add(card);

        for(AssistantCard a : chosenCards){
            if(a == card){
                chosenCards.remove(card);
                throw new Exception("Error, this card has already been played");
            }
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


    }

    public void assignNextTurn() {
    } //when a player has finished his turn

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
