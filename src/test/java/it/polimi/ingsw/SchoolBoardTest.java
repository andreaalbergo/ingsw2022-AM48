package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SchoolBoardTest {

    //BOZZ
    //added Board constructor, re-check this
    //Board board = new Board(1, true);
    SchoolBoard schoolBoard = new SchoolBoard("Andrea", 1, false);

    @Test
    void addStudentToDiningRoom() {
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
        schoolBoard.addStudentToDiningRoom(Color.RED_DRAGONS);
        /*
        for (int i = 0; i < 5; i++) {
            System.out.println(diningRoom[i]);
        }
         */
        assertEquals(1,diningRoom[Color.RED_DRAGONS.getColorIndex()]);
        schoolBoard.addStudentToDiningRoom(Color.RED_DRAGONS);
        assertEquals(2,diningRoom[Color.RED_DRAGONS.getColorIndex()]);

    }

    @Test
    void getNickname() {
        assertEquals("Andrea", schoolBoard.getNickname());
    }

    @Test
    void checkProfessors() {
        Boolean[] professor;
        schoolBoard.addProfessor(Color.RED_DRAGONS);
        professor = schoolBoard.getProfessors();
        assertTrue(professor[Color.RED_DRAGONS.getColorIndex()]);
    }

    @Test
    void addProfessor() {
        Boolean[] professor;
        schoolBoard.addProfessor(Color.PINK_FAIRIES);
        assertTrue(schoolBoard.checkProfessor(Color.PINK_FAIRIES));
    }

    @Test
    void removeProfessor() {
        Boolean[] professor;
        schoolBoard.addProfessor(Color.PINK_FAIRIES);
        schoolBoard.addProfessor(Color.RED_DRAGONS);
        assertTrue(schoolBoard.checkProfessor(Color.PINK_FAIRIES));
        assertTrue(schoolBoard.checkProfessor(Color.RED_DRAGONS));
        schoolBoard.removeProfessor(Color.PINK_FAIRIES);
        schoolBoard.removeProfessor(Color.RED_DRAGONS);
        assertFalse(schoolBoard.checkProfessor(Color.PINK_FAIRIES));
        assertFalse(schoolBoard.checkProfessor(Color.RED_DRAGONS));

    }

    @Test
    void addStudentToEntrance(){
        System.out.println(Color.colorFromIndex(2));
        int[] size = new int[5];
        schoolBoard.addStudentToEntrance(2);
        ArrayList<Color> entrance = schoolBoard.getEntrance();
        schoolBoard.addStudentToEntrance(2);
        schoolBoard.addStudentToEntrance(3);

        for(Color c: entrance){
            size[c.getColorIndex()]++;
        }
        assertEquals(2,size[2]);
        assertEquals(1,size[3]);
        schoolBoard.addStudentToDiningRoom(Color.GREEN_FROGS);

        for(Color c : entrance){
            size[c.getColorIndex()] = 0;
        }
        for(Color c: entrance){
            size[c.getColorIndex()]++;
        }
        assertEquals(1,size[2]);

        for(Color c: entrance){
            entrance.remove(c);
        }
    }

    @Test
    void getEntrance(){
        assertNotNull(schoolBoard.getEntrance());
    }

    @Test
    void removeFromEntrance(){
        schoolBoard.addStudentToEntrance(3);
        ArrayList<Color> entrance = schoolBoard.getEntrance();
        assertTrue(entrance.contains(Color.colorFromIndex(3)));
        schoolBoard.addStudentToDiningRoom(Color.RED_DRAGONS);
        assertFalse(entrance.contains(Color.colorFromIndex(3)));
    }
}