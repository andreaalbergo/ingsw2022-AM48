package it.polimi.ingsw.client.actions;

import it.polimi.ingsw.model.Cloud;

public class EndTurn implements UserCommand{

    private final Cloud cloud;


    private final boolean lastplayer;

    public EndTurn(Cloud cloud) {
        this.cloud = cloud;
        this.lastplayer = false;
    }

    public EndTurn(Cloud cloud, boolean enabler){
        this.cloud = cloud;
        this.lastplayer = enabler;
    }

    public boolean isLastplayer() {
        return lastplayer;
    }

    public Cloud getCloud() {
        return cloud;
    }

    public String toString(){
        return "EndTurn";
    }
}
