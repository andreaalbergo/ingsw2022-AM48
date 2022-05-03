package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.List;

public interface BoardManager {

    void login(String nickname, Wizard chosenWizard, TowersColor towerColor) throws Exception;
    ArrayList<Integer> sortFirstTurn();
    void setCurrentPlayer(Player player);
    int getPlayerIndex(String givenPlayer);
    void takeCoin() throws Exception;
    void chooseStepsMotherNature(int steps, int effect);
    boolean checkInfluence(Old_Island island, int effect);
    boolean checkActiveCharacterCards();
    void checkToAddProfessor(Color givenColor, int effect);
    void drawFromBagToClouds();
    void extractPawnsToCloud(ArrayList<Cloud> clouds);
    void checkNickname(String givenNickname) throws Exception;
    void chooseCloudTile(Cloud cloud) throws Exception;
    void buyCharacterCards(Character card) throws Exception;
    boolean checkGameOver();
    ArrayList<Player> getPlayers();
    Player getCurrentPlayer();
    MotherNature getMotherNature();
    Round getTurn();
    ArrayList<Integer> getFirstTurnSorted();
    CharacterCard getCard();
    Player getPlayer(int index);
    ArrayList<Cloud> getClouds();
    Cloud getCloud(int index);
    void checkMergingIslands(IslandTile islandTile);
    List<IslandTile> getPreviousNextIsland(IslandTile islandTile);
}

