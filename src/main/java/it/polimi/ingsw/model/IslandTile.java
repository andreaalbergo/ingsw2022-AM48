package it.polimi.ingsw.model;

/**
 * IslandTile class is the island object, which can contain a generic number of student discs, it can have an owner with
 * its tower color, it can be merged with other islands. There MUST BE 12 instances of this class at the beginning
 * of the game and setup all the islands in the correct way as the game rules say in the Setup Phase. When we have three
 * groups of islands we call game over.
 *
 * @author David Barb
 */
public class IslandTile {
    private int archipelagoDimension = 1;
    private int islandId;
    private Player islandOwner;
    private final int[] students = new int[5];

    /**
     * Constructor IslandTile creates a new IslandTile instance. If it's the first instance, it has mother nature pawn;
     * if it's the twelfth and last instance, the island is empty; the others have each one student disc, there MUST BE
     * 2 students for every color in total (picked randomly).
     */
    public IslandTile(int colorIndex, int islandId) {
        if (colorIndex!=-1)
            students[colorIndex]++;
        this.islandId = islandId;
    }

    /**
     * Method getIslandOwner that gets owner of given island. If Player is null, the island has no tower, so it is not
     * conquered yet.
     *
     * @return String - the string of the player that owns the island, with its tower color.
     */
    public Player getIslandOwner() {
        return islandOwner;
    }


    /**
     * Method getStudents gives us the array of student discs organized by colors, it shows how many of them are
     * present on given islandTile.
     *
     * @return int[] - array of student discs.
     */
    public int[] getStudents() {
        return students;
    }

    /**
     * Method getArchipelagoDimension gets the dimension of the archipelago formed with islands with the same ID.
     *
     * @return int - dimension of the archipelago.
     */
    public int getArchipelagoDimension() {
        return archipelagoDimension;
    }

    /**
     * Method setIslandOwner that sets new island owner, by taking reference of the player that conquered the island.
     *
     * @param islandOwner of type Player - new owner of the island.
     */
    public void setIslandOwner(Player islandOwner) {
        this.islandOwner = islandOwner;
    }

    /**
     * Method howManyTowers that shows how many towers are in the archipelago; one tower or none if single island is
     * conquered or not.
     *
     * @return int - the number of connected islands in given archipelago.
     */
    public int howManyTowers() {
        if (archipelagoDimension==1 && this.islandOwner==null)
            return 0;
        return this.archipelagoDimension;
    }

    /**
     * Method getIslandId gets the ID of the island.
     *
     * @return int - island ID.
     */
    public int getIslandId() {
        return islandId;
    }

    /**
     * Method setIslandId that sets new island ID.
     *
     * @param islandId of type int.
     */
    public void setIslandId(int islandId) {
        this.islandId = islandId;
    }

    /**
     * Method mergeIslands that takes the island where mother nature is positioned and merges with one of the two
     * adjacent island that has the same tower color as this one.
     *
     * @param adjacentIslandTile of type IslandTile - adjacent island.
     */
    public void mergeIslands(IslandTile adjacentIslandTile) {
        archipelagoDimension++;
        for (int i = 0; i < 5; i++) {
            students[i] = students[i] + adjacentIslandTile.students[i];
        }
    }

}

