package it.polimi.ingsw.server.servermessages;

import it.polimi.ingsw.model.Cloud;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.IslandTile;

import java.util.List;

/**
 *  MatchStarted is one of the Server answers, used to signal the start of the Match
 *  and sets some of the information for the Gameboard
 */
public class MatchStarted implements Answer{

        private final List<Cloud> clouds;

        private final List<IslandTile> islands;

        private final List<Color> entrance;
        public String getName() {
            return name;
        }

        private final String name;

    /**
     * Constructor
     *
     * @param cloudList List
     * @param islandTiles List
     * @param entrance List
     * @param name String
     */


    public MatchStarted(List<Cloud> cloudList, List<IslandTile> islandTiles, List<Color> entrance, String name){
        clouds = cloudList;
        islands = islandTiles;
        this.entrance = entrance;
        this.name = name;
    }

        public List<Color> getEntrance() {
        return entrance;
    }



        public List<Cloud> getClouds() {
        return clouds;
    }

        public List<IslandTile> getIslands() {
        return islands;
    }

        @Override
        public Object getMessage() {
        return "UTILITY : MATCH STARTED";
    }
}
