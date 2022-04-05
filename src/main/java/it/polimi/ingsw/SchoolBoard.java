package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.Arrays;

public class SchoolBoard {
    private final String nickname;
    private int[] entrance;
    private int[] diningRoom = new int[5];
    private Boolean[] professors = new Boolean[5];
    private int towers;
    private ArrayList<Integer> collectedCoins;
    private int movedstudents;

    public SchoolBoard(String nickname, int numberOfPlayers, boolean mode){
        this.nickname = nickname;
        if (numberOfPlayers == 3){
            this.entrance = new int[7];
            this.towers = 6;
        }else{
            this.entrance = new int[9];
            this.towers = 8;
        }

        if(mode){
            this.collectedCoins = new ArrayList<>(5);
        }else{
            this.collectedCoins = new ArrayList<>(1);
            collectedCoins.set(0, 0);
        }


    }


    // probably going to implement a method that throws an exception in case the color is not present in the entrance
    public void addStudentToDiningRoom(Color color){
        switch (color){
            case RED_DRAGONS: {
                diningRoom[color.getIndex()] += 1;
                entrance[color.getIndex()] -= 1;
            }
            break;
            case GREEN_FROGS: {
                diningRoom[color.getIndex()] += 1;
                entrance[color.getIndex()] -= 1;
            }
            break;
            case PINK_FAIRIES: {
                diningRoom[color.getIndex()] += 1;
                entrance[color.getIndex()] -= 1;
            }
            break;
            case BLUE_UNICORNS: {
                diningRoom[color.getIndex()] += 1;
                entrance[color.getIndex()] -= 1;
            }
            break;
            case YELLOW_GNOMES: {
                diningRoom[color.getIndex()] += 1;
                entrance[color.getIndex()] -= 1;
            }
            break;

        }
    }

    public String getNickname() {
        return nickname;
    }

    public boolean checkProfessor(Color color) {
        return professors[color.getIndex()];
    }

    public void addProfessor(Color color){
        professors[color.getIndex()] = true ;
    }

    public void removeProfessor(Color color){
        professors[color.getIndex()] = false ;
    }

    public Boolean[] getProfessors(){
        return this.professors;
    }

    public int[] getDiningRoom(){
        return this.diningRoom;
    }

    public void addStudentToEntrance(int index) {
        this.entrance[index]+=1;
    }


    public int[] getEntrance(){
        return this.entrance;
    }


/*
    public void addStudentToIsland(Color color, Island island){
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

/*
    public void addTowerToIsland(Island island, String nickname) throws Exception{
        if(this.towers > 1){
            this.towers--;
        }else if(this.towers == 1){
            this.towers = 0;
            throw new Exception("Last Tower, check if the Game is ");
        }


    }

*/

}
