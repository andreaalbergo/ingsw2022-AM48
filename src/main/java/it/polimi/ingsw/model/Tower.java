package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Tower class is an enumeration containing the three tower colors which a player can choose from the unpicked ones.
 * The grey tower is only available for a game of 3 players.
 *
 * @author David Barb, Andrea Albergo
 */
public enum Tower {
    WHITE(0), BLACK(1), GREY(2);

    private static final ArrayList<Tower> available = new ArrayList<>();
    private final int towerIndex;

    Tower(int towerIndex) {
        this.towerIndex = towerIndex;
    }

    /** Method setLists empties the alreadyPicked list. */
    public static void setList() {
        available().clear();
        available.add(WHITE);
        available.add( GREY);
        available.add( BLACK);
    }

    /**
     * Method available gets the list of available towers, useful for first setup before starting the game.
     *
     * @return of type List<> - the list of available towers
     */
    public static List<Tower> available() {
        return available;
    }

    /**
     * Method getIndex gives the number of chosen tower, useful for GameBoard class in order to print the CLI view.
     *
     * @return of type int - the ID of the tower.
     */
    public int getIndex(){
        return towerIndex;
    }

    /**
     * Method isAlreadyPicked is a getter.
     *
     * @param tower of type Tower.
     * @return of type boolean.
     */
    public static boolean isAlreadyPicked(Tower tower){
        return !(available.contains(tower));
    }

    /**
     * Method choose adds the chosen tower to the alreadyPicked list.
     *
     * @param tower of type Tower - the chosen tower.
     */
    public static void choose(Tower tower) {
        available.remove(tower);
    }

    /**
     * Method parseInput parses a type String input for tower choosing.
     *
     * @param input of type String - the chosen tower.
     * @return Wizard - the Enum value of the desired tower.
     */
    public static Tower parseInput(String input) {
        return Enum.valueOf(Tower.class, input.toUpperCase());
    }

}

