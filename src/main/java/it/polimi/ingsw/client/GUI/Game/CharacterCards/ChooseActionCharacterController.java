package it.polimi.ingsw.client.GUI.Game.CharacterCards;

import it.polimi.ingsw.model.CharacterCard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChooseActionCharacterController implements Initializable {

    @FXML
    private AnchorPane scenePane;
    @FXML
    private Label cardName = new Label(), label1 = new Label(), label2 = new Label(), label3 = new Label(), labelJester = new Label();
    @FXML
    private ChoiceBox<String> colorPick = new ChoiceBox<>(), secondChoice = new ChoiceBox<>();
    @FXML
    private Spinner<Integer> islandPicker = new Spinner<>();
    @FXML
    private Button nextButton;
    @FXML
    private GridPane monkGrid = new GridPane(), jesterGrid = new GridPane(), warriorPrincessGrid = new GridPane();
    @FXML
    private ImageView monk = new ImageView(), jester = new ImageView(), warriorPrincess = new ImageView();

    private String card;
    private int studentsMoved = 0;
    Stage stage;

    private ArrayList<String> firstChoice = new ArrayList<>();
    private ArrayList<String> secondChoiceBox = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private void setUp(){
        if(card.equals("MONK")){

            labelJester.setVisible(false);
            secondChoice.setVisible(false);
            jester.setVisible(false);
            warriorPrincess.setVisible(false);

            //cancel
            int[] students = {1, 0, 2, 1, 0};
            //end cancel
            //int[] students = CharacterCard.getMonkStudents(); --> remove comment

            fullFillChoice(students);
            fullFillGrid(students, monkGrid, 2);

            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,12);
            valueFactory.setValue(1);

            islandPicker.setValueFactory(valueFactory);
        }

        if(card.equals("JESTER")){

            label2.setVisible(false);
            islandPicker.setVisible(false);
            label3.setVisible(false);
            monk.setVisible(false);
            warriorPrincess.setVisible(false);

            //cancel
            int[] students = {1, 2, 0, 2, 1};
            //end cancel
            //int[] students = CharacterCard.getJesterStudents(); --> remove comment
            ArrayList<String> jesterColor = new ArrayList<>();

            fullFillChoice(students);
            fullFillGrid(students, jesterGrid, 3);

            ArrayList<it.polimi.ingsw.model.Color> colorEntrance = new ArrayList<>();
            //colorEntrance = schoolBoard.getEntrance() --> arrayList<Color>
            //cancel
            colorEntrance.add(it.polimi.ingsw.model.Color.RED_DRAGONS);
            colorEntrance.add(it.polimi.ingsw.model.Color.PINK_FAIRIES);
            colorEntrance.add(it.polimi.ingsw.model.Color.GREEN_FROGS);
            //end cancel

            for(it.polimi.ingsw.model.Color singleColor : colorEntrance){

                switch (singleColor){
                    case GREEN_FROGS -> secondChoiceBox.add("Green");
                    case RED_DRAGONS -> secondChoiceBox.add("Red");
                    case YELLOW_GNOMES -> secondChoiceBox.add("Yellow");
                    case PINK_FAIRIES -> secondChoiceBox.add("Pink");
                    case BLUE_UNICORNS -> secondChoiceBox.add("Blue");
                }
            }

            secondChoice.getItems().addAll(secondChoiceBox);
            secondChoice.setValue("-- color entrance --");

        }

        if(card.equals("MERCHANT")){

            label1.setVisible(false);
            label2.setVisible(false);
            label3.setVisible(false);
            labelJester.setVisible(false);
            secondChoice.setVisible(false);
            islandPicker.setVisible(false);
            warriorPrincess.setVisible(false);
            monk.setVisible(false);
            jester.setVisible(false);

            ArrayList<String> colors = new ArrayList<>();
            colors.add("Green");
            colors.add("Red");
            colors.add("Yellow");
            colors.add("Pink");
            colors.add("Blue");

            colorPick.getItems().addAll(colors);
            colorPick.setValue("-- select color --");

        }

        if(card.equals("WARRIOR PRINCESS")){

            label2.setVisible(false);
            label3.setVisible(false);
            islandPicker.setVisible(false);
            secondChoice.setVisible(false);
            labelJester.setVisible(false);
            monk.setVisible(false);
            jester.setVisible(false);

            int[] princessStudents = CharacterCard.getWarriorPrincessStudents();

            fullFillChoice(princessStudents);
            fullFillGrid(princessStudents, warriorPrincessGrid, 2);
        }

    }

    private void fullFillGrid(int[] students, GridPane gridPane, int j){

        Color color = null;
        int c = 0, r = 0;

        for (int i = 0; i < 5; i++) {
            if (students[i] != 0) {
                do {

                    switch (i) {
                        case 0 -> color = Color.GREEN;
                        case 1 -> color = Color.RED;
                        case 2 -> color = Color.YELLOW;
                        case 3 -> color = Color.PINK;
                        case 4 -> color = Color.BLUE;
                    }

                    Circle circle;
                    if(j == 2) {
                        circle = new Circle(20);
                    }
                    else{
                        circle = new Circle(13);
                    }
                    circle.setFill(color);
                    gridPane.add(circle, c, r);

                    r++;
                    if (r == j) {
                        r -= j;
                        c++;
                    }
                    students[i]--;

                } while (students[i] > 0);
            }

        }

    }

    private void fullFillChoice(int[] students){

        Color color = null;
        int c = 0, r = 0;

        for (int i = 0; i < 5; i++) {
            if (students[i] != 0) {
                switch (i) {
                    case 0 -> firstChoice.add("Green");
                    case 1 -> firstChoice.add("Red");
                    case 2 -> firstChoice.add("Yellow");
                    case 3 -> firstChoice.add("Pink");
                    case 4 -> firstChoice.add("Blue");
                }
            }
        }


        colorPick.getItems().addAll(firstChoice);
        colorPick.setValue("-- pick color from card --");

    }

    public void displayName(String given){
        card = given;
        cardName.setText(given);
        setUp();
    }

    @FXML
    private void next(){

        if(card.equals("MONK")) {
            int island = islandPicker.getValue();
            String colorChosen = colorPick.getValue();
            //say what student in what island
            closeWindow();
        }

        if(card.equals("JESTER")) {
            //in a 2.0 version can remove student from jesterGrid

            studentsMoved++;

            String colorChosen = colorPick.getValue();
            String colorEntrance = secondChoice.getValue();
            //say colors for the change

            int index = getIndexFromString(colorChosen);


            //int[] actualStudents = CharacterCard.getJesterStudents(); --> remove comment
            //ArrayList<it.polimi.ingsw.model.Color> studentEntrance = schoolBoard.getEntrance(); --> remove comment
            ArrayList<it.polimi.ingsw.model.Color> studentEntrance = new ArrayList<>();
            //cancel
            int[] actualStudents = {1, 2, 0, 2, 1};
            //end cancel

            secondChoice.getItems().remove(colorEntrance);
            secondChoice.setValue("-- color entrance --");
            if(actualStudents[index] == 1){
                colorPick.getItems().remove(colorChosen);
                colorPick.setValue("-- pick color from card --");
            }

            if(studentsMoved >= 3){
                closeWindow();
            }
        }

        if(card.equals("MERCHANT")){
            String colorChosen = colorPick.getValue();
            //say color for influence calculation
            closeWindow();
        }

        if(card.equals("WARRIOR PRINCESS")){

            String colorChosen = colorPick.getValue();

            int index = getIndexFromString(colorChosen);
            //say color chosen

            closeWindow();
        }

    }

    private int getIndexFromString(String string){

        int index = 5;

        switch (string){
            case "Green" -> index = 0;
            case "Red" -> index = 1;
            case "Yellow" -> index = 2;
            case "Pink" -> index = 3;
            case "Blue" -> index = 4;
        }

        return index;
    }

    private void closeWindow(){

        stage = (Stage) scenePane.getScene().getWindow();
        stage.close();

    }
}
