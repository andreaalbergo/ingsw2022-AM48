package it.polimi.ingsw.client.GUI;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ControllerSettingPage{

    @FXML
    Label welcomeLabel;

    @FXML
    private RadioButton druid, emir, old_samurai, witch;

    @FXML
    private CheckBox expertModeBox;

    @FXML
    private TextField numberOfPlayerField;


    private String chosenWizard;
    private boolean expertMode;

    @FXML
    public void displayName(String username) {
        welcomeLabel.setText("Welcome: " + username);
    }

    public void getWizard() {
        if (druid.isSelected()) {
            chosenWizard = druid.getText();
        } else {
            if (emir.isSelected()) {
                chosenWizard = emir.getText();
            } else {
                if (old_samurai.isSelected()) {
                    chosenWizard = old_samurai.getText();
                } else {
                    if(witch.isSelected())
                        chosenWizard = witch.getText();
                    else{
                        chosenWizard = null;
                    }
                }
            }
        }
    }

    public void getExpertMode() {
        expertMode = expertModeBox.isSelected();
    }

    @FXML
    public void switchToLoadingPage(ActionEvent event) throws IOException {

        Group rootPopUp = new Group();
        Scene scenePopUp = new Scene(rootPopUp, 340, 200);
        Stage stagePopUp = new Stage();
        Text text = new Text();

        int numberOfPlayers = Integer.parseInt(numberOfPlayerField.getText());
        String invalidWizard = "", invalidNOP = "";

        getWizard();
        getExpertMode();

        if (chosenWizard == null || numberOfPlayers < 2 || numberOfPlayers > 3) {

            if(chosenWizard == null) {
                invalidWizard = "You have to chose one wizard";
            }

            if(numberOfPlayers < 2 || numberOfPlayers > 3){
                invalidNOP = "You have to choose between 2 or 3 players";
            }

            text.setText(invalidWizard + "\n" + invalidNOP);

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

        } else {

            boolean result = ConfirmBox.display("CONFIRM",
                    "Chosen wizard: " + chosenWizard +
                    "\nNumber of players: " + numberOfPlayers +
                    "\nExpert mode: " + expertMode);

            if (result) {
                //ClientView.setWizard(chosenWizard);

                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml_files/LoadingPage.fxml")));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
        }
    }
}
