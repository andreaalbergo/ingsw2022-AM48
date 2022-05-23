package it.polimi.ingsw.model;

import java.util.ArrayList;

/**
 * Cloud class is an entity that has number of players instances and specific number of student cells that are filled
 * up every game's turn from the Bag class; then they are picked from every player during the same turn to fill
 * their own school entrances.
 *
 * @author David Barb
 */
public class Cloud {
    private final ArrayList<Color> cloudCells = new ArrayList<>();
    private final Board board;

    public Cloud(Board board) {
        this.board = board;
    }

    public void fillStudents(){
        if(board.getActivePlayers().size()==3) {
            for (int i = 0; i < 4; i++) {
                addStudentToCloud(Color.colorFromIndex(board.getBoardManager().getBag().getRandomColorFromBag()));
            }
        } else {
            for (int i = 0; i < 3; i++) {
                addStudentToCloud(Color.colorFromIndex(board.getBoardManager().getBag().getRandomColorFromBag()));
            }
        }
    }


    public void emptyCloud(SchoolBoard schoolBoard){
        for(int i = 0; i < cloudCells.size(); i++) {
            schoolBoard.addStudentToEntrance(cloudCells.get(i).getColorIndex());
            cloudCells.remove(cloudCells.get(i));
        }
    }

    public void addStudentToCloud(Color color){
        cloudCells.add(color);
    }
}

