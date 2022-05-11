package it.polimi.ingsw;

import java.util.ArrayList;

/**
 * Cloud class is an entity that has number of players instances and specific number of student cells that are filled
 * up every game's turn from the Bag class; then they are picked from every player during the same turn to fill
 * their own school entrances.
 *
 * @author David Barb
 */
public class Cloud {
    private final int NUMBER_OF_PLAYERS;
    private static int LAST_CLOUD_ID = 0;
    private final int cloudId;
    private final ArrayList<Color> cloudCells;
    private Board board;

    //when I call the constructor, it fills the clouds
    public Cloud(int cloudId, Board board) {
        this.cloudId = cloudId;
        this.cloudCells = new ArrayList<>();
        this.NUMBER_OF_PLAYERS = 2;
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
        for(int i = 0; i < 2; i++) {
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

