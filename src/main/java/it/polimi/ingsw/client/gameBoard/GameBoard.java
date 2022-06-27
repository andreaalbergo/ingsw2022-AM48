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

    public GameBoard(int numberOfPlayers) {
        archipelago = new Archipelago();
        clouds = new CloudGrid(numberOfPlayers);

        for (int i = 0; i < numberOfPlayers; i++) {
            schools.add(new SingleSchool(numberOfPlayers));
        }
    }


}
