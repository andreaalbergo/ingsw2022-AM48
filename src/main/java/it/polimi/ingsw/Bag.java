package it.polimi.ingsw;

import java.util.ArrayList;


public class Bag {
    private ArrayList<Integer> students;

    public Bag() {
        this.students = new ArrayList<>(Board.getNumberOfPlayers());
        for (int i = 0; i < 5; i++) {
            this.students.add(i, 24);
        }
    }

    public static void drawStudentsToCloudTile() {
        //TODO
    }

    public void drawStudentsToSchoolEntrance(SchoolBoard schoolBoard) {
        int number;
        if(Board.getNumberOfPlayers() == 2 /* || numberOfPlayers == 4*/){
            for (int i = 0; i < 4; i++) {
                number = (int) ((Math.random()*4.9));
                schoolBoard.addStudentToEntrance(number);
            }
        }else{
                for (int i = 0; i < 3; i++) {
                    number = (int) ((Math.random()*4.9));
                    schoolBoard.addStudentToEntrance(number);
                }
        }
    }

    public void setupSchoolEntrance(SchoolBoard schoolBoard) {
        int number;
        if(Board.getNumberOfPlayers() == 2 /* || numberOfPlayers == 4*/){
            for (int i = 0; i < 7; i++) {
                number = (int) ((Math.random()*4.9));
                schoolBoard.addStudentToEntrance(number);
            }
        }else{
            for (int i = 0; i < 9; i++) {
                number = (int) ((Math.random()*4.9));
                schoolBoard.addStudentToEntrance(number);
            }
        }
    }

    public ArrayList<Integer> getStudents() {
        return students;
    }
/*
    public void setupIslands(List<IslandTile> Islands){

    }

 */

}


