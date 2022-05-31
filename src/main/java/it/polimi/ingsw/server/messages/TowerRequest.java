package it.polimi.ingsw.server.messages;

public class TowerRequest implements Answer{

    private final String tower;

    private final String message;

    public TowerRequest(String tower, String message) {
        this.tower = tower;
        this.message = message;
    }

    public TowerRequest(String message) {
        this.tower = null;
        this.message = message;
    }

    public String getTower() {
        return tower;
    }

    @Override
    public Object getMessage() {
        return tower;
    }
}
