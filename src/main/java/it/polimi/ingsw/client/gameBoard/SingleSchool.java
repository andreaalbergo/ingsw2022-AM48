package it.polimi.ingsw.client.gameBoard;

import it.polimi.ingsw.costanti.Constants;

import java.util.ArrayList;

public class SingleSchool {
    private final int tower;
    private final int hallDimension;
    private final ArrayList<String> hall = new ArrayList<>();
    private final ArrayList<Integer>[] diningRoom = new ArrayList[Constants.NUMBER_OF_COLOR_TYPES];
    private final ArrayList<String> professorsTable = new ArrayList<>();

    public SingleSchool(int numberOfPlayers) {
        if (numberOfPlayers== Constants.MAX_PLAYERS) {
            tower = Constants.TOWERS_MAX_PLAYERS;
            hallDimension = Constants.HALL_MAX_PLAYERS;
        } else {
            tower = Constants.TOWERS_MIN_PLAYERS;
            hallDimension = Constants.HALL_MIN_PLAYERS;
        }
        for (int i = 0; i < Constants.NUMBER_OF_COLOR_TYPES; i++) {
            diningRoom[i] = new ArrayList<>();
        }
    }

    //some methods to update every grid of the school and also move methods
}
