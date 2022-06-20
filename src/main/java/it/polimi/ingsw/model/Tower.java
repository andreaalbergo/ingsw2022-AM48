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
    WHITE, BLACK, GREY;

    public static final List<Tower> available = new ArrayList<>();

    /** Method setLists empties the alreadyPicked list. */
    public static void clear() {
        available.add(0, WHITE);
        available.add(1, GREY);
        available.add(2, BLACK);
    }

    public static List<Tower> available() {
        return available;
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

