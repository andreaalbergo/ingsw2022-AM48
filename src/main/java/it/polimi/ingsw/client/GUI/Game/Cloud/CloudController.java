package it.polimi.ingsw.client.GUI.Game.Cloud;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CloudController implements Initializable {

    @FXML
    private AnchorPane scenePane;
    @FXML
    private GridPane cloudGrid1 = new GridPane(), cloudGrid2 = new GridPane(), cloudGrid3 = new GridPane();
    @FXML
    private ImageView cloud1, cloud2, cloud3;
    @FXML
    private RadioButton cloudButton1, cloudButton2, cloudButton3;
    @FXML
    private Circle student1Cloud1 = new Circle(), student2Cloud1 = new Circle(), student3Cloud1 = new Circle();
    @FXML
    private Circle student1Cloud2 = new Circle(), student2Cloud2 = new Circle(), student3Cloud2 = new Circle();

    private int numberOfPlayers;
    Stage stagePane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //getnumberOfPlayers();
        //cancel
        numberOfPlayers = 2;
        //end cancel

        //WORKS
        if(numberOfPlayers == 3){

            Image cloud1Player3 = new Image(getClass().getResourceAsStream("/Images/GameElements/Clouds/Cloud3Players/Cloud1.png"));
            Image cloud2Player3 = new Image(getClass().getResourceAsStream("/Images/GameElements/Clouds/Cloud3Players/Cloud2.png"));
            Image cloud3Player3 = new Image(getClass().getResourceAsStream("/Images/GameElements/Clouds/Cloud3Players/Cloud3.png"));

            cloud1.setImage(cloud1Player3);
            cloud2.setImage(cloud2Player3);
            cloud3.setImage(cloud3Player3);

            initializeCloud(cloudGrid1, 1);
            cloudButton1.setText("Cloud 1");
            initializeCloud(cloudGrid2, 2);
            cloudButton2.setText("Cloud 2");
            initializeCloud(cloudGrid3, 3);
            cloudButton3.setText("Cloud 3");
            student1Cloud1.setVisible(false);
            student2Cloud1.setVisible(false);
            student3Cloud1.setVisible(false);
            student1Cloud2.setVisible(false);
            student2Cloud2.setVisible(false);
            student3Cloud2.setVisible(false);
        }

        //WORKS BUT CAN BE BETTER
        if(numberOfPlayers == 2){

            Image cloud1Player2 = new Image(getClass().getResourceAsStream("/Images/GameElements/Clouds/Cloud2Players/Cloud1.png"));
            Image cloud2Player2 = new Image(getClass().getResourceAsStream("/Images/GameElements/Clouds/Cloud2Players/Cloud2.png"));

            cloud1.setImage(cloud1Player2);
            cloud1.setRotate(0);

            initializeCloud2Player(1);

            cloud2.setVisible(false);
            cloudButton2.setVisible(false);

            cloud3.setImage(cloud2Player2);
            cloud3.setScaleX(1.1);
            cloud3.setScaleY(1.1);
            initializeCloud2Player(2);

            cloudButton1.setText("Cloud 1");
            cloudButton3.setText("Cloud 2");


        }


    }

    private void initializeCloud(GridPane gridPane, int idCloud) {

        //int students = getStudentsOnCloud(idCloud);
        //cancel
        int[] students = {1, 0, 2, 1, 0};
        //end cancel

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

                    Circle circle = new Circle(20);
                    circle.setFill(color);
                    gridPane.add(circle, c, r);

                    r++;
                    if (r > 1) {
                        r -= 2;
                        c++;
                    }
                    students[i]--;

                } while (students[i] > 0);
            }

        }
    }

    private void initializeCloud2Player(int idCloud) {

        //int students = getStudentsOnCloud(idCloud);
        //cancel
        int[] students = {1, 0, 0, 2, 0};

        Color color = null;
        int counter = 1;

        for (int i = 0; i < 5; i++) {

            if (students[i] != 0) {
                do{
                    switch (i) {
                        case 0 -> color = Color.GREEN;
                        case 1 -> color = Color.RED;
                        case 2 -> color = Color.YELLOW;
                        case 3 -> color = Color.PINK;
                        case 4 -> color = Color.BLUE;
                    }

                    if(idCloud == 1) {
                        if (counter == 1) {
                            student1Cloud1.setFill(color);
                        }
                        if (counter == 2) {
                            student2Cloud1.setFill(color);
                        }
                        if (counter == 3) {
                            student3Cloud1.setFill(color);
                        }
                    }
                    else{
                        if(counter == 1){
                            student1Cloud2.setFill(color);
                        }if(counter == 2){
                            student2Cloud2.setFill(color);
                        }if(counter == 3){
                            student3Cloud2.setFill(color);
                        }
                    }

                    students[i]--;
                    counter++;
                } while (students[i] > 0);
            }
        }
    }

    @FXML
    private void chooseCloud(){

        int chosenCloud;
        boolean found = false;
        if(numberOfPlayers == 3) {
            if (cloudButton1.isSelected()) {
                chosenCloud = 1;
                found = true;
            }

            if (cloudButton2.isSelected()) {
                chosenCloud = 2;
                found = true;
            }

            if (cloudButton3.isSelected()) {
                chosenCloud = 3;
                found = true;
            }
        }

        if(numberOfPlayers == 2){

            if (cloudButton1.isSelected()) {
                chosenCloud = 1;
                found = true;
            }

            if (cloudButton3.isSelected()) {
                chosenCloud = 2;
                found = true;
            }

        }

        if(!found){
            String message = "You have to choose a cloud";

            Group rootPopUp = new Group();
            Scene scenePopUp = new Scene(rootPopUp, 340, 200);
            Stage stagePopUp = new Stage();
            Text text = new Text(message);

            text.setLayoutX(60);
            text.setLayoutY(52);
            Button closeButton = new Button();
            closeButton.setText("CLOSE");
            closeButton.setLayoutX(139);
            closeButton.setLayoutY(142);
            rootPopUp.getChildren().add(text);
            rootPopUp.getChildren().add(closeButton);
            stagePopUp.setScene(scenePopUp);
            stagePopUp.show();

            closeButton.setOnAction(new EventHandler() {
                @Override
                public void handle(Event event) {
                            stagePopUp.close();
                        }
            });
        }
        else{
            //send chosen cloud
            closeWindow();
        }
    }

    @FXML
    private void closeWindow(){

        stagePane = (Stage) scenePane.getScene().getWindow();
        stagePane.close();

    }
}
