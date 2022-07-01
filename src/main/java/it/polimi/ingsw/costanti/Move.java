package it.polimi.ingsw.costanti;

import it.polimi.ingsw.model.Cloud;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.IslandTile;

import java.io.Serializable;
import java.util.List;

/**
 * Move class is used to create the body of MoveMessage.
 */
public class Move implements Serializable {
    private final int[] diningRoom;
    private final Integer moved_students;
    private final List<Color> entrance;
    private final List<Cloud> cloudList;
    private final List<IslandTile> islandTiles;

    private final String id;

    /**
     * Method getDiningRoom is a getter.
     *
     * @return of type int[].
     */
    public int[] getDiningRoom() {
        return diningRoom;
    }

    /**
     * Method getMoved_students is a getter.
     *
     * @return of type Integer.
     */
    public Integer getMoved_students() {
        return moved_students;
    }

    /**
     * Method getEntrance is a getter.
     *
     * @return of type List<>.
     */
    public List<Color> getEntrance() {
        return entrance;
    }

    /**
     * Method getCloudList is a getter .
     *
     * @return of type List<>.
     */
    public List<Cloud> getCloudList() {
        return cloudList;
    }

    /**
     * Method getIslandTiles is a getter.
     *
     * @return of type List<>.
     */
    public List<IslandTile> getIslandTiles() {
        return islandTiles;
    }

    /**
     * Method getId is a getter.
     *
     * @return of type String.
     */
    public String getId() {
        return id;
    }

    /**
     * Constructor Move creates its instance.
     *
     * @param dining_room of type int[]
     * @param movedStudents of type Integer
     * @param entrance of type List<>.
     * @param cloudList of type List<>.
     * @param islandTiles of type List<>.
     * @param idMove of type String.
     */
    public Move(int[] dining_room, Integer movedStudents, List<Color> entrance, List<Cloud> cloudList, List<IslandTile> islandTiles, String idMove) {
        this.diningRoom = dining_room;
        this.moved_students = movedStudents;
        this.entrance = entrance;
        this.cloudList = cloudList;
        this.islandTiles = islandTiles;
        this.id = idMove;
    }
}
