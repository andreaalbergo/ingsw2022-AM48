package it.polimi.ingsw;

import java.util.List;

//CLASS MADE BY BARB, version 0.1-->...
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
        //this.students = setFirstRandomStudentOnIsland();
        //Waiting for the method implemented in class Bag
    }

    public List<Integer> getStudents() {
        return students;
    }

    public void buildTowerOnIsland(){
        //motherNature.getPosition();
        //checkPlayerProfessors
        //chekcIslandInfluence
        //addTower
        //newCheckMergingIslands
    }

    private void mergeTwoIslands() {
        //TODO
        //do it in buildTowerOnIsland() because I want to check the two adjacent islands after putting a tower
    }
}
