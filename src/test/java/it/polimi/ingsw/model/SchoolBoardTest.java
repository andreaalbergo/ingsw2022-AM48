package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.InvalidSelection;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.SchoolBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SchoolBoardTest {

    SchoolBoard schoolBoard;

    @BeforeEach
    void initialize(){
        schoolBoard = new SchoolBoard("Andrea", 1, false);
    }

    /**
     * checks that students are added to the Dining Room correctly
     * @throws InvalidSelection if there isn't the student in the entrance
     */
    @Test
    void addStudentToDiningRoom1() throws InvalidSelection {
        int [] diningRoom;
        diningRoom = schoolBoard.getDiningRoom();
        ArrayList<Color> entrance = schoolBoard.getEntrance();
        schoolBoard.addStudentToEntrance(Color.RED_DRAGONS.getColorIndex());
        schoolBoard.addStudentToEntrance(Color.RED_DRAGONS.getColorIndex());
        /*
        for (int i = 0; i < 5; i++) {
            System.out.println(diningRoom[i]);
        }
         */
        schoolBoard.addStudentToDiningRoom(Color.GREEN_FROGS);
        /*
        for (int i = 0; i < 5; i++) {
            System.out.println(diningRoom[i]);
        }
         */
        assertEquals(1,diningRoom[Color.RED_DRAGONS.getColorIndex()]);
    }

    /**
     * checks that students are added to the Dining Room correctly
     * @throws InvalidSelection if there isn't the student in the entrance
     */
    @Test
    void addStudentToDiningRoom2() throws InvalidSelection {
        int[] diningRoom;
        diningRoom = schoolBoard.getDiningRoom();
        ArrayList<Color> entrance = schoolBoard.getEntrance();
        schoolBoard.addStudentToEntrance(Color.RED_DRAGONS.getColorIndex());
        schoolBoard.addStudentToEntrance(Color.RED_DRAGONS.getColorIndex());
        schoolBoard.addStudentToDiningRoom(Color.RED_DRAGONS);
        schoolBoard.addStudentToDiningRoom(Color.RED_DRAGONS);

        assertEquals(2, diningRoom[Color.RED_DRAGONS.getColorIndex()]);
    }

    /**
     * checks that the nickname of the schoolboard is the same of the player
     */
    @Test
    void getNickname() {
        assertEquals("Andrea", schoolBoard.getNickname());
    }

    /**
     * checks if a professor is added correctly
     */
    @Test
    void checkProfessors() {
        Boolean[] professor;
        schoolBoard.addProfessor(Color.RED_DRAGONS);
        professor = schoolBoard.getProfessors();

        assertTrue(professor[Color.RED_DRAGONS.getColorIndex()]);
    }

    /**
     * checks if a professor is added correctly
     */
    @Test
    void addProfessor() {
        Boolean[] professor;
        schoolBoard.addProfessor(Color.PINK_FAIRIES);

        assertTrue(schoolBoard.checkProfessor(Color.PINK_FAIRIES));
    }

    /**
     * checks if a professor is added correctly
     */
    @Test
    void addProfessor1() {
        schoolBoard.addProfessor(Color.RED_DRAGONS);

        assertTrue(schoolBoard.checkProfessor(Color.RED_DRAGONS));
    }

    /**
     * checks if a professor is added correctly
     */
    @Test
    void addProfessor2() {
        schoolBoard.addProfessor(Color.PINK_FAIRIES);

        assertTrue(schoolBoard.checkProfessor(Color.PINK_FAIRIES));
    }

    /**
     * checks if a professor is removed correctly
     */
    @Test
    void removeProfessor1(){
        schoolBoard.addProfessor(Color.RED_DRAGONS);
        schoolBoard.removeProfessor(Color.RED_DRAGONS);

        assertFalse(schoolBoard.checkProfessor(Color.RED_DRAGONS));
    }

    /**
     * checks if a professor is removed correctly
     */
    @Test
    void removeProfessor2(){
        schoolBoard.addProfessor(Color.PINK_FAIRIES);
        schoolBoard.removeProfessor(Color.PINK_FAIRIES);

        assertFalse(schoolBoard.checkProfessor(Color.PINK_FAIRIES));
    }

    /**
     * checks if a student is added to the entrance correctly
     */
    @Test
    void addStudentToEntrance1(){
        System.out.println(Color.colorFromIndex(2));
        int[] size = {0, 0, 0, 0, 0};
        schoolBoard.addStudentToEntrance(2);
        //0, 0, 1, 0, 0
        ArrayList<Color> entrance = schoolBoard.getEntrance();
        schoolBoard.addStudentToEntrance(2);
        //0, 0, 2, 0, 0
        schoolBoard.addStudentToEntrance(3);
        //0, 0, 2, 1, 0

        for(Color c: entrance){
            size[c.getColorIndex()]++;
        }
        assertEquals(2,size[2]);
    }

    /**
     * checks if a student is added to the entrance correctly
     */
    @Test
    void addStudentToEntrance2() {
        System.out.println(Color.colorFromIndex(2));
        int[] size = {0, 0, 0, 0, 0};
        schoolBoard.addStudentToEntrance(2);
        //0, 0, 1, 0, 0
        ArrayList<Color> entrance = schoolBoard.getEntrance();
        schoolBoard.addStudentToEntrance(2);
        //0, 0, 2, 0, 0
        schoolBoard.addStudentToEntrance(3);
        //0, 0, 2, 1, 0

        for (Color c : entrance) {
            size[c.getColorIndex()]++;
        }
        assertEquals(1,size[3]);
    }

    /**
     * checks that the entrance isn't null
     */
    @Test
    void getEntrance(){
        assertNotNull(schoolBoard.getEntrance());
    }

    /**
     * checks if a student is removed from the entrance correctly
     */
    @Test
    void removeFromEntrance1(){
        schoolBoard.addStudentToEntrance(3);
        ArrayList<Color> entrance = schoolBoard.getEntrance();

        assertTrue(entrance.contains(Color.colorFromIndex(3)));
    }

    /**
     * checks if a student is removed from the entrance correctly
     */
    @Test
    void removeFromEntrance2() throws InvalidSelection {
        schoolBoard.addStudentToEntrance(3);
        ArrayList<Color> entrance = schoolBoard.getEntrance();
        schoolBoard.addStudentToDiningRoom(Color.RED_DRAGONS);

        assertFalse(entrance.contains(Color.colorFromIndex(2)));
    }
}