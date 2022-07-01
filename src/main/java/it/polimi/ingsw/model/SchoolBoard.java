package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.InvalidSelection;

import java.io.Serializable;
import java.util.ArrayList;

public class SchoolBoard implements Serializable {
    private final String nickname;
    private final ArrayList<Color> entrance;
    private final int[] diningRoom = new int[5];
    private final Boolean[] professors = new Boolean[5];
    private int towers;
    private int movedStudents = 0;
    private final int numberOfPlayers;
    private final boolean mode;

    /**
     * Method setTowers is a setter.
     *
     * @param tower of type int.
     */
    public void setTowers(int tower) {
        this.towers = tower;
    }

    /**
     * Method getMovedStudents is a getter.
     *
     * @return of type int.
     */
    public int getMovedStudents() {
        return movedStudents;
    }

    /**
     * Method setMovedStudents is a setter.
     *
     * @param movedStudents of int.
     */
    public void setMovedStudents(int movedStudents) {
        this.movedStudents = movedStudents;
    }

    /**
     * Method getNumberOfPlayers is a getter.
     *
     * @return of type int.
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * Method isMode is a getter.
     *
     * @return of type boolean.
     */
    public boolean isMode() {
        return mode;
    }

    /**
     * Constructor SchoolBoard to create its instance.
     *
     * @param nickname of type String.
     * @param numberOfPlayers of type int.
     * @param mode of type boolean.
     */
    public SchoolBoard(String nickname, int numberOfPlayers, boolean mode){
        this.nickname = nickname;
        this.numberOfPlayers = numberOfPlayers;
        this.mode = mode;
        for (int i = 0; i < 5; i++) {
            professors[i] = false;
        }

        if (numberOfPlayers == 3){
            this.entrance = new ArrayList<>(9);
            //entrance.add(0,null);
            this.towers = 6;
        }else{
            this.entrance = new ArrayList<>(7);
            //entrance.add(0,null);
            this.towers = 8;
        }

        ArrayList<Integer> collectedCoins;
        if(mode){
            collectedCoins = new ArrayList<>(5);
        }else{
            collectedCoins = new ArrayList<>(1);
            collectedCoins.add(0, 0);
        }

    }

    /**
     * This method adds a student to its designated dining room.
     *
     * @param color is the color of the student the player wants to move to the dining room
     */
    public void addStudentToDiningRoom(Color color) throws InvalidSelection{
        if((movedStudents == 4 && numberOfPlayers==3)|| (movedStudents == 3 && numberOfPlayers==2))
            //System.out.println("\nYou already moved the maximum number of students\n");
            throw new InvalidSelection();
        else {
            diningRoom[color.getColorIndex()] += 1;
            removeFromEntrance(color);

            if(numberOfPlayers == 2 && movedstudents < 3){
                movedstudents++;
            } else{
                if(numberOfPlayers == 3 && movedstudents < 4){
                    movedstudents++;
                }
            }
            /*
            switch (numberOfPlayers) {
                case 2: {
                    if (movedStudents < 3) {
                        movedStudents++;
                    }
                }

                case 3: {
                    if (movedStudents < 4) {
                        movedStudents++;
                    }
                }

            }
             */
        }
    }

    /**
     * Method getNickname is a getter.
     *
     * @return of type String.
     */
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

    /**
     * Method getProfessors is a getter.
     *
     * @return of type Boolean[].
     */
    public Boolean[] getProfessors(){
        return this.professors;
    }

    /**
     * Method getDiningRoom is a getter.
     *
     * @return of type int[].
     */
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

    /**
     * Method getEntrance is a getter.
     *
     * @return of type ArrayList<> - the list of entrance hall.
     */
    public ArrayList<Color> getEntrance(){
        return this.entrance;
    }

    /**
     * Method removeFromEntrance is a method that removes a given student from entrance.
     *
     * @param color of type Color.
     * @throws InvalidSelection when input is wrong.
     */
    private void removeFromEntrance(Color color) throws InvalidSelection {
        if(!entrance.contains(color)){
            throw new InvalidSelection();
        }
        entrance.remove(color);
        //movedStudents++;
    }

    /**
     * Method addStudentToIsland adds selected student to given island.
     *
     * @param color of type Color.
     * @param islandTile of type IslandTile.
     * @throws InvalidSelection when moved students surpasses given limit.
     */
    public void addStudentToIsland(Color color, IslandTile islandTile) throws InvalidSelection {
        if((movedStudents == 4 && numberOfPlayers==3)|| (movedStudents == 3 && numberOfPlayers==2))
            //System.out.println("\nYou already moved the maximum number of students\n");
            throw new InvalidSelection();
        switch (numberOfPlayers) {
            case 2:
                if (movedStudents < 3) {
                    movedStudents++;
                }

            case 3:
                if (movedStudents < 4) {
                    movedStudents++;
                }

        }
        removeFromEntrance(color);
        islandTile.getStudents()[color.getColorIndex()]++;
    }

    /**
     * Method getTowers is a getter.
     *
     * @return of type int.
     */
    public int getTowers() {
        return towers;
    }
}
