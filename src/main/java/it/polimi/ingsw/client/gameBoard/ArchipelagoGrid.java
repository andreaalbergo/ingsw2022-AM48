package it.polimi.ingsw.client.gameBoard;


import java.util.*;

/**
 * Archipelago grid is the grid of the 12 island that keeps track of every data useful to create and print the
 * archipelago scheme from the Blueprint class.
 *
 * @author David Barb
 */
public class ArchipelagoGrid {
    /**
     * HashMap of key Integer of islandID and array of int with dimension 8:
     * cell 0-> islandID (useful when it is merged with island with lowerID);
     * cell 1-> mother nature presence (if present set int from 0 to 1);
     * cell 2-> towerID (1 for white, 2 for black, 3 for grey and 0 for empty tower cell);
     * cell 3-> archipelagoDimension (starts from value 1);
     * cell 4-> the amount of COLOR GREEN_FROGS on the island;
     * cell 5-> the amount of COLOR RED_DRAGONS on the island;
     * cell 6-> the amount of COLOR YELLOW_GNOMES on the island;
     * cell 7-> the amount of COLOR PINK_FAIRIES on the island;
     * cell 8->the amount of COLOR BLUE_UNICORNS on the island.
     */
    private final HashMap<Integer, int[]> archipelagoConfiguration = new LinkedHashMap<>();

    /**
     * Archipelago constructor for handling every single player move made on island that changes the game board.
     */
    public ArchipelagoGrid() {
        for (int i = 0; i < 12; i++) {
            archipelagoConfiguration.put(i, new int[9]);
            if (i == 0)
                archipelagoConfiguration.get(i)[1] = 1;

            archipelagoConfiguration.get(i)[0] = i+1;
            archipelagoConfiguration.get(i)[3] = 1;
        }
    }

    /**
     * Method getArchipelagoConfiguration is to get the hashmap of the islands.
     *
     * @return of type HashMap<> - the collection of islands.
     */
    public HashMap<Integer, int[]> getConfiguration() {
        return archipelagoConfiguration;
    }

    /**
     * Method updateIsland is used to keep track of every player's move, see more on class GameBoard.
     *
     * @param islandID of type int - the ID of the island.
     * @param typeToChange of type int - the type of integer to handle.
     * @param newValue - the new value to set on the HashMap given the key and index of array.
     */
    public void updateIsland(int islandID, int typeToChange, int newValue) {
        archipelagoConfiguration.get(islandID)[typeToChange] = newValue;
    }
}
