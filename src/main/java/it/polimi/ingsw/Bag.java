package it.polimi.ingsw;

import java.util.ArrayList;


public class Bag {
    private static ArrayList<Integer> students;

    public Bag() {
        students = new ArrayList<>(Board.getNumberOfPlayers());
        for (int i = 0; i < 5; i++) {
            students.add(i, 24);
        }
    }

    public static void drawOneStudentToCloudTile() {
        addStudentToCloud();
    }

    private static void addStudentToCloud() {
        int indexCell;
        Color randomColorPicked;

        for(indexCell=0; indexCell< Cloud.getCloudDimension(); indexCell++) {
            randomColorPicked = Color.getRandomColor();

            if(!removeStudentFromBag(randomColorPicked.getColorIndex()))
                indexCell--;
            else
                Cloud.addStudentToSelectedCloudCell(indexCell, randomColorPicked.getColorIndex());
        }
    }

    private static boolean removeStudentFromBag(int colorIndex) {
        int quantityOfColorIndex = students.get(colorIndex);

        if (quantityOfColorIndex > 0) {
            students.set(colorIndex, quantityOfColorIndex-1);
            return true;
        } else {
            System.out.println("Bag is Empty, retry \"getRandomColor()\"");
            return false;
        }
    }

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
    public void setupIslands(List<IslandTile> Islands){

    }

 */

}


