package it.polimi.ingsw;

import java.util.ArrayList;

//CLASS MADE BY BARB, version 0.1-->OK
public class Cloud {
    private static final int NUMBER_OF_PLAYERS = Board.getNumberOfPlayers();
    private final int cloudId;
    private final ArrayList<Color> cloudCells;

    public Cloud(int cloudId) {
        this.cloudId = cloudId;
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

    public void emptyCloud(SchoolBoard schoolBoard){
        for(int i = 0; i<Board.getNumberOfPlayers(); i++) {
            schoolBoard.addStudentToEntrance(cloudCells.get(i).getColorIndex());
            cloudCells.remove(cloudCells.get(i));
        }
    }

    public void addStudentToCloud(int indexForColor){
        cloudCells.add(Color.colorFromIndex(indexForColor));
    }
}
