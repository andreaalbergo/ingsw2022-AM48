package it.polimi.ingsw.client.gameBoard;

import it.polimi.ingsw.costanti.Constants;

import java.util.ArrayList;
import java.util.Hashtable;

public class Archipelago {
    private final Hashtable<Integer, ArrayList<String>> archipelagoGrid;

    public Archipelago() {
        archipelagoGrid = new Hashtable<>(Constants.NUMBER_OF_ISLANDS);
    }

    public Hashtable<Integer, ArrayList<String>> getArchipelagoGrid() {
        return archipelagoGrid;
    }

    public ArrayList<String> getSingleIsland(int key) {
        return archipelagoGrid.get(key);
    }

    //and other methods to update every island or merge them.
}
