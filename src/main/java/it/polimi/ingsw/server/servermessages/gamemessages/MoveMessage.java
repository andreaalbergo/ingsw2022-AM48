package it.polimi.ingsw.server.servermessages.gamemessages;

import it.polimi.ingsw.costanti.Move;
import it.polimi.ingsw.model.Cloud;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.IslandTile;
import it.polimi.ingsw.server.servermessages.Answer;

import java.util.List;

public class MoveMessage implements Answer {

    private final Move message;

    //update dopo aver mosso uno studente nella dining room
    public MoveMessage(int moved_students, List<IslandTile> islandTiles, int[] dining_room, List<Color> entrance ,String id_move) {
        this.message = new Move(dining_room,moved_students,entrance,null,islandTiles, id_move);
    }
    //update dopo aver mosso uno studente su un isola
    public MoveMessage(int moved_students, List<IslandTile> islandTiles, List<Color> entrance, String id_move){
        this.message = new Move(null,moved_students,entrance,null,islandTiles, id_move);
    }
    //update dopo aver scelto nuvola
    public MoveMessage(List<Cloud> clouds, List<Color> entrance, String id_move){
        this.message = new Move(null,null,entrance,clouds,null, id_move);
    }

    @Override
    public Move getMessage() {
        return message;
    }
}
