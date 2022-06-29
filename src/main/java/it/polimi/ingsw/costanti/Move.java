package it.polimi.ingsw.costanti;

import it.polimi.ingsw.model.Cloud;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.IslandTile;

import java.io.Serializable;
import java.util.List;

public class Move implements Serializable {
    private final int[] dinining_room;
    private final Integer moved_students;
    private final List<Color> entrance;
    private final List<Cloud> cloudList;
    private final List<IslandTile> islandTiles;

    private final String id;

    public int[] getDinining_room() {
        return dinining_room;
    }

    public Integer getMoved_students() {
        return moved_students;
    }

    public List<Color> getEntrance() {
        return entrance;
    }

    public List<Cloud> getCloudList() {
        return cloudList;
    }

    public List<IslandTile> getIslandTiles() {
        return islandTiles;
    }

    public String getId() {
        return id;
    }

    public Move(int[] dinining_room, Integer moved_students, List<Color> entrance, List<Cloud> cloudList, List<IslandTile> islandTiles, String idmove) {
        this.dinining_room = dinining_room;
        this.moved_students = moved_students;
        this.entrance = entrance;
        this.cloudList = cloudList;
        this.islandTiles = islandTiles;
        this.id = idmove;
    }
}
