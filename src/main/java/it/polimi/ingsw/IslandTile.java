package it.polimi.ingsw;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class IslandTile {
    private static final List<Integer> colorsForSetup = Arrays.asList(0, 0, 1, 1, 2, 2, 3, 3, 4, 4);
    private int archipelagoDimension;
    private String ownerNickname;
    private final int[] students;

    public IslandTile() {
        Random random = new Random();
        int randomIndex = random.nextInt(colorsForSetup.size());
        int randomStudent = colorsForSetup.get(randomIndex);
        colorsForSetup.remove(randomIndex);

        this.archipelagoDimension = 1;
        this.ownerNickname = null;
        this.students = new int[5];
        this.students[randomStudent]++;

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

