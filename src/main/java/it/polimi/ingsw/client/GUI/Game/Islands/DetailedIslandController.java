package it.polimi.ingsw.client.GUI.Game.Islands;

import it.polimi.ingsw.model.Tower;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;


public class DetailedIslandController {

    @FXML
    private AnchorPane scenePane;
    @FXML
    private ImageView islandType;
    @FXML
    private Label idIsland;
    @FXML
    private Label towers;
    @FXML
    private Label motherNature;
    @FXML
    private Label students;
    @FXML
    private ImageView towerImageView;
    @FXML
    private ImageView motherNatureImage;
    @FXML
    private GridPane studentsPanel;

    private int[] studentsOnIsland = new int[5];
    private Tower tower;
    Stage stage;

    @FXML
    public void setDetails(int island) {

        Image islandImage = new Image("/Images/GameElements/Islands/IslandType1.png");

        getIslandDetails(island);

        switch (island % 3){
            case 0 -> islandImage = new Image("/Images/GameElements/Islands/IslandType3.png");
            case 1 -> islandImage = new Image("/Images/GameElements/Islands/IslandType1.png");
            case 2 -> islandImage = new Image("/Images/GameElements/Islands/IslandType2.png");
        }
        islandType.setImage(islandImage);

        decorateIsland(island);

    }

    private void getIslandDetails(int island) {

        String details = "Island id: " + island;

        ArrayList<Integer> studentsIsland = new ArrayList<>();

        //How many students and which color
/*
        for(int i = 0; i < 5; i++){
            studentsOnIsland[i] = studentsIsland.get(i);
        }

 */

        //who have influence
        //owner of the island --> username or ------

        idIsland.setText(details);

        //tower, motherNature and students Label
    }

    private void decorateIsland(int island){

        //getTowerColor
        //WORKS
        tower = Tower.WHITE;
        //cancel

        Image towerImage = new Image("/Images/GameElements/Towers/WhiteTower.png");

        switch (tower){
            case WHITE -> towerImage = new Image("/Images/GameElements/Towers/WhiteTower.png");
            case BLACK -> towerImage = new Image("/Images/GameElements/Towers/BlackTower.png");
            case GREY -> towerImage = new Image("/Images/GameElements/Towers/GreyTower.png");
        }
        towerImageView.setImage(towerImage);

        //motherNaturePosition = getMotherNature Position
        //WORKS
        int motherNaturePosition = 1;
        //cancel

        motherNatureImage.setVisible(false);
        if(motherNaturePosition == island){
            motherNatureImage.setVisible(true);
        }

        //getStudents on island --> receive an ArrayList<Integer>
        //WORKS
        studentsOnIsland[0] = 2;
        studentsOnIsland[1] = 3;
        studentsOnIsland[2] = 1;
        studentsOnIsland[3] = 0;
        studentsOnIsland[4] = 4;
        //to cancel

        int i = 0;
        Color studentColor = Color.BLACK; //default value

        for(int c = 0; c < 5 && i < 5; c++){
            for(int r = 0; r < 4 && i < 5; r++){

                switch(i){
                    case 0 -> studentColor = Color.GREEN;
                    case 1 -> studentColor = Color.RED;
                    case 2 -> studentColor = Color.YELLOW;
                    case 3 -> studentColor = Color.PINK;
                    case 4 -> studentColor = Color.BLUE;
                }

                if(!(studentsOnIsland[i] == 0)) {
                    Circle circle = new Circle(14);
                    circle.setFill(studentColor);
                    studentsPanel.add(circle, c, r);
                    if (studentsOnIsland[i] - 1 == 0) {
                        studentsOnIsland[i]--;
                        i++;
                    } else{
                        studentsOnIsland[i]--;
                    }
                }
                else{
                    i++;
                    r--;
                }

            }
        }
    }

    @FXML
    public void closePane(){

        stage = (Stage) scenePane.getScene().getWindow();
        stage.close();

    }
}
