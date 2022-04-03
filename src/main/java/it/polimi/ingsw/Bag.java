package it.polimi.ingsw;

import java.util.ArrayList;

public class Bag {
    private ArrayList<Integer> students;

    public Bag() {
        this.students = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            this.students.add(i, 24);
        }
    }

    public void drawStudentsToSchoolEntrance(SchoolBoard schoolBoard){

    }
}


