package it.polimi.ingsw.client.gameBoard;

import it.polimi.ingsw.costanti.Constants;
import it.polimi.ingsw.model.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * SchoolGrid class is used by class GameBoard to update and print the CLI view of all the school boards.
 *
 * @author David Barb
 */
public class SchoolGrid {
    private int towers;
    private final int towerColor;
    private List<Color> entrance = new ArrayList<>();
    private int[] diningRoom= new int[5];
    private boolean[] professors = new boolean[5];

    /**
     * SchoolGrid is the school constructor.
     *
     * @param numberOfPlayers of type int - the number of players.
     * @param towerColor of type int - the tower color.
     */
    public SchoolGrid(int numberOfPlayers, int towerColor) {
        this.towerColor = towerColor;
        if (numberOfPlayers==Constants.MAX_PLAYERS)
            towers = Constants.TOWERS_MAX_PLAYERS;
        else
            towers = Constants.TOWERS_MIN_PLAYERS;

        for (int i = 0; i < Constants.NUMBER_OF_COLOR_TYPES; i++) {
            diningRoom[i] = 0;
            professors[i] = false;
        }
    }


    /**
     * Method setEntrance is used to update given school entrance for the CLI view.
     *
     * @param entrance of type List<> - the list of students present in the entrance.
     */
    public void setEntrance(List<Color> entrance) {
        this.entrance = entrance;
    }

    /**
     * Method setDining is used to update given school dining room for the CLI view.
     *
     * @param diningRoom of type int[] - the array of students.
     */
    public void setDining(int[] diningRoom) {
        this.diningRoom = diningRoom;
    }

    /**
     * Method setProfessors is used to update given school professor's table for the CLI view.
     *
     * @param professors of type boolean[] - the array of owned professors.
     */
    public void setProfessors(boolean[] professors) {
        this.professors = professors;
    }

    /**
     * Method updateTowerCell is used to update given school tower container by decreasing its counter.
     */
    public void updateTowerCells() {
        towers--;
    }

    public List<Color> getEntrance() {
        return entrance;
    }

    public int[] getDiningRoom() {
        return diningRoom;
    }

    public boolean checkProfessor(Color color) {
        return professors[color.getColorIndex()];
    }

    public int getTowers() {
        return towers;
    }
}
