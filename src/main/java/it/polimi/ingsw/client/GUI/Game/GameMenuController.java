package it.polimi.ingsw.client.GUI.Game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameMenuController implements Initializable {

    @FXML
    private Label moneyCounter;

    Parent root;
    Scene scene;
    Stage stage;

    @FXML
    private void openIslandsPopUp(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml_files/Game/Islands/Islands.fxml"));
        root = loader.load();
        stage = new Stage(); //(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("ISLANDS");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void openSchoolBoardPopUp(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml_files/Game/SchoolBoard/SchoolBoard.fxml"));
        root = loader.load();
        stage = new Stage(); //(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("SCHOOLBOARD");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void openCloudsPopUp(ActionEvent event){

    }

    private void openAssistantCardsPopUp(ActionEvent event){

    }

    private void openCharacterCardsPopUp(ActionEvent event){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //getCoin of the player
    }
}
