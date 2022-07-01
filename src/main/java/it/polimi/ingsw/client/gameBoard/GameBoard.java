package it.polimi.ingsw.client.gameBoard;

import it.polimi.ingsw.costanti.Blueprint;
import it.polimi.ingsw.model.Cloud;
import it.polimi.ingsw.model.IslandTile;

import it.polimi.ingsw.model.Tower;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GameBoard is the graphical representation of our game model, which consists of islands, clouds and players' school.
 *
 * @author David Barb
 */
public class GameBoard {
    private final ArchipelagoGrid archipelagoGrid;
    private final CloudGrid clouds;
    private final HashMap<String, SchoolGrid> schools;
    private final int NUMBER_OF_PLAYERS;
    private final Blueprint boardScheme = new Blueprint();

    private Integer motherNaturePosition = 0;


    /**
     * GameBoard constructor in order to make its instance.
     */
    public GameBoard(int numberOfPlayers) {
        archipelagoGrid = new ArchipelagoGrid();
        clouds = new CloudGrid(numberOfPlayers);
        this.NUMBER_OF_PLAYERS = numberOfPlayers;
        schools = new HashMap<>();
    }



    /**
     * Method getArchipelago is used to get the ArchipelagoGrid object. Check its class to know more about it.
     *
     * @return of type ArchipelagoGrid - the grid of the islands.
     */
    public ArchipelagoGrid getArchipelago() {
        return archipelagoGrid;
    }

    /**
     * Method getClouds is used to get the CloudGrid object. Check its class to know more about it.
     *
     * @return of type CloudGrid - the grid of clouds.
     */
    public CloudGrid getClouds() {
        return clouds;
    }

    /**
     * Method getSchools is used to get all the schoolBoards for the CLI view.
     *
     * @return of type ArrayList<> - the list of schools.
     */
    public HashMap<String, SchoolGrid> getSchools() {
        return schools;
    }

    /**
     * Method getNumberOfPlayers is used to get the number of players
     *
     * @return of type int.
     */
    public int getNumberOfPlayers() {
        return NUMBER_OF_PLAYERS;
    }

    /**
     * Method insertNicknameToTower is used to create a hashmap with nicknames as keys and Tower as value.
     *
     * @param nickname of type String - the chosen name of given player.
     * @param tower of type Tower - the chosen tower by given nickname.
     */
    public void insertNicknameToTower(String nickname, Tower tower) {
        schools.put(nickname, new SchoolGrid(getNumberOfPlayers(), tower.getIndex()));
    }

    /**
     * Method getSchoolFromNickname is a getter
     *
     * @param nickname of type SchoolGrid.
     * @return of type String.
     */
    public SchoolGrid getSchoolFromNickname(String nickname) {
        return schools.get(nickname);
    }

    /**
     * Method setCloudGrid sets the list of clouds in order to update the CLI view (if phase is 1, which means at "start
     * game" phase, we print at the end which is after player's hands in normal mode, after character cards in expert
     * mode).
     *
     * @param clouds of type List<> - the list of clouds.
     * @param toEmpty of type boolean - true if clouds are full and need to be emptied.
     */
    public void setCloudGrid(List<Cloud> clouds, boolean toEmpty) {
        if(toEmpty)
            for (int i = 0; i < getNumberOfPlayers(); i++)
                this.clouds.emptySingleCloud(i);
        else
            for (int i = 0; i < getNumberOfPlayers(); i++)
                this.clouds.updateSingleCloud(i, clouds.get(i).getCloudCells());
    }

    /**
     * Method setArchipelagoGrid is used to set the archipelago structure for the CLI view, it checks for the game
     * phase (if phase is 1 it means game started and setup phase).
     *
     * @param islands of type List<> - the list of islands.
     */
    public void setArchipelagoGrid(List<IslandTile> islands) {
        for (Map.Entry<String,SchoolGrid> school : schools.entrySet())
            school.getValue().resetTowers(getNumberOfPlayers());

        for (int i = 0; i < 12; i++) {
            archipelagoGrid.updateIsland(i, 0, islands.get(i).getIslandID());
            if (getMotherNaturePosition()==i)
                archipelagoGrid.updateIsland(i, 1, 1);
            else if (getMotherNaturePosition()!=i) {
                archipelagoGrid.updateIsland(i, 1, 0);
            }

            if (islands.get(i).getIslandOwner()!=null){
                schools.get(islands.get(i).getIslandOwner().getNickname()).decrementTowers();
                archipelagoGrid.updateIsland(i, 2, islands.get(i).getIslandOwner().getTower().getIndex());
            } else
                archipelagoGrid.updateIsland(i, 2, 3);

            archipelagoGrid.updateIsland(i, 3, islands.get(i).getArchipelagoDimension());
            archipelagoGrid.updateIsland(i, 4, islands.get(i).getStudents()[0]);
            archipelagoGrid.updateIsland(i, 5, islands.get(i).getStudents()[1]);
            archipelagoGrid.updateIsland(i, 6, islands.get(i).getStudents()[2]);
            archipelagoGrid.updateIsland(i, 7, islands.get(i).getStudents()[3]);
            archipelagoGrid.updateIsland(i, 8, islands.get(i).getStudents()[4]);
            }
    }

    /**
     * Method printCLI is used to print the entire game board for the CLI view.
     */
    public void printCLI() {
        System.out.println(boardScheme.printBoard(getNumberOfPlayers(), this));
    }

    /**
     * Method setMotherNaturePosition is a setter.
     *
     * @param islandTile of type Integer - given islandTile.
     */
    public void setMotherNaturePosition(Integer islandTile) {
        motherNaturePosition = islandTile;
    }

    /**
     * Method getMotherNaturePosition is a getter.
     *
     * @return of type Integer.
     */
    public Integer getMotherNaturePosition() {
        return motherNaturePosition;
    }
}
