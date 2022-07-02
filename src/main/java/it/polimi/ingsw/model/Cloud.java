package it.polimi.ingsw.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Cloud class is an entity that has number of players instances and specific number of student cells that are filled
 * up every game's turn from the Bag class; then they are picked from every player during the same turn to fill
 * their own school entrances.
 *
 * @author David Barb
 */
public class Cloud implements Serializable{
    private final ArrayList<Color> cloudCells = new ArrayList<>();
    //private final Board board;

    /**
     * Constructor Cloud creates a new instance.
     */
    public Cloud(int i) {
        //this.board = board;
        iD = i;
    }

    private boolean empty = true;

    private final int iD;



    public int getiD() {
        return iD;
    }



    /**
     * Method getCloudCells is a getter.
     *
     * @return of type ArrayList<>.
     */
    public ArrayList<Color> getCloudCells() {
        return cloudCells;
    }

    /**
     * Method emptyCloud empties the cloud to specific schoolBoard.
     *
     * @param schoolBoard of type SchoolBoard.
     */
    public void emptyCloud(SchoolBoard schoolBoard){
        for(int i = 0; i < cloudCells.size(); i++) {
            schoolBoard.addStudentToEntrance(cloudCells.get(i).getColorIndex());
            cloudCells.remove(cloudCells.get(i));
        }
        setEmpty(true);
    }

    /**
     * Method addStudentToCloud adds a color to cloud.
     *
     * @param color of type Color.
     */
    public void addStudentToCloud(Color color){
        cloudCells.add(color);
        setEmpty(false);
    }
    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}

