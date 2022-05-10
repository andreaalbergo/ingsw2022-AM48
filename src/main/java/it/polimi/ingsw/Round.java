package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Round {
    private final Map<String,Integer> chosenCards;
    private ArrayList<String> roundOrder;
    private ArrayList<String> nextRoundOrder = new ArrayList<>();
    private int turnNumber; //is the index of the array of players
    private ArrayList<Player> players;
    private int roundNumber = 1;
    private Player currentPlayer;
    private final Board board;

    public Round(Board board){
        this.chosenCards = new HashMap<>();
        this.roundOrder = new ArrayList<>(board.getNumberOfPlayers());
        this.turnNumber = 0;
        this.board = board;

        setUpPlayers();

        ArrayList<Integer> firstTurnOrder = board.getBoardManager().getFirstTurnSorted();

        for(int playerIndex = 0; playerIndex < board.getNumberOfPlayers(); playerIndex++){

            roundOrder.add(players.get(firstTurnOrder.get(playerIndex)).getNickname());

        }
    }

    //BOZZ: BARB add this javadoc
    /**
     *
     *
     * @param player is the nickname of the player
     * @param chosenAssistantCard is the card chosen by player
     */
    //called in assignNextTurn()? maybe rename this class in checkAssistantCard, then update the "else" case
    public void chooseAssistantCard(String player, AssistantCard chosenAssistantCard) {

        int index = board.getBoardManager().getPlayerIndex(player);

        if(players.get(index).getAssistantCards().contains(chosenAssistantCard)){
            players.get(index).getAssistantCards().remove(chosenAssistantCard);
            if(this.turnNumber==0)
                chosenCards.putIfAbsent(players.get(index).getNickname(), chosenAssistantCard.getNumber_of_steps());
            else
                chosenCards.replace(players.get(index).getNickname(), chosenAssistantCard.getNumber_of_steps());
        } else
            System.out.println("ERROR!!!! CARD IS ALREADY USED BEFORE");

        if(chosenCards.size() == board.getNumberOfPlayers()){
            compareAssistantCards();
        }

    }

    //BARB: remember to overload to expertBoardManager
    /*
    BOZZ: is like assignNextRound() and compareAssistantCards() but this:
    - doesn't remove chosenCard from the Map
    - doesn't change idPlayerForTurn in Player
    - doesn't change turn in Player
    - doesn't update roundNumber and turnNumber

    public void assignRoundOrder(SimpleBoardManager simpleBoardManager) {

        int tmpHighestCardValue = 0;

        for(Player player: simpleBoardManager.getPlayers()) {
            if(this.chosenCards.get(player.getNickname()) > tmpHighestCardValue) {
                tmpHighestCardValue = this.chosenCards.get(player.getNickname());
                roundOrder.add(0, player.getNickname());
            } else
                roundOrder.add(player.getNickname());
        }

        //this.turnOrder.get(0).assignTurn();
    }

     */

    /**
     * This methods is used to assign the turn, it checks the assistant cards played by the players and assign in nextRoundOrder the right order
     * (in position 0 there's the turn number of the player 0 and so on)
     *
     * @throws Exception from playerFromTurnNumber(), it can't happen but there's still this case
     */

    public void assignNextRound() throws Exception {

        //set charactercCard = false if it has been played
        roundOrder = nextRoundOrder;

        for(int playerIndex = 0; playerIndex < board.getNumberOfPlayers(); playerIndex++){
            chosenCards.remove(playerIndex);
            players.get(playerIndex).setIdPlayerForTurn(board.getBoardManager().getPlayerIndex(roundOrder.get(playerIndex)));
        }

        setUpCurrentPlayer();
        currentPlayer.setTurn(false);

        players.get(playerFromTurnNumber(turnNumber)).setTurn(true);
        board.getBoardManager().setCurrentPlayer(players.get(playerFromTurnNumber(turnNumber)));
        currentPlayer = board.getBoardManager().getCurrentPlayer();
        turnNumber += 1;

        if(turnNumber >= board.getNumberOfPlayers()) {
            turnNumber = 0;
            roundNumber += 1;
            board.getBoardManager().setCurrentPlayer(players.get(playerFromTurnNumber(turnNumber)));
        }

    } //when a player has finished his turn (after chooseCloudTile() in BoardManager

    /*
    private int indexMin = 10, indexMax = 0;

    private void compareAssistantCard() {
        for (int firstPointer = 0; firstPointer+1 < Board.getNumberOfPlayers(); firstPointer++) {
            for (int secondPointer = firstPointer+1; secondPointer < Board.getNumberOfPlayers(); secondPointer++){
                if(chosenCards.get(firstPointer).getValue() < chosenCards.get(secondPointer).getValue()) {
                    if(chosenCards.get(firstPointer).getValue() < indexMin) {
                        indexMin = firstPointer;
                    }
                    if(chosenCards.get(secondPointer).getValue() > indexMax) {
                        indexMax = secondPointer;
                    }
                }
                else{
                    if(chosenCards.get(secondPointer).getValue() < indexMin) {
                        indexMin = secondPointer;
                    }
                    if(chosenCards.get(firstPointer).getValue() > indexMax) {
                        indexMax = firstPointer;
                    }
                }
            }
        }
        assignRoundOrder();
    }
     //when a player has finished his turn (after chooseCloudTile() in BoardManager

     */

    /**
     * This methods compare the assistant cards chosen by the player to assign the turn order
     */

    private void compareAssistantCards(){

        String playerMax = null;

        for(Player firstPointer : players){
            for(Player secondPointer : players){
                if(firstPointer != secondPointer){

                    if(chosenCards.get(firstPointer.getNickname()) > chosenCards.get(secondPointer.getNickname())){

                        playerMax = firstPointer.getNickname();

                    }

                    else{

                        playerMax = secondPointer.getNickname();

                    }

                }
            }

            if(playerMax.equals(firstPointer.getNickname())){
                nextRoundOrder.add(0, playerMax);
            }

        }

    }

    /**
     * This methods gives the index of the player from his turn number
     *
     * @param turnNumber is the turn of the player
     * @return the index of the player (in players arrayList)
     * @throws Exception if the player isn't found (can't happen but there's still this case)
     */

    private int playerFromTurnNumber(int turnNumber) throws Exception {

        for(int playerIndex = 0; playerIndex < board.getNumberOfPlayers(); playerIndex++){

            if(roundOrder.get(playerIndex).equals(players.get(playerIndex).getNickname())){

                return playerIndex;

            }

        }

        throw new Exception("Player not found!");

    }

    /*
    Included in the constructor

    public void setUpTurnOrder(){

        for(int playerNumber = 0; playerNumber < Board.getNumberOfPlayers(); playerNumber++){

            roundOrder.add(players.get(playerNumber).getNickname());

        }

    }

     */

    /**
     * This mathods changes the current player setting the turn in Player class
     */

    public void setUpCurrentPlayer(){
        currentPlayer.setTurn(false);
        currentPlayer = board.getBoardManager().getCurrentPlayer();
        currentPlayer.setTurn(true);
    }

    public void setUpPlayers(){
        players = board.getBoardManager().getPlayers();
    }

    public int getChosenCardsNumber_of_Steps(AssistantCard assistantCard) {
        return assistantCard.getNumber_of_steps();
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public int getCurrentPlayersAssistantCard() {
        return chosenCards.get(board.getBoardManager().getCurrentPlayer().getNickname());
    }

    public Map<String, Integer> getChosenCards() {
        return chosenCards;
    }

    public ArrayList<String> getRoundOrder() {
        return roundOrder;
    }

    public ArrayList<String> getNextRoundOrder() {
        return nextRoundOrder;
    }

    public String getCurrentPlayer(){
        return this.roundOrder.get(0);
    }

    public void setNextCurrentPlayer(){
        this.roundOrder.remove(0);
    }

}


