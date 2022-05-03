package it.polimi.ingsw;

import java.util.List;

public interface BoardManager {
    List<Player> getPlayers();
    void drawFromBagToClouds();
    void setupAllSchoolEntrances();
    //void chooseThreeStudentsFromEntrance(); //it's a very complex function, do I need it here? WAIT FOR CONTROLLER
    void checkProfessorAddition(int indexDiningRoomUpdated);
    void chooseStepsMotherNature(int chosenSteps);
    void tryConqueringIsland(IslandTile islandTile);
    void checkMergingIslands(IslandTile islandTile);
    List<IslandTile> getPreviousNextIsland(IslandTile islandTile);
    void chooseCloudTile(Cloud cloud);
    void checkGameOverConditions();
}
