package it.polimi.ingsw.client.gameBoard;

import it.polimi.ingsw.costanti.Blueprint;
import it.polimi.ingsw.model.Cloud;
import it.polimi.ingsw.model.IslandTile;

import it.polimi.ingsw.model.Tower;

import java.util.HashMap;
import java.util.List;

/**
 * GameBoard is the graphical representation of our game model, which consists of islands, clouds and players' school.
 *
 * @author David Barb
 */
public class GameBoard {
    private final ArchipelagoGrid archipelagoGrid;
    private int numberOfSchools;
    private final CloudGrid clouds;
    private HashMap<Integer, SchoolGrid> schools;
    private final int NUMBER_OF_PLAYERS;
    private final Blueprint boardScheme = new Blueprint();

    private Integer motherNaturePosition;


    /**
     * GameBoard constructor in order to make its instance.
     *
     * @param numberOfPlayers of type int - the number of players.
     */
    public GameBoard(int numberOfPlayers) {
        archipelagoGrid = new ArchipelagoGrid();
        clouds = new CloudGrid(numberOfPlayers);
        this.NUMBER_OF_PLAYERS = numberOfPlayers;
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
    public HashMap<Integer, SchoolGrid> getSchools() {
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
        schools.put(numberOfSchools, new SchoolGrid(getNumberOfPlayers(), tower.getIndex(), nickname));
        numberOfSchools++;
    }

    public SchoolGrid getSchoolFromNickname(String nickname) {
        return schools.get(nickname);
    }

    /**
     * Method setCloudGrid sets the list of clouds in order to update the CLI view (if phase is 1, which means at "start
     * game" phase, we print at the end which is after player's hands in normal mode, after character cards in expert
     * mode).
     *
     * @param clouds of type List<> - the list of clouds.
     * @param phase of type int - the phase of the game.
     * @param toEmpty of type boolean - true if clouds are full and need to be emptied.
     */
    public void setCloudGrid(List<Cloud> clouds, int phase, boolean toEmpty) {
        if (phase==1) {
            for (int i = 0; i < getNumberOfPlayers(); i++) {
                this.clouds.updateSingleCloud(i, clouds.get(i).getCloudCells());
            }
        } else if(toEmpty){
            for (int i = 0; i < getNumberOfPlayers(); i++) {
                this.clouds.emptySingleCloud(i);
            }
            boardScheme.printBoard(getNumberOfPlayers(),this);
        } else {
            for (int i = 0; i < getNumberOfPlayers(); i++) {
                this.clouds.updateSingleCloud(i, clouds.get(i).getCloudCells());
            }
            boardScheme.printBoard(getNumberOfPlayers(),this);
        }
    }

    /**
     * Method setArchipelagoGrid is used to set the archipelago structure for the CLI view and it checks for the game
     * phase (if phase is 1 it means game started and setup phase).
     *
     * @param islands of type List<> - the list of islands.
     * @param phase of type int - the game phase.
     */
    public void setArchipelagoGrid(List<IslandTile> islands, int phase) {
        if(phase==1) {
            for (int i = 1; i < 11; i++) {
                int[] students = islands.get(i).getStudents();

                for (int j = 0; j < 5; j++) {
                    if (students[j] != 0) {
                        archipelagoGrid.updateIsland(i, 4 + j, 1);
                        break;
                    }
                }
            }
        } else {
            for (int i = 0; i < 12; i++) {
                archipelagoGrid.updateIsland(i, 0, islands.get(i).getIslandID());
                archipelagoGrid.updateIsland(i, 1, getMotherNaturePosition());

                if (islands.get(i).getIslandOwner()!=null)
                    archipelagoGrid.updateIsland(i, 2, islands.get(i).getIslandOwner().getTower().getIndex());
                else
                    archipelagoGrid.updateIsland(i, 2, 3);

                archipelagoGrid.updateIsland(i, 3, islands.get(i).getArchipelagoDimension());
                archipelagoGrid.updateIsland(i, 4, islands.get(i).getStudents()[0]);
                archipelagoGrid.updateIsland(i, 5, islands.get(i).getStudents()[1]);
                archipelagoGrid.updateIsland(i, 6, islands.get(i).getStudents()[2]);
                archipelagoGrid.updateIsland(i, 7, islands.get(i).getStudents()[3]);
                archipelagoGrid.updateIsland(i, 8, islands.get(i).getStudents()[4]);
            }

            boardScheme.printBoard(getNumberOfPlayers(), this);
        }
    }

    /**
     * Method updateSchools is used to print all schoolboards for the CLI view.
     */
    public void printCLI() {
        boardScheme.printBoard(getNumberOfPlayers(), this);
    }

    public void setMotherNaturePosition(Integer islandTile) {
        motherNaturePosition = islandTile;
    }

    public Integer getMotherNaturePosition() {
        return motherNaturePosition;
    }
}
