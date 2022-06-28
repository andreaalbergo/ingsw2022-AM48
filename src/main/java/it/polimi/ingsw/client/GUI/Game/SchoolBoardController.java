package it.polimi.ingsw.client.GUI.Game;

import it.polimi.ingsw.model.Tower;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SchoolBoardController implements Initializable{
    //missing last part: move student from entrance to dining or island + ask what id island

    private ArrayList<String> usernames = new ArrayList<>();
    private String[] placesToMoveStudent = {"Dining Room", "Island"};

    @FXML
    private ChoiceBox<String> selectPlayer;

    @FXML
    private ChoiceBox<String> moveTo;

    @FXML
    private GridPane entranceGrid = new GridPane();

    @FXML
    private ImageView diningGreen, diningRed, diningYellow, diningPink, diningBlue;

    @FXML
    private ImageView professorGreen, professorRed, professorYellow, professorPink, professorBlue;

    @FXML
    private GridPane gridTowers = new GridPane();

    @FXML
    private ChoiceBox<String> chooseColor;


    //private ToggleGroup toggleGroup = new ToggleGroup();
    private ArrayList<String> colorInEntrance = new ArrayList<>();
    private int[] entranceStud = {2, 4, 1, 0, 1};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //get nickname players
        //WORKS
        String currentPlayer = "";
        usernames.add("Player 1");
        usernames.add("Player 2");
        usernames.add("Player 3");

        selectPlayer.getItems().addAll(usernames);
        selectPlayer.setValue("-- Select Player --");
        //selectPlayer.setValue(currentPlayer);

        moveTo.getItems().addAll(placesToMoveStudent);
        moveTo.setValue("-- Choose location --");

        //getTowers and colour
        //WORKS
        int numTowers = 5; //received from getTowers
        Tower towerColor = Tower.WHITE;
        Color tower;
        final int numCol = 2;

        switch(towerColor) {
            case WHITE -> tower = Color.WHITE;
            case GREY -> tower = Color.GREY;
            case BLACK -> tower = Color.BLACK;
            default -> tower = null;
        }

        for(int i = 0; i < numTowers; i++){
            Circle circle = new Circle(14);
            circle.setFill(tower);
            gridTowers.add(circle, i % numCol, i / numCol);
        }

        //getStudents
        //WORKS
        //try to do with grid
        
        int[] position = {2, 3, 5, 1, 0};

        for(int index = 0; index < 5; index++){

            if(position[index] > 0){
                switch (index) {
                    case 0 -> diningGreen.setVisible(true);
                    case 1 -> diningRed.setVisible(true);
                    case 2 -> diningYellow.setVisible(true);
                    case 3 -> diningPink.setVisible(true);
                    case 4 -> diningBlue.setVisible(true);
                }

                moveStudentInDiningRoom(position[index], index);

            }

            else{
                switch (index) {
                    case 0 -> diningGreen.setVisible(false);
                    case 1 -> diningRed.setVisible(false);
                    case 2 -> diningYellow.setVisible(false);
                    case 3 -> diningPink.setVisible(false);
                    case 4 -> diningBlue.setVisible(false);
                }
            }
        }

        //getProfessors
        //CAN BE BETTER BUT WORKS
        boolean[] professors = {true, true, false, false, false};

        professorGreen.setVisible(false);
        professorRed.setVisible(false);
        professorYellow.setVisible(false);
        professorPink.setVisible(false);
        professorBlue.setVisible(false);

        if(professors[0]){
            professorGreen.setVisible(true);
        }
        if(professors[1]){
            professorRed.setVisible(true);
        }
        if(professors[2]){
            professorYellow.setVisible(true);
        }
        if(professors[3]){
            professorPink.setVisible(true);
        }
        if(professors[4]){
            professorBlue.setVisible(true);
        }

        //getStudentsEntrance
        //WORKS
        ArrayList<Integer> entrance = new ArrayList<>(5);
        entrance.add(2);
        entrance.add(3);
        entrance.add(1);
        entrance.add(2);
        entrance.add(0);


        int i = 0;
        //int counter = 0;
        String colorName = "";

        for(int c = 0; c < 2 && i < 5; c++){
            for(int r = 0; r < 5 && i < 5; r++) {
                    if(!(c == 0 && r == 0)) {
                        Color color = null;
                        //ImageView image = null;

                        switch (i) {
                            case 0 -> { color = Color.GREEN; colorName = "Green"; }
                            case 1 -> { color = Color.RED; colorName = "Red"; }
                            case 2 -> { color = Color.YELLOW; colorName = "Yellow"; }
                            case 3 -> { color = Color.PINK; colorName = "Pink"; }
                            case 4 -> { color = Color.BLUE; colorName = "Blue"; }
                        }

                        /*
                        switch (i) {
                            case 0 -> image = new ImageView("/Images/GameElements/Students/GreenStudent.png");
                            case 1 -> image = new ImageView("/Images/GameElements/Students/RedStudent.png");
                            case 2 -> image = new ImageView("/Images/GameElements/Students/YellowStudent.png");
                            case 3 -> image = new ImageView("/Images/GameElements/Students/PinkStudent.png");
                            case 4 -> image = new ImageView("/Images/GameElements/Students/BlueStudent.png");
                        }
                         */

                        if(!(entrance.get(i) == 0)) {
                            if (entrance.get(i) == 1) {
                                colorInEntrance.add(colorName);
                            }
                            Circle circle = new Circle(14);
                            circle.setFill(color);
                            entranceGrid.add(circle, c, r);
                            /*
                            RadioButton selectStudent = new RadioButton();
                            selectStudent.setText("");
                            selectStudent.setOpacity(0.45);
                            selectStudent.setToggleGroup(toggleGroup);
                            selectStudent.setId("student" + counter);
                            selectStudent.setOnAction(sendId(counter));
                            entranceGrid.add(selectStudent, c, r);
                            counter++;
                             */


                            if (entrance.get(i) - 1 == 0) {
                                entrance.set(i, entrance.get(i) - 1);
                                i++;
                            } else {
                                entrance.set(i, entrance.get(i) - 1);
                            }
                        }
                        else{
                            i++;
                            r--;
                        }
                    }
            }
        }

        chooseColor.setValue("-- Choose color --");
        chooseColor.getItems().addAll(colorInEntrance);
    }

    private void moveStudentInDiningRoom(int position, int index) {

        for(int i = 0; i < position-1; i++) {

            switch (index){
                case 0 -> diningGreen.setLayoutX(diningGreen.getLayoutX() + 26);
                case 1 -> diningRed.setLayoutX(diningRed.getLayoutX() + 26);
                case 2 -> diningYellow.setLayoutX(diningYellow.getLayoutX() + 26);
                case 3 -> diningPink.setLayoutX(diningPink.getLayoutX() + 26);
                case 4 -> diningBlue.setLayoutX(diningBlue.getLayoutX() + 26);
                default -> System.out.println("ERROR");
            }

        }

    }

    /*
    FOR SELECT THE STUDENTS FROM THE ENTRANCE
    public EventHandler<ActionEvent> sendId(int id){

        selectedStudent = id;

        return null;
    }

    public void selectedStudents(){

        int col = 0, row = 1;

        switch (selectedStudent){
            case 0 -> {col = 0; row = 1; }
            case 1 -> {col = 0; row = 2; }
            case 2 -> {col = 0; row = 3; }
            case 3 -> {col = 0; row = 4; }
            case 4 -> {col = 1; row = 0; }
            case 5 -> {col = 1; row = 1; }
            case 6 -> {col = 1; row = 2; }
            case 7 -> {col = 1; row = 3; }
            case 8 -> {col = 1; row = 4; }
        }

        Node node = getNodeFromGridPane(entranceGrid, col, row);
        if(node != null) {
            getColorFromNode(node);
        }

    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row){

        for(Node node : gridPane.getChildren()){
            if(GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row){
                return node;
            }
        }
        return null;
    }

    private Color getColorFromNode(Node node){

        Color color = null;

        String nodeDescription = String.valueOf(node);
        System.out.println(nodeDescription);
        String colorCode = nodeDescription.substring(53,61);
        System.out.println(colorCode);
        if(colorCode == "ffc0cbff"){
            color = Color.PINK;
        }
        if(colorCode == ""){
            color = Color.YELLOW;
        }
        if(colorCode == ""){
            color = Color.BLUE;
        }

        if(colorCode = ""){
            color = Color.RED;
        }
        if(colorCode = ""){
            color = Color.GREEN;
        }


        return color;
    }
    */

    @FXML
    private void moveStudent(){

        String chosenColor, chosenLocation;
        int index = -1;

        chosenColor = chooseColor.getValue();

        if(chosenColor == "Green"){
            index = 0;
        }
        if(chosenColor == "Red"){
            index = 1;
        }
        if(chosenColor == "Yellow"){
            index = 2;
        }
        if(chosenColor == "Pink"){
            index = 3;
        }
        if(chosenColor == "Blue"){
            index = 4;
        }

        chosenLocation = moveTo.getValue();

        if(chosenLocation == "Island"){
            //ask what id island
        }

        if(chosenLocation == "Dining Room"){
            //diningroom[index]++;
            System.out.println(diningGreen.getX());
            moveStudentInDiningRoom(1,index);
            System.out.println(diningGreen.getX());
        }
    }

}
