package it.polimi.ingsw;

//CLASS MADE BY BARB, version 0.1-->OK
public class Cloud {
    private static final int NUMBER_OF_PLAYERS = Board.getNumberOfPlayers();
    private static int LAST_CLOUD_ID = 0;
    private final int cloudId;
    private final int[] cloudCells;

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

    public void fillStudents(Bag bag, Cloud cloud){
        for(int i=0; i<getCloudDimension(); i++) {
            cloud.cloudCells[i] = bag.extractPawnToCloud();
        }
    }

    public void emptyCloud(SchoolBoard schoolBoard, Cloud cloud){
        for(int i = 0; i<Board.getNumberOfPlayers(); i++) {
            schoolBoard.addStudentToEntrance(cloud.cloudCells[i]);
            cloud.cloudCells[i] = 0;
        }
    }
}
