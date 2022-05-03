package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.List;

public abstract class BoardManagerDecorator implements BoardManager {
    private final BoardManager decoratedBoardManager;
    private final List<Character> characterCards;
    private int coinBag;

    public BoardManagerDecorator(BoardManager decoratedBoardManager) {
        this.decoratedBoardManager = decoratedBoardManager;
        this.characterCards = new ArrayList<>();
        this.coinBag = 20;

        for (int i = 0; i < 3; i++) {
            //TODO
        }
    }

    @Override
    public List<Player> getPlayers() { return decoratedBoardManager.getPlayers(); }

    @Override
    public void drawFromBagToClouds() {
        decoratedBoardManager.drawFromBagToClouds();
    }

    @Override
    public void setupAllSchoolEntrances() { decoratedBoardManager.setupAllSchoolEntrances(); }

    @Override
    public void checkProfessorAddition(int indexDiningRoomUpdated) {
        decoratedBoardManager.checkProfessorAddition(indexDiningRoomUpdated);
    }

    @Override
    public void chooseStepsMotherNature(int chosenSteps) {
        decoratedBoardManager.chooseStepsMotherNature(chosenSteps);
    }

    @Override
    public void tryConqueringIsland(IslandTile islandTile) {
        decoratedBoardManager.tryConqueringIsland(islandTile);
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
    public void chooseCloudTile(Cloud cloud) {
        decoratedBoardManager.chooseCloudTile(cloud);
    }


    @Override
    public void checkGameOverConditions() {
        decoratedBoardManager.checkGameOverConditions();
    }

    public void takeCoinFromBag(){
        this.coinBag--;
    }

    public void buyCharacterCard(CharacterCard characterCard) {
        //characterCard.incrementCharacterCost();
        //TODO then maybe switch or other methods
    }

}
