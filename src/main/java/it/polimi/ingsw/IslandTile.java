package it.polimi.ingsw;

import java.util.List;

public class IslandTile {
    private static final int NUMBER_OF_SINGLE_ISLANDS = 12;
    private static int lastIslandId = 0;
    private final int idIsland;
    private String ownerNickname;
    private List<Integer> students;

    public IslandTile() {
        lastIslandId++;
        this.idIsland = lastIslandId;
        this.ownerNickname = null;
        this.students = setupFirstStudent();
    }

    private List<Integer> setupFirstStudent() {
        //TODO
        return students;
    }

    public void buildTowerOnIsland(){
        //TODO
        //getMotherNaturePosition
        //checkPlayerProfessors
        //chekcIslandInfluence
        //addTower
        //newCheckMergingIslands
    }

    public void moveStudentFromEntranceToIsland(int idIsland) {
        //TODO
    }

}
