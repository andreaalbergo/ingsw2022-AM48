package it.polimi.ingsw;

import java.util.Random;

public class Bag {

    private final int[] students; //BARB: for now I think it's good a simple int array
    private Board board;

    public Bag(Board board) {
        students = new int[5];
        for (int i = 0; i < 5; i++) {
            students[i] = 24;
        }
        this.board = board;
    }

    public int getRandomColorFromBag() {
        int colorIndex;
        Random random = new Random();

        while (true){
            colorIndex = random.nextInt(1,6);
            if(this.students[colorIndex] > 0){
                this.students[colorIndex]--;
                return colorIndex;
            }
        }
    }
    //try a more efficient way of random, like handling when a colorIndex goes to zero
    //I found the solution to problem of exception, if it's 2 players 20 turns, if 3 or 4 12 turns
    //I moved extractPawnsToCloud in BoardManager

    public void setupSchoolEntrance(SchoolBoard schoolBoard) {
        int randomColor;
        if(board.getNumberOfPlayers() == 3){
            for (int i = 0; i < 9; i++) {
                randomColor = this.getRandomColorFromBag();
                schoolBoard.addStudentToEntrance(randomColor);
            }
        }else{
            for (int i = 0; i < 7; i++) {
                randomColor = this.getRandomColorFromBag();
                schoolBoard.addStudentToEntrance(randomColor);
            }
        }
    }

    public int[] getStudents() {
        return students;
    }
    /*
    public void setupIslands(ArrayList<Old_Island> islands) {
        int number;
        for (Old_Island island : islands) {
            number = (int) ((Math.random() * 4.9));
            island.addStudentOnIsland(number);
        }
    }
    */

    /*BOZZ
    add extractSinglePawn() which returns the Color of the extracted pawn
     */


}


