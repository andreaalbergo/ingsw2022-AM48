package it.polimi.ingsw.client.gameBoard;

import it.polimi.ingsw.costanti.Constants;

public class CloudGrid {
    private final int cloudDimension;
    private final String[][] cloudGrid;

    public CloudGrid(int numberOfPlayers) {
        cloudDimension = numberOfPlayers;
       if (cloudDimension==Constants.MAX_PLAYERS) {
           cloudGrid = new String[Constants.MAX_PLAYERS][Constants.CLOUD_MAX_PLAYERS];
           for (int i = 0; i < Constants.MAX_PLAYERS; i++)
               for (int j = 0; j < Constants.CLOUD_MAX_PLAYERS; j++)
                   cloudGrid[i][j] = null;
       } else {
           cloudGrid = new String[Constants.MIN_PLAYERS][Constants.CLOUD_MIN_PLAYERS];
           for (int i = 0; i < Constants.MIN_PLAYERS; i++)
               for (int j = 0; j < Constants.CLOUD_MIN_PLAYERS; j++)
                   cloudGrid[i][j] = null;
       }
    }

    public String[][] getCloudGrid() {
        return cloudGrid;
    }

    public String getSingleCloud(int cloudId) throws IllegalArgumentException{
        if(cloudId<0 && cloudId>cloudDimension) throw new IllegalArgumentException();

        StringBuilder cloudArray = new StringBuilder("[");
        for (int i = 0; i < cloudDimension; i++)
            cloudArray.append(cloudGrid[cloudId][i]).append(", ");
        cloudArray.append("]");

        return cloudArray.toString();
    }

    public void emptyCloud(int cloudId) {
        //TODO
    }

    public void fillClouds() {
        //TODO
    }
}
