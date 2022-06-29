package it.polimi.ingsw.client.gameBoard;

import java.util.ArrayList;

/**
 * GameBoard is the graphical representation of our game model, which consists of islands, clouds and players' school.
 *
 * @author David Barb
 */
public class GameBoard {
    private Archipelago archipelago;
    private CloudGrid clouds;
    private ArrayList<SingleSchool> schools;
    private final int number_of_Players;


    public GameBoard(int numberOfPlayers) {
        archipelago = new Archipelago();
        clouds = new CloudGrid(numberOfPlayers);

        for (int i = 0; i < numberOfPlayers; i++) {
            schools.add(new SingleSchool(numberOfPlayers));
        }
        this.number_of_Players= numberOfPlayers;
    }
    public Archipelago getArchipelago() {
        return archipelago;
    }

    public CloudGrid getClouds() {
        return clouds;
    }

    public ArrayList<SingleSchool> getSchools() {
        return schools;
    }

    public int getNumber_of_Players() {
        return number_of_Players;
    }

}
