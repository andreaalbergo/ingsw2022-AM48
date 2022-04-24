package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchoolBoardTest {

    SchoolBoard schoolBoard = new SchoolBoard("Andrea");

    @Test
    void addStudentToDiningRoom() {
        int [] diningRoom;
        diningRoom = schoolBoard.getDiningRoom();
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
        schoolBoard.addStudentToEntrance(2);
        assertEquals(1,schoolBoard.getEntrance()[2]);
        schoolBoard.addStudentToEntrance(2);
        assertEquals(2,schoolBoard.getEntrance()[2]);
    }

    @Test
    void getEntrance(){
        assertNotNull(schoolBoard.getEntrance());
    }
}