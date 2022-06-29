package it.polimi.ingsw.client.GUI.Pre_Game;

import it.polimi.ingsw.client.GUI.ConfirmBox;
import it.polimi.ingsw.costanti.Constants;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import static javafx.fxml.FXMLLoader.load;

public class ControllerIntro {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    TextField usernameField;
    @FXML
    TextField ipAddressField;
    @FXML
    TextField portField;

    @FXML
    public void switchToMainMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml_files/Intro/MainMenu.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToCredits(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml_files/Intro/Credits.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToRules(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml_files/Intro/Rules.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openItalianRuleLink(ActionEvent event) throws URISyntaxException, IOException{
        System.out.println("Downloading rules");
        Desktop.getDesktop().browse(new URI("https://bit.ly/3zW1ns5"));
    }

    @FXML
    void openEnglishRuleLink(ActionEvent event) throws URISyntaxException, IOException{
        System.out.println("Downloading rules");
        Desktop.getDesktop().browse(new URI("https://bit.ly/3ykVio9"));
    }

    @FXML
    public void switchToStartingPanel(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml_files/Intro/StartingPanel.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToSettingPage(ActionEvent event) throws IOException {

        String ipAddress = ipAddressField.getText();
        String IPV4_REGEX = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        Matcher matcher = Pattern.compile(IPV4_REGEX).matcher(ipAddress);
        String portNumber = portField.getText();
        String username = usernameField.getText();
        boolean ipValid = matcher.matches();
        boolean portValid = isInteger(portNumber);
        String invalidIP = "", invalidPort = "", invalidUsername = "";
        int portNumberInt = Integer.parseInt(portNumber);

        Group rootPopUp = new Group();
        Scene scenePopUp = new Scene(rootPopUp, 340, 200);
        Stage stagePopUp = new Stage();
        Text text = new Text();

        if(portValid){
            if(portNumberInt < 1024 || portNumberInt > 64000) {
                portValid = false;
            }
        }

        if(!ipValid || !portValid || isInteger(username) || username.isEmpty()){

            if(!ipValid) {
                invalidIP = "Ip address not valid";
            }
            if(!portValid){
                invalidPort = "Port number not valid";
            }
            if(isInteger(username) || username.isEmpty()){
                invalidUsername = "Invalid username";
            }

            text.setText(invalidUsername + "\n" + invalidIP + "\n" + invalidPort);

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
            closeButton.setOnAction(new EventHandler(){
                @Override
                public void handle(Event event){
                    stagePopUp.close();
                }
            });
        }

        else {

            boolean result = ConfirmBox.display("CONFIRM", "Username: "+ username + "\nIP Address: " + ipAddress + "\nPort: " + portNumber);

            if(result) {
                Constants.setAddress(ipAddress);
                Constants.setPort(portNumberInt);
                //ClientView.setName(username);
                //MultiplayerServer.addClientToGame(username, ClientHandler client);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml_files/Intro/SettingPage.fxml"));
                root = loader.load();
                ControllerSetUpGame controllerSettingPage = loader.getController();
                controllerSettingPage.displayName(username);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }

            //find a way to open this window when user click on CONFIRM button, closing the popUp and exiting from the previous fxml page



        }
    }

    private boolean isInteger(String string){
        try{
            int portNumber = Integer.parseInt(string);
                return true;
        }
        catch(Exception e){
            return false;
        }
    }

}
