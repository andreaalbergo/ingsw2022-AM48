package it.polimi.ingsw.client.gameBoard;

import it.polimi.ingsw.costanti.Constants;
import it.polimi.ingsw.model.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class SchoolGrid {
    private final int tower;
    private final int hallDimension;
    private final ArrayList<Color> hall = new ArrayList<>();
    private final HashMap<Color, Integer> diningRoom= new LinkedHashMap<>();
    private final ArrayList<String> professorsTable = new ArrayList<>();

    public SchoolGrid(int numberOfPlayers) {
        if (numberOfPlayers==Constants.MAX_PLAYERS) {
            tower = Constants.TOWERS_MAX_PLAYERS;
            hallDimension = Constants.HALL_MAX_PLAYERS;
        } else {
            tower = Constants.TOWERS_MIN_PLAYERS;
            hallDimension = Constants.HALL_MIN_PLAYERS;
        }

        for (int i = 0; i < Constants.NUMBER_OF_COLOR_TYPES; i++) {
            diningRoom.put(Color.colorFromIndex(i), 0);
        }
    }

    public void updateHall(int newValue){

    }

    public void updateDining(int newValue){

    }

    public void updateProfessor(Color color, boolean removeProfessor) {

    }

    public void updateTowerContainer(int newValue) {

    }

}
