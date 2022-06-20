package it.polimi.ingsw.server.servermessages;

import java.util.HashMap;
import java.util.Map;

public class MatchStarted implements Answer{

    private final Map<String,String> playerMapTower;

    private final Map<String,String> playermapWizard;

    public MatchStarted(){
        playerMapTower = new HashMap<>();
        playermapWizard = new HashMap<>();
    }

    public void setPlayerMapTower(String player, String tower){
        playerMapTower.put(player, tower);
    }

    public void setPlayerMapWizard(String player, String wizard){
        playerMapTower.put(player, wizard);
    }

    public Map<String, String> getPlayermapWizard() {
        return playermapWizard;
    }

    public Map<String, String> getPlayerMapTower() {
        return playerMapTower;
    }

    @Override
    public Object getMessage() {
        return null;
    }
}
