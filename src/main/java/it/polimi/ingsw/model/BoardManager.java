package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * BoardManager class is the simple class without decoration of the game, which is used when "normal" mode of the
 * game is set. It handles the lists of islands, clouds. It also handles mother nature object that tells us which island
 * we need to watch in order to start the "conquering" step of the "action" phase of the current player's turn.
 *
 * @author Edoardo Bozzini, David Barb
 */
public class BoardManager {
    private final Board board;
    private final List<IslandTile> islands = new ArrayList<>();
    private final List<Cloud> clouds = new ArrayList<>();
    private final MotherNature motherNature = new MotherNature();
    private final Bag bag;

    /** Constructor BoardManager creates a new "normal" mode container of the pieces of the game board.
     *  It also creates twelve instances of islands with the proper setup following the game's rules, and a bag with
     *  26 students of each color.
     *
     * @param board of type Board.
     */
    public BoardManager(Board board) {
        this.board = board;
        bag = new Bag(board);
        List<Integer> list = Stream.of(0,0,1,1,2,2,3,3,4,4).collect(Collectors.toList());
        Collections.shuffle(list);

        for (int i = 0; i < 12; i++) {
            if(i==0 || i==11)
                islands.add(new IslandTile(-1, i));
            else
                islands.add(new IslandTile(list.get(i-1), i));
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

    /**
     *
     * @return clouds list reference
     */
    public List<Cloud> getClouds() {
        return clouds;
    }

    /**
     *
     * @return bag reference
     */
    public Bag getBag() {
        return bag;
    }

    public void createCloudList() {
        for (int i = 0; i < board.getActivePlayers().size(); i++) {
            clouds.add(new Cloud(board));
        }
    }

    public MotherNature getMotherNature() {
        return motherNature;
    }

    //checkInfluence will be in GameController package
    //same with checkToAddProfessors
    //drawFromBagToClouds I think will be in Board Class
    //extractPawnsToClouds need to be implemented for 3 or 4 times in base of number of players
}
