package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * NormalBoardManager class is the simple class without decoration of the game, which is used when "normal" mode of the
 * game is set. It handles the lists of islands, clouds. It also handles mother nature object that tells us which island
 * we need to watch in order to start the "conquering" step of the "action" phase of the current player's turn.
 *
 * @author Edoardo Bozzini, David Barb
 */
public class NormalBoardManager {
    private final Board board;
    private final List<IslandTile> islands = new ArrayList<>();
    private final List<Cloud> clouds = new ArrayList<>();
    private final MotherNature motherNature = new MotherNature();

    /** Constructor NormalBoardManager creates a new "normal" mode container of the pieces of the game board. */
    public NormalBoardManager(Board board) {
        this.board = board;
        List<Integer> list = Stream.of(0,0,1,1,2,2,3,3,4,4).collect(Collectors.toList());
        Collections.shuffle(list);

        for (int i = 0; i < 12; i++) {
            if(i==0 || i==11)
                islands.add(new IslandTile(-1));
            else
                islands.add(new IslandTile(list.get(i-1)));
        }

    }

    /**
     * Method getIslands gives us back the list of IslandTiles.
     *
     * @return of type List - the list of islands.
     */
    public List<IslandTile> getIslands() {
        return islands;
    }
    //checkInfluence will be in Controller package
    //same with checkToAddProfessors
    //drawFromBagToClouds I think will be in Board Class
    //extractPawnsToClouds need to be implemented for 3 or 4 times in base of number of players
}
