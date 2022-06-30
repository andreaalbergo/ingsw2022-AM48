package it.polimi.ingsw.server.servermessages;

import it.polimi.ingsw.model.Cloud;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.IslandTile;

import java.util.List;

public class MatchStarted implements Answer{

        private final List<Cloud> clouds;

        private final List<IslandTile> islands;


    private final List<Color> entrance;

    public String getName() {
        return name;
    }

    private final String name;

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
