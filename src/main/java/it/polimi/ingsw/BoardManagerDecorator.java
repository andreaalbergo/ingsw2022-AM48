package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.List;

public abstract class BoardManagerDecorator implements BoardManager {
    private final BoardManager decoratedBoardManager;
    // private List<Character> characterCards;
    private int coinBag;

    public BoardManagerDecorator(BoardManager decoratedBoardManager) {
        this.decoratedBoardManager = decoratedBoardManager;
        this.coinBag = 20;
        //TODO
    }


    @Override
    public ArrayList<Player> getPlayers() {
        return decoratedBoardManager.getPlayers();
    }

    @Override
    public void drawFromBagToClouds() {
        decoratedBoardManager.drawFromBagToClouds();
    }

    //public void chooseThreeStudents() {}

    @Override
    public void checkToAddProfessor(Color givenColor, int effect) {

        decoratedBoardManager.checkToAddProfessor(givenColor, effect);
    }

    @Override
    public void chooseStepsMotherNature(int chosenSteps, int effect) {
        decoratedBoardManager.chooseStepsMotherNature(chosenSteps, effect);
    }

    @Override
    public boolean checkInfluence(Old_Island old_island, int effect){
        return decoratedBoardManager.checkInfluence(old_island, effect);
    }

    @Override
    public boolean checkActiveCharacterCards(){
        return decoratedBoardManager.checkActiveCharacterCards();
    }

    /*
    @Override
    public void drawFromBagToClouds(){
        decoratedBoardManager.extractPawnsToCloud(decoratedBoardManager.getClouds());
    }
     */

    @Override
    public void extractPawnsToCloud(ArrayList<Cloud> clouds){
        decoratedBoardManager.extractPawnsToCloud(decoratedBoardManager.getClouds());
    }

    @Override
    public void checkNickname(String givenNickname) throws Exception {
        decoratedBoardManager.checkNickname(givenNickname);
    }

    @Override
    public void chooseCloudTile(Cloud cloud) throws Exception {
        decoratedBoardManager.chooseCloudTile(cloud);
    }

    @Override
    public void buyCharacterCards(Character characterCard) throws Exception {
        decoratedBoardManager.buyCharacterCards(characterCard);
        //characterCard.incrementCharacterCost();
        //TO DO then maybe switch or other methods
    }

    @Override
    public boolean checkGameOver() {
        return decoratedBoardManager.checkGameOver();
    }

    /*
    @Override
    public ArrayList<Player> getPlayers(){
        return decoratedBoardManager.getPlayers();
    }
     */

    @Override
    public Player getCurrentPlayer(){
        return decoratedBoardManager.getCurrentPlayer();
    }

    @Override
    public MotherNature getMotherNature(){
        return decoratedBoardManager.getMotherNature();
    }

    @Override
    public  Round getTurn(){
        return decoratedBoardManager.getTurn();
    }

    @Override
    public ArrayList<Integer> getFirstTurnSorted(){
        return decoratedBoardManager.getFirstTurnSorted();
    }

    @Override
    public CharacterCard getCard(){
        return decoratedBoardManager.getCard();
    }

    @Override
    public Player getPlayer(int index){
        return decoratedBoardManager.getPlayer(index);
    }

    @Override
    public ArrayList<Cloud> getClouds(){
        return decoratedBoardManager.getClouds();
    }

    @Override
    public void checkMergingIslands(IslandTile islandTile) {
        decoratedBoardManager.checkMergingIslands(islandTile);
    }

    @Override
    public List<IslandTile> getPreviousNextIsland(IslandTile islandTile) {
        return decoratedBoardManager.getPreviousNextIsland(islandTile);
    }

    @Override
    public Board getBoard(){
        return decoratedBoardManager.getBoard();
    }

    //here setters from interface?

    public void takeCoinFromBag(){
        this.coinBag--;
    }

    /*
    @Override
    public void tryConqueringIsland(IslandTile islandTile) {
        decoratedBoardManager.tryConqueringIsland(islandTile);
    }
    */

}
