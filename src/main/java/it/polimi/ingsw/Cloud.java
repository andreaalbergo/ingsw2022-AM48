package it.polimi.ingsw;

import java.util.ArrayList;

//CLASS MADE BY BARB, version 0.1-->OK
public class Cloud {
    private final int NUMBER_OF_PLAYERS;
    private static int LAST_CLOUD_ID = 0;
    private final int cloudId;
    private final ArrayList<Color> cloudCells;
    private Board board;

    //when i call the constructor, it fills the clouds
    public Cloud(int cloudId, Board board) {
        this.cloudId = cloudId;
        this.cloudCells = new ArrayList<>(getCloudDimension());
        this.NUMBER_OF_PLAYERS = board.getNumberOfPlayers();
        this.board = board;
    }

    public int getCloudDimension() {
        if(NUMBER_OF_PLAYERS == 3) {
            return 4;
        }

        else {
            return 3;
        }
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
        for(int i = 0; i < board.getNumberOfPlayers(); i++) {
            schoolBoard.addStudentToEntrance(cloudCells.get(i).getColorIndex());
            cloudCells.remove(cloudCells.get(i));
        }
    }

    public void addStudentToCloud(int indexForColor){
        cloudCells.add(Color.colorFromIndex(indexForColor));
    }

    public boolean checkEmptyCloud(){
        //probably need getStudents because like this it says only if the ArrayList is empty, not if there aren't students on the cloud
        return this.cloudCells.isEmpty();
    }
}

