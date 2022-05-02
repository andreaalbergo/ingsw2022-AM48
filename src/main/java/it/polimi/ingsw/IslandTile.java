package it.polimi.ingsw;

import java.util.List;

public class IslandTile {
    private static int lastIslandID = 0;
    private int islandId;
    private int archipelagoDimension;
    private String ownerNickname;
    private final int[] students;

    public IslandTile() {
        lastIslandID++;
        this.islandId = lastIslandID;
        this.archipelagoDimension = 1;
        this.ownerNickname = null;
        this.students = new int[5];
    }

    public boolean isIslandWithoutTower() {
        return ownerNickname == null;
    }

    public String getIslandOwner() {
        return ownerNickname;
    }

    public int[] getStudents() {
        return students;
    }

    public void setIslandOwner(String nickname) {
        this.ownerNickname = nickname;
    }
    public int howManyTowers() {
        return this.archipelagoDimension;
    }

    public void mergeIslands(IslandTile currentIslandTile, IslandTile adjacentIslandTile) {
        addIslandToArchipelago();
        for (int i = 0; i < 5; i++) {
            currentIslandTile.students[i] = currentIslandTile.students[i] + adjacentIslandTile.students[i];
        }
    }

    private void addIslandToArchipelago() {
        this.archipelagoDimension++;
    }

}
