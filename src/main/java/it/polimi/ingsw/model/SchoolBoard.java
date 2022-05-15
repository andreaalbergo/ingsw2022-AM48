package it.polimi.ingsw.model;

import java.util.ArrayList;

public class SchoolBoard {
    private final String nickname;
    private int entrance_size;
    private ArrayList<Color> entrance;
    private int[] diningRoom = new int[5];
    private Boolean[] professors = new Boolean[5];
    private int towers;
    private ArrayList<Integer> collectedCoins;
    private int movedstudents =0;
    private int numberOfPlayers;
    private boolean mode;

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public boolean isMode() {
        return mode;
    }

    public SchoolBoard(String nickname, int numberOfPlayers, boolean mode){
        this.nickname = nickname;
        this.numberOfPlayers = numberOfPlayers;
        this.mode = mode;
        for (Boolean professor:
             professors) {
            professor = false;
        }

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

    /**
     * This method adds a student to its designated dining roomm
     *
     * @param color is the color of the student the player wants to move to the dining room
     */
    public void addStudentToDiningRoom(Color color){
        if((movedstudents == 4 && numberOfPlayers==2)|| (movedstudents == 3 && numberOfPlayers==3))
            System.out.println("\nYou already moved the maximum number of students\n");
        else {
            diningRoom[color.getColorIndex()] += 1;
            removeFromEntrance(color);

            switch (numberOfPlayers) {
                case 2:
                    if (movedstudents < 4) {
                        movedstudents++;
                    }

                case 3:
                    if (movedstudents < 3) {
                        movedstudents++;
                    }

            }
        }
    }

    public String getNickname() {
        return nickname;
    }

    /**
     * This method is used to check if the player owns the professor for the given color
     *
     * @param color the color of the Professor that needs to be checked
     * @return a boolean saying if the Player owns or not the professor for the parameter's color
     */
    public boolean checkProfessor(Color color) {
        return professors[color.getColorIndex()];
    }

    /**
     * This method assigns the professor to the player
     *
     * @param color gives the method the specification to which professor is newly assigned to the player
     */
    public void addProfessor(Color color){
        professors[color.getColorIndex()] = true ;
    }

    /**
     * This method assigns the professor to the player
     *
     * @param indexColor gives the method the specification to which professor is newly assigned to the player, similar to the
     *                   one above but uses an Integer instead of Color
     */
    public void addProfessor(int indexColor) { professors[indexColor] = true; }

    /**
     * The method is used to deprive the student of the professor after he doesn't have enough influence
     *
     * @param color gives the method the specification to which professor has to be removed from the player
     */
    public void removeProfessor(Color color){
        professors[color.getColorIndex()] = false ;
    }

    /**
     * The method is used to deprive the student of the professor after he doesn't have enough influence
     *
     * @param indexColor gives the method the specification to which professor has to be removed from the player, using
     *                   an Integer(indicating the Color's index) instead of a Color
     */
    public void removeProfessor(int indexColor) { professors[indexColor] = false;}

    public Boolean[] getProfessors(){
        return this.professors;
    }

    public int[] getDiningRoom(){
        return this.diningRoom;
    }

    /**
     * this method adds a student to the Player's entrance
     *
     * @param index of the color extracted or chosen to be added to
     */
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

    public void addStudentToIsland(Color color, IslandTile islandTile) {
        removeFromEntrance(color);
        islandTile.getStudents()[color.getColorIndex()]++;


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
    public void addStudentToIsland(Color color, IslandTile islandTile){
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
