package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.GameOverException;

import java.util.Random;

/**
 *
 */
public class Bag {
    private final int[] students;
    private final Board board;

    public Bag(Board board) {
        this.board = board;
        students = new int[5];
        for (int i = 0; i < 5; i++) {
            students[i] = 24;
        }
    }

    private boolean checkEmpty(){
        int count = 0;
        for(int i = 0; i < 5; i++){
            count = count + this.students[i];
        }
        if(count == 0){
            return true;
        }
        return false;
    }

    public int getRandomColorFromBag() throws GameOverException {
        int colorIndex;
        Random random = new Random();
        if(checkEmpty()){
            throw new GameOverException();
        }
        while (true){
            colorIndex = random.nextInt(0,5);
            if(this.students[colorIndex] > 0){
                this.students[colorIndex]--;
                return colorIndex;
            }
        }
    }
    //try a more efficient way of random, like handling when a colorIndex goes to zero

    /**
     * This method is used to setup the entrance for the Schoolboards, extracting the correct number of students at the
     * start of the game
     *
     * @param schoolBoard parameter indicates which one of the SchoolBoard needs to be filled
     *
     */
    public void setupSchoolEntrance(SchoolBoard schoolBoard) throws GameOverException {
        int randomColor;
        if(board.getActivePlayers().size()==3){
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
     * @return of type boolean - signaling if the Bag is empty or not;
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
     * @return of type int[] - the students array
     */
    public int[] getStudents() {
        return students;
    }
}


