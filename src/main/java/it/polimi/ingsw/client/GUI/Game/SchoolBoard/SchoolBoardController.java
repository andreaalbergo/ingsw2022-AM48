package it.polimi.ingsw.client.GUI.Game.SchoolBoard;

import it.polimi.ingsw.client.GUI.Game.CharacterCards.ChooseActionCharacterController;
import it.polimi.ingsw.model.Tower;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SchoolBoardController implements Initializable{

    @FXML
    private AnchorPane scenePane;
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
    @FXML
    private Button move;
    @FXML
    private Label islandLabel;
    @FXML
    private Spinner<Integer> islandNumber;

    //private ToggleGroup toggleGroup = new ToggleGroup();
    private ArrayList<String> colorInEntrance = new ArrayList<>();
    private int defaultStart;
    private int movedStudent;
    private int maxStudentMove;
    ArrayList<Integer> entranceCounter = new ArrayList<>();
    ArrayList<Integer> entranceCounterFinal = new ArrayList<>();
    private ArrayList<String> usernames = new ArrayList<>();
    private ArrayList<String> placesToMoveStudent = new ArrayList<>();
    private ArrayList<String> movedStudents = new ArrayList<>();
    private String playerSchoolBoard;
    Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
        defaultStart = (int) diningGreen.getLayoutX();
        movedStudent = 0;
        placesToMoveStudent.add("Dining Room");
        placesToMoveStudent.add("Island");
        //int numberOfPlayer = board.getNumberOfPlayer()
        //cancel
        int numberOfPlayer = 2;
        //end cancel
        if(numberOfPlayer == 2){
            maxStudentMove = 3;
        } else{
            maxStudentMove = 4;
        }

        //cancel
        entranceCounter.add(2);
        entranceCounter.add(3);
        entranceCounter.add(1);
        entranceCounter.add(2);
        entranceCounter.add(0);
        //end cancel

        entranceCounterFinal.addAll(entranceCounter);

        //get nickname players
        //WORKS
        String currentPlayer = "";
        usernames.add("Player 1");
        usernames.add("Player 2");
        usernames.add("Player 3");

        selectPlayer.getItems().addAll(usernames);
        selectPlayer.setValue("-- Select Player --"); //--> when connected with server --> add next line and delete this
        //selectPlayer.setValue(currentPlayer); --> remove comment

        moveTo.getItems().addAll(placesToMoveStudent);
        moveTo.setValue("-- Choose location --");

        //getTowers and colour
        //WORKS
        //cancel
        int numTowers = 5; //received from getTowers
        // end cancel

        //remove declaration
        Tower towerColor = Tower.WHITE;
        Color tower;
        final int numCol = 2;

        switch(towerColor) {
            case WHITE -> tower = Color.WHITE;
            case GREY -> tower = Color.GREY;
            case BLACK -> tower = Color.BLACK;
            default -> tower = null; //shouldn't happen
        }

        for(int i = 0; i < numTowers; i++){
            Circle circle = new Circle(14);
            circle.setFill(tower);
            gridTowers.add(circle, i % numCol, i / numCol);
        }

        //getStudents
        //WORKS
        //try to do with grid (not urgent)

        //cancel
        int[] position = {2, 3, 5, 1, 0};
        //end cancel

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
        //cancel
        boolean[] professors = {true, true, false, false, false};
        //end cancel

        professorGreen.setVisible(false);
        professorRed.setVisible(false);
        professorYellow.setVisible(false);
        professorPink.setVisible(false);
        professorBlue.setVisible(false);

        for(int i = 0; i < 5; i++){
            if(professors[i]) {
                switch (i) {
                    case 0 -> professorGreen.setVisible(true);
                    case 1 -> professorRed.setVisible(true);
                    case 2 -> professorYellow.setVisible(true);
                    case 3 -> professorPink.setVisible(true);
                    case 4 -> professorBlue.setVisible(true);
                }
            }
        }

        //getStudentsEntrance
        //WORKS
        ArrayList<Integer> entrance = new ArrayList<>();

        //cancel
        entrance.add(2);
        entrance.add(3);
        entrance.add(1);
        entrance.add(2);
        entrance.add(0);
        //end cancel

        int i = 0;
        //for 2.0 version
        //int counter = 0;
        String colorName = "";

        for(int c = 0; c < 2 && i < 5; c++){
            for(int r = 0; r < 5 && i < 5; r++) {
                    if(!(c == 0 && r == 0)) {
                        Color color = null;
                        //for 2.0 version
                        //Image image = null;

                        switch (i) {
                            case 0 -> { color = Color.GREEN; colorName = "Green"; }
                            case 1 -> { color = Color.RED; colorName = "Red"; }
                            case 2 -> { color = Color.YELLOW; colorName = "Yellow"; }
                            case 3 -> { color = Color.PINK; colorName = "Pink"; }
                            case 4 -> { color = Color.BLUE; colorName = "Blue"; }
                        }

                        /*
                        for 2.0 version
                        switch (i) {
                            case 0 -> image = new Image("/Images/GameElements/Students/GreenStudent.png");
                            case 1 -> image = new Image("/Images/GameElements/Students/RedStudent.png");
                            case 2 -> image = new Image("/Images/GameElements/Students/YellowStudent.png");
                            case 3 -> image = new Image("/Images/GameElements/Students/PinkStudent.png");
                            case 4 -> image = new Image("/Images/GameElements/Students/BlueStudent.png");
                        }
                         */
/*
                        if(!(entrance.get(i) == 0)) {
                            if (entrance.get(i) == 1) {
                                colorInEntrance.add(colorName);
                            }
                            Circle circle = new Circle(14);
                            circle.setFill(color);
                            entranceGrid.add(circle, c, r);
 */
                            /*
                            for 2.0 version
                            RadioButton selectStudent = new RadioButton();
                            selectStudent.setText("");
                            selectStudent.setOpacity(0.45);
                            selectStudent.setToggleGroup(toggleGroup);
                            selectStudent.setId("student" + counter);
                            selectStudent.setOnAction(sendId(counter));
                            entranceGrid.add(selectStudent, c, r);
                            counter++;
                             */
/*

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

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,12);
        valueFactory.setValue(1);

        islandNumber.setValueFactory(valueFactory);
        */
        setUp(playerSchoolBoard);
    }

    //first open there are 3 time the player names
    //ALMOST WORKS
    public void setUp(String playerSchoolBoard){

        defaultStart = (int) diningGreen.getLayoutX();
        movedStudent = 0;
        placesToMoveStudent.add("Dining Room");
        placesToMoveStudent.add("Island");
        //int numberOfPlayer = board.getNumberOfPlayer()
        //cancel
        int numberOfPlayer = 2;
        //end cancel
        if(numberOfPlayer == 2){
            maxStudentMove = 3;
        } else{
            maxStudentMove = 4;
        }

        //cancel
        entranceCounter.add(2);
        entranceCounter.add(3);
        entranceCounter.add(1);
        entranceCounter.add(2);
        entranceCounter.add(0);
        //end cancel

        entranceCounterFinal.addAll(entranceCounter);

        //get nickname players
        //WORKS
        String currentPlayer = "";
        usernames.add("Player 1");
        usernames.add("Player 2");
        usernames.add("Player 3");

        selectPlayer.getItems().addAll(usernames);
        selectPlayer.setValue(playerSchoolBoard);

        moveTo.getItems().addAll(placesToMoveStudent);
        moveTo.setValue("-- Choose location --");

        //getTowers and colour
        //WORKS
        //cancel
        int numTowers = 5; //received from getTowers
        // end cancel

        //remove declaration
        Tower towerColor = Tower.WHITE;
        Color tower;
        final int numCol = 2;

        switch(towerColor) {
            case WHITE -> tower = Color.WHITE;
            case GREY -> tower = Color.GREY;
            case BLACK -> tower = Color.BLACK;
            default -> tower = null; //shouldn't happen
        }

        for(int i = 0; i < numTowers; i++){
            Circle circle = new Circle(14);
            circle.setFill(tower);
            gridTowers.add(circle, i % numCol, i / numCol);
        }

        //getStudents
        //WORKS
        //try to do with grid (not urgent)

        //cancel
        int[] position = {2, 3, 5, 1, 0};
        //end cancel

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
        //cancel
        boolean[] professors = {true, true, false, false, false};
        //end cancel

        professorGreen.setVisible(false);
        professorRed.setVisible(false);
        professorYellow.setVisible(false);
        professorPink.setVisible(false);
        professorBlue.setVisible(false);

        for(int i = 0; i < 5; i++){
            if(professors[i]) {
                switch (i) {
                    case 0 -> professorGreen.setVisible(true);
                    case 1 -> professorRed.setVisible(true);
                    case 2 -> professorYellow.setVisible(true);
                    case 3 -> professorPink.setVisible(true);
                    case 4 -> professorBlue.setVisible(true);
                }
            }
        }

        //getStudentsEntrance
        //WORKS
        ArrayList<Integer> entrance = new ArrayList<>();

        //cancel
        entrance.add(2);
        entrance.add(3);
        entrance.add(1);
        entrance.add(2);
        entrance.add(0);
        //end cancel

        int i = 0;
        //for 2.0 version
        //int counter = 0;
        String colorName = "";

        for(int c = 0; c < 2 && i < 5; c++){
            for(int r = 0; r < 5 && i < 5; r++) {
                if(!(c == 0 && r == 0)) {
                    Color color = null;
                    //for 2.0 version
                    //Image image = null;

                    switch (i) {
                        case 0 -> { color = Color.GREEN; colorName = "Green"; }
                        case 1 -> { color = Color.RED; colorName = "Red"; }
                        case 2 -> { color = Color.YELLOW; colorName = "Yellow"; }
                        case 3 -> { color = Color.PINK; colorName = "Pink"; }
                        case 4 -> { color = Color.BLUE; colorName = "Blue"; }
                    }

                        /*
                        for 2.0 version
                        switch (i) {
                            case 0 -> image = new Image("/Images/GameElements/Students/GreenStudent.png");
                            case 1 -> image = new Image("/Images/GameElements/Students/RedStudent.png");
                            case 2 -> image = new Image("/Images/GameElements/Students/YellowStudent.png");
                            case 3 -> image = new Image("/Images/GameElements/Students/PinkStudent.png");
                            case 4 -> image = new Image("/Images/GameElements/Students/BlueStudent.png");
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
                            for 2.0 version
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

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,12);
        valueFactory.setValue(1);

        islandNumber.setValueFactory(valueFactory);

    }

    private void moveStudentInDiningRoom(int position, int index) {

        int max;

        for(int i = 0; i < position-1; i++) {

            switch (index){
                case 0 -> diningGreen.setLayoutX(diningGreen.getLayoutX() + 26);
                case 1 -> diningRed.setLayoutX(diningRed.getLayoutX() + 26);
                case 2 -> diningYellow.setLayoutX(diningYellow.getLayoutX() + 26);
                case 3 -> diningPink.setLayoutX(diningPink.getLayoutX() + 26);
                case 4 -> diningBlue.setLayoutX(diningBlue.getLayoutX() + 26);
                default -> System.out.println("ERROR");
            }

            max = maxPosition();
            if(max != -1){
                chooseColor.getItems().remove(colorInEntrance.get(max));
            }

        }

    }

    /*
    //FOR SELECT THE STUDENTS FROM THE ENTRANCE WITH RADIO BUTTONS --> version 2.0
    public EventHandler<ActionEvent> sendId(int id){

        //selectedStudent = id;

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
    private void moveStudent() {
        //IF I REMOVE 3 DIFFERENT STUDENTS IT WORKS, OTHERWISE IF I REMOVE 2/3 STUDENTS OF THE SAME
        //COLOR, THE LAST WONT GO AWAY --> to fix

        String chosenColor, chosenLocation;
        int index = -1;

        chosenColor = chooseColor.getValue();
        chosenLocation = moveTo.getValue();

        if (colorInEntrance.contains(chosenColor) && placesToMoveStudent.contains(chosenLocation)) {

            switch (chosenColor){
                case "Green" -> index = 0;
                case "Red" -> index = 1;
                case "Yellow" -> index = 2;
                case "Pink" -> index = 3;
                case "Blue" -> index = 4;
            }

            if (chosenLocation.equals("Island")) {

                islandNumber.setVisible(true);
                islandLabel.setVisible(true);

                int idIsland = islandNumber.getValue();

                if(idIsland >= 1 && idIsland <= 12){

                    //say what island
                    removeFromEntrance(index, chosenColor);

                }

            }

            if (chosenLocation.equals("Dining Room")) {
                //diningroom[index]++;

                removeFromEntrance(index, chosenColor);
                moveStudentInDiningRoom(2, index);

            }

            if (movedStudent == maxStudentMove-1) {
                move.setVisible(false);
            }

            movedStudent++;
        }
    }

    private void removeFromEntrance(int index, String chosenColor){

        int counter = 0;
        int i;

        for (i = 0; i <= index; i++) {
            counter = counter + entranceCounterFinal.get(i);
        }
        i--;

        if(movedStudents.contains(chosenColor)){
            counter -= movedStudent;
                    /*if(entranceCounterFinal.get(i) > 2){
                        counter -= movedStudent;
                    }

                     */
        } else{
            movedStudents.add(chosenColor);
        }

        int val = entranceCounter.get(i) - 1;

        if (val == 0) {

            String remove = "NONE";

            switch (i) {
                case 0 -> remove = "Green";
                case 1 -> remove = "Red";
                case 2 -> remove = "Yellow";
                case 3 -> remove = "Pink";
                case 4 -> remove = "Blue";
            }

            chooseColor.getItems().remove(colorInEntrance.get(colorInEntrance.indexOf(remove)));
            chooseColor.setValue("-- Select Color --");

        }

        entranceCounter.set(i, val);
        int col = 0, row = 0;

        switch (counter - 1) {
            case 0 -> {
                col = 0;
                row = 1;
            }
            case 1 -> {
                col = 0;
                row = 2;
            }
            case 2 -> {
                col = 0;
                row = 3;
            }
            case 3 -> {
                col = 0;
                row = 4;
            }
            case 4 -> {
                col = 1;
                row = 0;
            }
            case 5 -> {
                col = 1;
                row = 1;
            }
            case 6 -> {
                col = 1;
                row = 2;
            }
            case 7 -> {
                col = 1;
                row = 3;
            }
            case 8 -> {
                col = 1;
                row = 4;
            }
        }

        int finalCol = col;
        int finalRow = row;
        entranceGrid.getChildren().removeIf(node -> GridPane.getColumnIndex(node) == finalCol && GridPane.getRowIndex(node) == finalRow);

    }

    private int maxPosition(){

        int value = -1;

        if(diningGreen.getLayoutX() == (defaultStart + (26*9))){
            System.out.println("You can't add more students to this line");
            value = 0;
        }
        if(diningRed.getLayoutX() == (defaultStart + (26*9))){
            System.out.println("You can't add more students to this line");
            value = 1;
        }
        if(diningYellow.getLayoutX() == (defaultStart + (26*9))){
            System.out.println("You can't add more students to this line");
            value = 2;
        }
        if(diningPink.getLayoutX() == (defaultStart + (26*9))){
            System.out.println("You can't add more students to this line");
            value = 3;
        }
        if(diningBlue.getLayoutX() == (defaultStart + (26*9))){
            System.out.println("You can't add more students to this line");
            value = 4;
        }

        return value;
    }

    @FXML
    public void closePane(){

        stage = (Stage) scenePane.getScene().getWindow();
        stage.close();

    }

    @FXML
    public void selectPlayer(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml_files/Game/SchoolBoard/SchoolBoard.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("SCHOOLBOARD");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        setUp(selectPlayer.getValue());
    }

}
