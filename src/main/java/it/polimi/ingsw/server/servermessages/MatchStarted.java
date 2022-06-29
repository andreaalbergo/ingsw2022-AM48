package it.polimi.ingsw.server.servermessages;

import it.polimi.ingsw.model.Cloud;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.IslandTile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchStarted implements Answer{

        private final List<Cloud> clouds;

        private final List<IslandTile> islands;


    private final List<Color> entrance;

        public MatchStarted(List<Cloud> cloudList, List<IslandTile> islandTiles, List<Color> entrance){
            clouds = cloudList;
            islands = islandTiles;
            this.entrance = entrance;
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
