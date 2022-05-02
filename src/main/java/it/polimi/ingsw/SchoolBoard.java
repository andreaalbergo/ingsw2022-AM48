package it.polimi.ingsw;

import java.util.ArrayList;

public class SchoolBoard {
    private final String nickname;
    private int entrance_size;
    private ArrayList<Color> entrance;
    private int[] diningRoom = new int[5];
    private Boolean[] professors = new Boolean[5];
    private int towers;
    private ArrayList<Integer> collectedCoins;
    private int movedstudents;

    public SchoolBoard(String nickname){
        this.nickname = nickname;
        if (Board.getNumberOfPlayers() == 3){
            this.entrance = new ArrayList<>(7);
            //entrance.add(0,null);
            this.towers = 6;
        }else{
            this.entrance = new ArrayList<>(9);
            //entrance.add(0,null);
            this.towers = 8;
        }

        if(Board.isExpertMode()){
            this.collectedCoins = new ArrayList<>(5);
        }else{
            this.collectedCoins = new ArrayList<>(1);
            collectedCoins.add(0, 0);
        }


    }

    public void addStudentToDiningRoom(Color color){
        diningRoom[color.getColorIndex()] += 1;
        removeFromEntrance(color);
    }

    public String getNickname() {
        return nickname;
    }

    public boolean checkProfessor(Color color) {
        return professors[color.getColorIndex()];
    }

    public void addProfessor(Color color){
        professors[color.getColorIndex()] = true ;
    }

    public void removeProfessor(Color color){
        professors[color.getColorIndex()] = false ;
    }

    public Boolean[] getProfessors(){
        return this.professors;
    }

    public int[] getDiningRoom(){
        return this.diningRoom;
    }

    public void addStudentToEntrance(int index) {
        this.entrance.add(Color.colorFromIndex(index));
    }

    public ArrayList<Color> getEntrance(){
        return this.entrance;
    }

    private void removeFromEntrance(Color color) {
        //check if color exists in entrance[], if not raise an exception
        entrance.remove(color);
    }

    public void addStudentToIsland(Color color, Old_Island oldIsland) {
        removeFromEntrance(color);
        oldIsland.getStudents()[color.getColorIndex()]++;

        /*BOZZ
        MotherNature motherNature = null;
        int position = motherNature.getPosition();
        if(!(oldIsland.getNoEntryTile(oldIsland) == true)){
            BoardManager.checkInfluence(oldIsland);
        }

         */
    }
    public int getTowers() {
        return towers;
    }

    public void decrementTowers() {
        this.towers--;
        /*TODO
        if (towers==0)
            callGameOver();
         */
    }
}
