package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.GameOverException;

import java.io.Serializable;
import java.util.Random;

/**
 * Bag is a container that extracts students to the clouds and school boards and signals when it's empty so the game
 * finally ends.
 *
 *@author Andrea Albergo
 */
public class Bag implements Serializable {
    private final int[] students;
    private final Board board;

    /**
     * Constructor Bag creates its instance.
     *
     * @param board of type Board.
     */
    public Bag(Board board) {
        this.board = board;
        students = new int[5];
        for (int i = 0; i < 5; i++) {
            students[i] = 26;
        }
    }

    /**
     * Method checkEmpty checks if bag is empty.
     *
     * @return of type boolean.
     */
    private boolean checkEmpty(){
        int count = 0;
        for(int i = 0; i < 5; i++){
            count = count + this.students[i];
        }
        return count == 0;
    }

    /**
     * Method getRandomColorFromBag pick a student randomly.
     *
     * @return of type int.
     * @throws GameOverException when bag is empty.
     */
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

    /**
     * This method is used to set up the entrance for the SchoolBoards, extracting the correct number of students at the
     * start of the game
     *
     * @param schoolBoard parameter indicates which one of the SchoolBoard needs to be filled
     *
     */
    public void setupSchoolEntrance(SchoolBoard schoolBoard) throws GameOverException {
        int randomColor;
        if(board.getActivePlayers().size()==3){
            for (int i = 0; i < 9; i++) {
                randomColor = getRandomColorFromBag();
                schoolBoard.addStudentToEntrance(randomColor);
            }
        }else{
            for (int i = 0; i < 7; i++) {
                randomColor = getRandomColorFromBag();
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


