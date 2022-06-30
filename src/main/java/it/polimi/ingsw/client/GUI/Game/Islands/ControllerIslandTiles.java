package it.polimi.ingsw.client.GUI.Game.Islands;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class ControllerIslandTiles{

    private int interestedIsland;

    @FXML
    private AnchorPane scenePane;
    @FXML
    private TextField numberOfStepsString;
    @FXML
    private ImageView motherNature;
    @FXML
    private Label details;

    private int motherNaturePosition = 0;
    private final int[] islandsPositionX = {30, 159, 284, 407, 535, 535, 535, 407, 284, 159, 30};
    private final int[] islandPositionY = {35, 35, 35, 35, 35, 193, 332, 332, 332, 332, 332, 193};

    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    private void pressedIsland1(ActionEvent event) throws IOException{
        interestedIsland = 1;
        openIslandPopUp();
    }

    @FXML
    private void pressedIsland2(ActionEvent event) throws IOException{
        interestedIsland = 2;
        openIslandPopUp();
    }

    @FXML
    private void pressedIsland3(ActionEvent event) throws IOException{
        interestedIsland = 3;
        openIslandPopUp();
    }

    @FXML
    private void pressedIsland4(ActionEvent event) throws IOException{
        interestedIsland = 4;
        openIslandPopUp();
    }

    @FXML
    private void pressedIsland5(ActionEvent event) throws IOException{
        interestedIsland = 5;
        openIslandPopUp();
    }

    @FXML
    private void pressedIsland6(ActionEvent event) throws IOException{
        interestedIsland = 6;
        openIslandPopUp();
    }

    @FXML
    private void pressedIsland7(ActionEvent event) throws IOException{
        interestedIsland = 7;
        openIslandPopUp();
    }

    @FXML
    private void pressedIsland8(ActionEvent event) throws IOException{
        interestedIsland = 8;
        openIslandPopUp();
    }

    @FXML
    private void pressedIsland9(ActionEvent event) throws IOException{
        interestedIsland = 9;
        openIslandPopUp();
    }

    @FXML
    private void pressedIsland10(ActionEvent event) throws IOException{
        interestedIsland = 10;
        openIslandPopUp();
    }

    @FXML
    private void pressedIsland11(ActionEvent event) throws IOException {
        interestedIsland = 11;
        openIslandPopUp();
    }

    @FXML
    private void pressedIsland12(ActionEvent event) throws IOException {
        interestedIsland = 12;
        openIslandPopUp();
    }

    private void openIslandPopUp() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml_files/Game/Islands/IslandDetails.fxml"));
        root = loader.load();
        DetailedIslandController detailedIslandController = loader.getController();
        detailedIslandController.setDetails(interestedIsland);
        stage = new Stage(); //(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("ISLAND: " + interestedIsland);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void moveMotherNature(){

        int position;

        int numberOfSteps = Integer.parseInt(numberOfStepsString.getText());
        //check if the number is ok
        motherNaturePosition += numberOfSteps;

        if(motherNaturePosition > 11) {
            position = motherNaturePosition % 12;
        }
        else{
            position = motherNaturePosition;
        }

        setMotherNaturePosition(position);
    }

    private void setMotherNaturePosition(int idIsland){
        //if idIsland starts from 0
        motherNature.setLayoutX(islandsPositionX[idIsland]);
        motherNature.setLayoutY(islandPositionY[idIsland]);
    }

    @FXML
    public void closePane(){

        stage = (Stage) scenePane.getScene().getWindow();
        stage.close();

    }
}