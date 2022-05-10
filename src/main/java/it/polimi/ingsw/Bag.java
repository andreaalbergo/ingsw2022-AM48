package it.polimi.ingsw;

import java.util.Random;

public class Bag {

    private final int[] students; //BARB: for now I think it's good a simple int array
    //private Board board;
    private final int numberOfPlayers;

    public Bag(int numberOfPlayers) {
        students = new int[5];
        for (int i = 0; i < 5; i++) {
            students[i] = 24;
        }
        //this.board = board;
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getRandomColorFromBag() {
        int colorIndex;
        Random random = new Random();

        while (true){
            colorIndex = random.nextInt(0,5);
            if(this.students[colorIndex] > 0){
                this.students[colorIndex]--;
                return colorIndex;
            }
        }
    }
    //try a more efficient way of random, like handling when a colorIndex goes to zero
    //I found the solution to problem of exception, if it's 2 players 20 turns, if 3 or 4 12 turns
    //I moved extractPawnsToCloud in BoardManager

    /**
     * This method is used to setup the entrance for the Schoolboards, extracting the correct number of students at the
     * start of the game
     *
     * @param schoolBoard parameter indicates which one of the SchoolBoard needs to be filled
     *
     */
    public void setupSchoolEntrance(SchoolBoard schoolBoard) {
        int randomColor;
        if(numberOfPlayers == 3){
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

    /**
     * This method checks if the Bag is empty, may be the case, the game is over.
     *
     * @return a boolean signaling if the Bag is emtpty(1) or not;
     */
    public boolean isBagEmpty(){
        for (int i = 0; i < 5; i++) {
           if (students[i]!=0)
               return false;
        }
        return true;
    }

    /**
     * Used to have a reference to students still present in the Bag
     *
     * @return the studenst array
     */
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
    getRandomCOLOR FROM BAG
     */

}


