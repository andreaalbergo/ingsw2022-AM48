package it.polimi.ingsw;

//CLASS MADE BY BARB, version 0.1-->...
public class Old_Island {
    private static final int NUMBER_OF_SINGLE_ISLANDS = 12;
    private static int lastIslandId = 0;
    private final int idIsland;
    private String ownerNickname;
    private int[] students;
    //BOZZ private boolean[] noEntryTile = false;

    public Old_Island() {
        lastIslandId++;
        this.idIsland = lastIslandId;
        this.ownerNickname = null;
        //this.students = setFirstRandomStudentOnIsland();
        //Waiting for the method implemented in class Bag
    }

    public int[] getStudents() {
        return students;
    }

    public void buildTowerOnIsland(){
        //motherNature.getPosition();
        //checkPlayerProfessors
        //checkIslandInfluence
        //addTower
        //newCheckMergingIslands
    }

    private void mergeTwoIslands() {
        //TODO
        //do it in buildTowerOnIsland() because I want to check the two adjacent islands after putting a tower
    }

    public String getOwnerNickname() {
        return ownerNickname;
    }

    /*BOZZ
    public void setNoEntryTile(boolean noEntryTile, Old_Island island) {
        this.noEntryTile[getIdIsland(island)] = noEntryTile;
    }

    private void getNoEntryTile(Old_Island island){
        return noEntryTile[getIdIsland(island)];
    }

    add getIdIsland(Old_Island island){}
     */

}

