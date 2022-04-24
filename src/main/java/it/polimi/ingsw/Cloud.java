package it.polimi.ingsw;

public class Cloud {
    private static final int NUMBER_OF_PLAYERS = Board.getNumberOfPlayers();
    private static int LAST_CLOUD_ID = 0;
    private final int cloudId;
    private static int[] cloudCells;

    public Cloud() {
        LAST_CLOUD_ID++;
        this.cloudId = LAST_CLOUD_ID;
        this.cloudCells = new int[getCloudDimension()];
    }

    public static int getCloudDimension() {
        if(NUMBER_OF_PLAYERS == 3)
            return 4;
        return 3;
    }

    public int getCloudId() {
        return cloudId;
    }

    public void moveFromBagToCloud(){
        int i;

        for(i=0; i<getCloudDimension(); i++) {
            Bag.drawOneStudentToCloudTile();
        }
    }

    public static void addStudentToSelectedCloudCell(int indexCell, int studentColor) {
        cloudCells[indexCell] = studentColor;
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
