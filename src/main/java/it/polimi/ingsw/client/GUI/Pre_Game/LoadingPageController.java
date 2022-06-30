package it.polimi.ingsw.client.GUI.Pre_Game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadingPageController implements Initializable {

    /*
    @FXML
    private ProgressBar progressBar;
     */

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private Label playersConnected;

    @FXML
    private Label loadingLabel;

    int progressCheck = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //progressBar.setStyle("-fx-accent: #00FF00;");
        progressIndicator.setStyle("-fx-accent: #00FF00;");
    }

    /*
    can add a ProgressBar in LoadingPage
    public void increaseProgress(){
        if(progressCheck < 1) {
            /*
            BigDecimal progress = new BigDecimal(String.format("%.2f", 0.1));
            progressBar.setProgress(progress.doubleValue());
            loadingLabel.setText(Double.toString((int) Math.round(progress.doubleValue() * 100)) + "%");
            progressCheck = (int) Math.round(progress.doubleValue());

            /*
            progressBar.setProgress(progressCheck += 0.2);
            loadingLabel.setText(Integer.toString(progressCheck * 100) + " %");

        }
    }
    */

    //PatternObserver when one method is called
    //MultiplayerServer.addClientToGame() useful
    //playersInLobby = MultiplayerServer.getWaitingSize()

    public void switchToGameMenu(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml_files/Game/GameMenu.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
