package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.List;

public class ExpertBoardManager extends BoardManagerDecorator{

    public ExpertBoardManager(BoardManager decoratedBoardManager) {
        super(decoratedBoardManager);
    }

    private Player currentPlayer;
    private int remainingCoinCounter = 20;

    @Override
    public void login(String nickname, Wizard chosenWizard, TowersColor towerColor){

    }

    @Override
    public ArrayList<Integer> sortFirstTurn() {
        return null;
    }

    @Override
    public void setCurrentPlayer(Player player) {

    }

    @Override
    public int getPlayerIndex(String givenPlayer) {
        return 0;
    }

    public void takeCoin() throws Exception {

        int moneyCounter = 0;

        for (int indexForChangeColor = 0; indexForChangeColor < 5; indexForChangeColor++) {    //for every index of the array control if there's a student on a place with a coin

            int studentsNumber = currentPlayer.getSchoolBoard().getDiningRoom()[indexForChangeColor];

            if(studentsNumber >= 3){
                moneyCounter += 1;

                if(studentsNumber >= 6){
                    moneyCounter += 1;

                    if(studentsNumber >= 9){
                        moneyCounter += 1;
                    }
                }
            }

        }

        if(remainingCoinCounter - moneyCounter >= 0) {
            remainingCoinCounter = remainingCoinCounter - moneyCounter;
        }

        else {

            if (remainingCoinCounter - moneyCounter <= 0 && remainingCoinCounter != 0) {
                moneyCounter = remainingCoinCounter;
                remainingCoinCounter = 0;
            }

            else {
                throw new Exception("No coins available");
            }
        }

        currentPlayer.addCoins(moneyCounter);

    }

    @Override
    public void chooseStepsMotherNature(int steps, int effect) {
    }

    @Override
    public boolean checkInfluence(Old_Island island, int effect) {
        return false;
    }

    @Override
    public boolean checkActiveCharacterCards() {
        return false;
    }

    @Override
    public void checkToAddProfessor(Color givenColor, int effect) {

    }

    @Override
    public void drawFromBagToClouds() {

    }

    @Override
    public void extractPawnsToCloud(ArrayList<Cloud> clouds) {

    }

    @Override
    public void checkNickname(String givenNickname){

    }

    @Override
    public void chooseCloudTile(Cloud cloud){

    }

    @Override
    public void buyCharacterCards(Character card){

    }

    @Override
    public boolean checkGameOver() {
        return false;
    }

    //BOZZ: created this from an error in Old_BoardManagerTest
    @Override
    public ArrayList<Player> getPlayers() {
        return null;
    }

    @Override
    public Player getCurrentPlayer() {
        return null;
    }

    @Override
    public MotherNature getMotherNature() {
        return null;
    }

    @Override
    public Round getTurn() {
        return null;
    }

    @Override
    public ArrayList<Integer> getFirstTurnSorted() {
        return null;
    }

    @Override
    public CharacterCard getCard() {
        return null;
    }

    @Override
    public Player getPlayer(int index) {
        return null;
    }

    @Override
    public ArrayList<Cloud> getClouds() {
        return null;
    }

    @Override
    public Cloud getCloud(int index) {
        return null;
    }

    @Override
    public void checkMergingIslands(IslandTile islandTile) {

    }

    @Override
    public List<IslandTile> getPreviousNextIsland(IslandTile islandTile) {
        return null;
    }

    @Override
    public Board getBoard() {
        return null;
    }
}

