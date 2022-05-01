package it.polimi.ingsw;

import java.util.ArrayList;

//CLASS MADE BY BARB, version 0.1-->OK
public class Cloud {
    private static final int NUMBER_OF_PLAYERS = Board.getNumberOfPlayers();
    private static int LAST_CLOUD_ID = 0;
    private final int cloudId;
    private final ArrayList<Color> cloudCells;

    public Cloud() {
        LAST_CLOUD_ID++;
        this.cloudId = LAST_CLOUD_ID;
        this.cloudCells = new ArrayList<>(getCloudDimension());
    }

    public static int getCloudDimension() {
        if(NUMBER_OF_PLAYERS == 3)
            return 4;
        return 3;
    }

    public int getCloudId() {
        return cloudId;
    }

    //COMMENT BY ANDREA: Maybe we should try to use a different approach by using a method in Bag to which you pass
    // the Array of Clouds and it fills them with the students, maybe implement a method that adds a student to the
    // cloud
    //

    /*
    public void fillStudents(Bag bag, Cloud cloud){
        for(int i=0; i<getCloudDimension(); i++) {
            cloud.cloudCells[i] = bag.extractPawnsToCloud();
        }
    }
    */

    public void emptyCloud(SchoolBoard schoolBoard, Cloud cloud){
        for(int i = 0; i<Board.getNumberOfPlayers(); i++) {
            schoolBoard.addStudentToEntrance(cloudCells.get(i).getColorIndex());
            cloudCells.remove(cloudCells.get(i));
        }
    }

    public void addStudentToCloud(int indexForColor){
        cloudCells.add(Color.colorFromIndex(indexForColor));
    }
}
