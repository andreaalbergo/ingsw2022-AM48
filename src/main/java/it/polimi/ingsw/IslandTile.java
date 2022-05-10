package it.polimi.ingsw;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * IslandTile class is the island object, which can contain a generic number of student discs, it can have an owner with
 * its tower color, it can be merged with other islands, it can have mother nature pawn on it. There MUST BE 12
 * instances of this class at the beginning of the game and setup all the islands in the correct way as the game rules
 * say in the Setup Phase. When we have three groups of islands we call game over.
 *
 * @author David Barb
 */
public class IslandTile {
    private static int numberOfIslands = 0;
    private static final List<Integer> colorsForSetup = Arrays.asList(0, 0, 1, 1, 2, 2, 3, 3, 4, 4);
    private int archipelagoDimension = 1;
    private String ownerNickname;
    private final int[] students = new int[5];
    private boolean isMotherPresent;

    /**
     * Constructor IslandTile creates a new IslandTile instance. If it's the first instance, it has mother nature pawn;
     * if it's the twelfth and last instance, the island is empty; the others have each one student disc, there MUST BE
     * 2 students for every color in total (picked randomly).
     */
    public IslandTile() {
        if(numberOfIslands>0 && numberOfIslands!=11) {
            Random random = new Random();
            int randomIndex = random.nextInt(colorsForSetup.size());
            int randomStudent = colorsForSetup.get(randomIndex);
            //colorsForSetup.remove(randomIndex);
            this.students[randomStudent]++;
        } else if(numberOfIslands==0) {
            isMotherPresent = true;
        }
        numberOfIslands++;
    }

    /**
     * Method getIslandOwner that gets owner of given island. If string is null, the island has no tower, so it is not
     * conquered yet.
     *
     * @return String - the string of the player that owns the island, with its tower color.
     */
    public String getIslandOwner() {
        return ownerNickname;
    }

    /**
     * Method getColorStudent that gets the number of students of picked color present on given island.
     *
     * @param index of type int - index of specific student color.
     * @return int - the number of discs of given color index
     */
    public int getColorStudent(int index) {
        return students[index];
    }

    /**
     *
     * @return boolean - true if mother is present on the island.
     */
    public boolean isMotherPresent() {
        return isMotherPresent;
    }

    /**
     *
     * @param ownerNickname of type String - nickname of new owner of the island.
     */
    public void setIslandOwner(String ownerNickname) {
        this.ownerNickname = ownerNickname;
    }

    /**
     *
     * @return int - the number of connected islands in given archipelago.
     */
    public int howManyTowers() {
        if (archipelagoDimension==1 && this.ownerNickname==null)
            return 0;
        return this.archipelagoDimension;
    }

    /**
     *
     * @param currentIslandTile of type IslandTile - current island.
     * @param adjacentIslandTile of type IslandTile - adjacent island.
     */
    public void mergeIslands(IslandTile currentIslandTile, IslandTile adjacentIslandTile) {
        addIslandToArchipelago();
        for (int i = 0; i < 5; i++) {
            currentIslandTile.students[i] = currentIslandTile.students[i] + adjacentIslandTile.students[i];
        }
    }

    /**
     *
     */
    private void addIslandToArchipelago() {
        this.archipelagoDimension++;
    }

}

