package it.polimi.ingsw.client.GUI.Game;

import it.polimi.ingsw.client.GUI.Game.SchoolBoard.SchoolBoardController;
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

        //getCurrentPlayer()
        //cancel
        String currentPlayer = "Player1";
        //end cancel

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml_files/Game/SchoolBoard/SchoolBoard.fxml"));
        root = loader.load();
        SchoolBoardController schoolBoardController = loader.getController();
        schoolBoardController.setUp(currentPlayer);
        stage = new Stage(); //(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("SCHOOLBOARD");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void openCloudsPopUp(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml_files/Game/Cloud/Cloud.fxml"));
        root = loader.load();
        stage = new Stage(); //(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("CLOUDS");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void openAssistantCardsPopUp(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml_files/Game/AssistantCards/AssistantCards.fxml"));
        root = loader.load();
        stage = new Stage(); //(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("ASSISTANT CARDS");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    private void openCharacterCardsPopUp(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml_files/Game/CharacterCards/CharacterCards.fxml"));
        root = loader.load();
        stage = new Stage(); //(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("CHARACTER CARDS");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //getCoin of the player
        //cancel
        moneyCounter.setText("5");
        //end cancel
    }
}
