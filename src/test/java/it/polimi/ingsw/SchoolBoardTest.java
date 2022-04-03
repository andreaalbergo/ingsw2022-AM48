package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchoolBoardTest {

    SchoolBoard schoolBoard = new SchoolBoard("Andrea",3,true);

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
        assertEquals(1,diningRoom[Color.RED_DRAGONS.getIndex()]);
        schoolBoard.addStudentToDiningRoom(Color.RED_DRAGONS);
        assertEquals(2,diningRoom[Color.RED_DRAGONS.getIndex()]);

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
        assertTrue(professor[Color.RED_DRAGONS.getIndex()]);
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


}