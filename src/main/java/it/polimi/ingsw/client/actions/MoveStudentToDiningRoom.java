package it.polimi.ingsw.client.actions;

import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.IslandTile;

public class MoveStudentToDiningRoom implements UserCommand {
    private final Color color;

    public Color getColor() {
        return color;
    }

    public MoveStudentToDiningRoom(Color color) {
        this.color = color;
    }

    public String toString(){
        return "MoveStudentToDiningRoom";
    }
}
