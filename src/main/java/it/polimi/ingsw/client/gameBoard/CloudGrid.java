package it.polimi.ingsw.client.gameBoard;

import it.polimi.ingsw.model.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * CloudGrid class is used to handle every player's move on the clouds, like emptying one of them or the game filling
 * them. It is useful for the class GameBoard.
 *
 * @author David Barb
 */
public class CloudGrid {
    private final HashMap <Integer, int[]> clouds = new LinkedHashMap<>();
    private final int cloudDimension;

    /**
     * CloudGrid constructor, to create the hashmap of the clouds given the number of players.
     *
     * @param numberOfPlayers of type int - the number of players.
     */
    public CloudGrid(int numberOfPlayers) {
        if (numberOfPlayers==3)
            cloudDimension=4;
        else
            cloudDimension=3;

        for (int i = 0; i < numberOfPlayers; i++) {
            clouds.put(i, new int[cloudDimension]);
        }
    }

    /**
     * Method updateSingleCloud is used by class GameBoard to keep track of filling the cloud with given student colors.
     * @param cloudID of type int - the ID of the cloud.
     * @param colors of type ArrayList<> - the list of colors to fill given cloud.
     */
    public void updateSingleCloud(int cloudID, ArrayList<Color> colors) {
        for (int i = 0; i < cloudDimension; i++) {
            clouds.get(cloudID)[i] = colors.get(i).getColorIndex();
        }
    }

    /**
     * Method emptySingleCloud is used by class GameBoard to empty a given cloud when chosen by specific player.
     *
     * @param cloudID of type int - the ID of the cloud.
     */
    public void emptySingleCloud(int cloudID) {
        for (int i = 0; i < cloudDimension; i++) {
           clouds.get(cloudID)[i] = 5;
        }
    }

    /**
     * Method getAllStudents returns an array of int useful for the class Blueprint in order to print the clouds.
     *
     * @return of type int[] - the array of students of all clouds.
     */
    public int[] getAllStudents(){
        int[] studentsAllClouds = new int[cloudDimension*clouds.size()];

        for (int i = 0; i < clouds.size(); i++) {
            for (int j = 0; j < cloudDimension; j++) {
                studentsAllClouds[i*cloudDimension+j] = clouds.get(i)[j];
            }
        }
        return studentsAllClouds;
    }
}
