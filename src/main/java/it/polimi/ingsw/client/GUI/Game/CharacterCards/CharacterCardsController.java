package it.polimi.ingsw.client.GUI.Game.CharacterCards;

import it.polimi.ingsw.client.GUI.ConfirmBox;
import it.polimi.ingsw.model.CharacterCard;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

//Rearrange the images with cards without cost --> modify externally
//add new choice in new panel
public class CharacterCardsController implements Initializable {

    @FXML
    private AnchorPane scenePane;
    @FXML
    private ImageView cardImage1, cardImage2, cardImage3;
    @FXML
    private Button infoCard1, infoCard2, infoCard3;
    @FXML
    private Button card1, card2, card3;
    @FXML
    private Label moneyCounter;

    Stage stagePane;
    String detail1, detail2, detail3;
    String title1, title2, title3;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        int coins;
        //coins = player.getCoins()
        //cancel
        coins = 5;
        //end cancel

        moneyCounter.setText(String.valueOf(coins));

        Map<CharacterCard, Integer> extractedCards = new HashMap<>();
        //extractedCards = getExtractedCharacterCards();
        //cancel
        extractedCards.put(CharacterCard.MONK, 1);
        extractedCards.put(CharacterCard.JESTER, 1);
        extractedCards.put(CharacterCard.MERCHANT, 2);
        //end cancel

        Image image = null, image1, image2, image3;
        int counter = 0;
        String message = "";
        String title = "";


        for(Map.Entry<CharacterCard, Integer> entry : extractedCards.entrySet()) {

            switch (entry.getKey()){
                case MONK -> {
                    image = new Image("/Images/GameElements/CharacterCards/Monk.jpg");
                    message = "Take 1 student from this card and place it \n on an island of your choice";
                    title = "MONK";
                }
                case INNKEEPER -> {
                    image = new Image("/Images/GameElements/CharacterCards/Innkeeper.jpg");
                    message = """
                            Take control of any number of Professors even if\s
                             you have the same number of students as the\s
                             player who currently controls them""";
                    title = "INNKEEPER";
                }
                case HERALD -> {
                    image = new Image("/Images/GameElements/CharacterCards/Herald.jpg");
                    message = """
                            You may move mother nature up to 2 additional\s
                             island than is indicated by the assistant card\s
                             you've played""";
                    title = "HERALD";
                }
                case CENTAUR -> {
                    image = new Image("/Images/GameElements/CharacterCards/Centaur.jpg");
                    message = "When resolving a conquering on an island towers \n do not count towards influence";
                    title = "CENTAUR";
                }
                case JESTER -> {
                    image = new Image("/Images/GameElements/CharacterCards/Jester.jpg");
                    message = """
                            You may take up to 3 students from this card\s
                             and replace them with the same number of\s
                             students from your entrance""";
                    title = "JESTER";
                }
                case KNIGHT -> {
                    image = new Image("/Images/GameElements/CharacterCards/Knight.jpg");
                    message = "During the influence calculation this turn, you \n count as having 2 more influence";
                    title = "KNIGHT";
                }
                case MERCHANT -> {
                    image = new Image("/Images/GameElements/CharacterCards/Merchant.jpg");
                    message = "Choose a color of student: during the \n influence calculation this turn, that color \n adds no influence";
                    title = "MERCHANT";
                }
                case WARRIOR_PRINCESS -> {
                    image = new Image("/Images/GameElements/CharacterCards/WarriorPrincess.jpg");
                    message = "Take 1 student from this card and place it in \n your dining room";
                    title = "WARRIOR PRINCESS";
                }
            }
            if(counter == 0){
                image1 = image;
                cardImage1.setImage(image1);
                detail1 = message;
                title1 = title;
            } else{
                if(counter == 1){
                    image2 = image;
                    cardImage2.setImage(image2);
                    detail2 = message;
                    title2 = title;
                } else{
                    image3 = image;
                    cardImage3.setImage(image3);
                    detail3 = message;
                    title3 = title;
                }
            }

            counter++;
        }
    }

    @FXML
    private void pressedDetail(ActionEvent event){

        Button sourceButton = (Button) event.getSource();

        if(sourceButton.equals(infoCard1)){
            popUp(detail1, title1);
        }
        if(sourceButton.equals(infoCard2)){
            popUp(detail2, title2);
        }
        if(sourceButton.equals(infoCard3)){
            popUp(detail3, title3);
        }

    }

    private void popUp(String message, String title){
        Group rootPopUp = new Group();
        Scene scenePopUp = new Scene(rootPopUp, 340, 200);
        Stage stagePopUp = new Stage();
        Text text = new Text(message);
        stagePopUp.setTitle(title);

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

    @FXML
    private void pressedCharacter(ActionEvent event) throws IOException {

        Button sourceButton = (Button) event.getSource();
        boolean confirm = false;
        String chosen = "";
        String message = "You have chosen: ";

        if(sourceButton.equals(card1)){
            confirm = ConfirmBox.display("CONFIRM", message + title1);
            chosen = title1;
        }
        if(sourceButton.equals(card2)){
            confirm = ConfirmBox.display("CONFIRM", message + title2);
            chosen = title2;
        }
        if(sourceButton.equals(card3)){
            confirm = ConfirmBox.display("CONFIRM", message + title3);
            chosen = title3;
        }

        if(confirm){
            if(chosen.equals("MONK") || chosen.equals("JESTER") || chosen.equals("MERCHANT") || chosen.equals("WARRIOR PRINCESS")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml_files/Game/CharacterCards/ChooseActionCharacter.fxml"));
                Parent root = loader.load();
                ChooseActionCharacterController chooseActionCharacterController = loader.getController();
                chooseActionCharacterController.displayName(chosen);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else{
                //send information
                closeWindow();
            }
        }

    }

    @FXML
    private void closeWindow(){

        stagePane = (Stage) scenePane.getScene().getWindow();
        stagePane.close();

    }
}
