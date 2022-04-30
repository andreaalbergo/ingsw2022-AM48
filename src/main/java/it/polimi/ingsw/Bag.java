package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.Random;


public class Bag {
    private static ArrayList<Integer> students;

    public Bag() {
        students = new ArrayList<>(Board.getNumberOfPlayers());
        for (int i = 0; i < 5; i++) {
            students.add(i, 24);
        }
    }

    public void extractPawnsToCloud(ArrayList<Cloud> clouds) {
        int random_number;
        Random random = new Random();
        for(Cloud c: clouds){
            random_number = random.nextInt(1,6);
            c.addStudentToCloud(random_number);
        }
    }

    //remember to throw exception in case no students of color picked is not present

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
    public void setupIslands(ArrayList<IslandTile> islands) {
        int number;
        for (IslandTile island : islands) {
            number = (int) ((Math.random() * 4.9));
            island.addStudentOnIsland(number);
        }
    }
    */


}


