package it.polimi.ingsw;

public class CloudTile {
    private static int LAST_ID = 0;
    private final int cloudId;
    private final int [] cloudCells;

    public CloudTile() {
        LAST_ID++;
        this.cloudId = LAST_ID;
        this.cloudCells = new int[Board.getNumberOfPlayers()];
    }

    public int getCloudId() {
        return cloudId;
    }

    public void moveFromBagToAllClouds(){
        //TODO
    }

    public void chooseCLoud(int cloudId) {
        moveFromCloudToSchoolEntrance(cloudId);
        emptyCloud();
    }

    private void moveFromCloudToSchoolEntrance(int cloudId) {
        //TODO
    }

    private void emptyCloud(){
        int i;
        for(i = 0; i < Board.getNumberOfPlayers(); i++)
            cloudCells[i] = 0;
    }
}
