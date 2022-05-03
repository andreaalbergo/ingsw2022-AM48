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

    public SchoolBoard(String nickname, int numberOfPlayers, boolean mode){
        this.nickname = nickname;
        if (numberOfPlayers == 3){
            this.entrance = new ArrayList<>(7);
            //entrance.add(0,null);
            this.towers = 6;
        }else{
            this.entrance = new ArrayList<>(9);
            //entrance.add(0,null);
            this.towers = 8;
        }

        if(mode){
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

    //BARB: prossimo metodo fatto da me, ma sistema il problema in linea 37 e linea 92 IMPORTANTE!
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
    /* BOZZ
        insert checkInfluence() [BOARDMANAGER] in addStudentToIsland()
        insert checkToAddProfessor() [BOARDMANAGER] in addStudentToDiningRoom()
     */

    /* BARB: RIFACCIO MEGLIO STO METODO, VEDI LINEA 106
    public void addStudentToIsland(Color color, Old_Island island){
        switch (color){
            case RED_DRAGONS: island.addStudentToIsland(RED_DRAGONS);
                break;
            case GREEN_FROGS: : island.addStudentToIsland(GREEN_FROGS);
                break;
            case YELLOW_GNOMES: : island.addStudentToIsland(YELLOW_GNOMES);
                break;
            case BLUE_UNICORNS: : island.addStudentToIsland(BLUE_UNICORNS);
                break;
            case PINK_FAIRIES: : island.addStudentToIsland(PINK_FAIRIES);
                break;

        }
    }
*/

/* BARB: NON E' NECESSARIO SAPERE SE HO UNA TORRE SU UN ISOLA; SE HO UN OWNER DELL'ISOLA, IN AUTOMATICO HO UNA TOWER
    public void addTowerToIsland(Island island, String nickname) throws Exception{
        if(this.towers > 1){
            this.towers--;
        }else if(this.towers == 1){
            this.towers = 0;
            throw new Exception("Last Tower, check if the Game is ");
        }
    }
*/

    public int getTowers() {
        return towers;
    }
/*
    public void decrementTowers() {
        this.towers--;
        if (towers==0)
            callGameOver();

    }
*/
    //BOZZ
    public boolean getSingleProfessor(Color color){
        return professors[color.getColorIndex()];
    }
}
