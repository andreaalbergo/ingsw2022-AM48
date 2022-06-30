package it.polimi.ingsw.client.GUI.Game.AssistantCards;

import it.polimi.ingsw.client.GUI.ConfirmBox;
import it.polimi.ingsw.model.AssistantCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AssistantCardsController implements Initializable {

    @FXML
    private AnchorPane scenePane;
    @FXML
    private ImageView assistant1, assistant2, assistant3, assistant4, assistant5;
    @FXML
    private ImageView assistant6, assistant7, assistant8, assistant9, assistant10;
    @FXML
    private Button button1, button2, button3, button4, button5;
    @FXML
    private Button button6, button7, button8, button9, button10;

    Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<AssistantCard> cardsNotPlayed = new ArrayList<>();
        //player.getAssistantCards() returns a List
        //cancel
        cardsNotPlayed.add(AssistantCard.ONE);
        //cardsNotPlayed.add(AssistantCard.TWO);
        cardsNotPlayed.add(AssistantCard.THREE);
        //cardsNotPlayed.add(AssistantCard.FOUR);
        //cardsNotPlayed.add(AssistantCard.FIVE);
        cardsNotPlayed.add(AssistantCard.SIX);
        cardsNotPlayed.add(AssistantCard.SEVEN);
        //cardsNotPlayed.add(AssistantCard.EIGHT);
        cardsNotPlayed.add(AssistantCard.NINE);
        cardsNotPlayed.add(AssistantCard.TEN);
        //end cancel

        for(AssistantCard i : cardsNotPlayed){
            switch (i){
                case ONE -> {
                    assistant1.setVisible(true);
                    button1.setVisible(true);
                    button1.setDisable(false);
                }
                case TWO -> {
                    assistant2.setVisible(true);
                    button2.setVisible(true);
                    button2.setDisable(false);
                }
                case THREE -> {
                    assistant3.setVisible(true);
                    button3.setVisible(true);
                    button3.setDisable(false);
                }
                case FOUR -> {
                    assistant4.setVisible(true);
                    button4.setVisible(true);
                    button4.setDisable(false);
                }
                case FIVE -> {
                    assistant5.setVisible(true);
                    button5.setVisible(true);
                    button5.setDisable(false);
                }
                case SIX -> {
                    assistant6.setVisible(true);
                    button6.setVisible(true);
                    button6.setDisable(false);
                }
                case SEVEN -> {
                    assistant7.setVisible(true);
                    button7.setVisible(true);
                    button7.setDisable(false);
                }
                case EIGHT -> {
                    assistant8.setVisible(true);
                    button8.setVisible(true);
                    button8.setDisable(false);
                }
                case NINE -> {
                    assistant9.setVisible(true);
                    button9.setVisible(true);
                    button9.setDisable(false);
                }
                case TEN -> {
                    assistant10.setVisible(true);
                    button10.setVisible(true);
                    button10.setDisable(false);
                }
            }
        }

    }

    //VERY BASIC WAY TO GET THE ASSISTANT CARD --> find a better way
    //BUT WORKS

    @FXML
    private void pressedButton(ActionEvent event){

        Button sourceButton = (Button) event.getSource();

        if(sourceButton.equals(button1)){
            confirmBox(1);
        }
        if(sourceButton.equals(button2)){
            confirmBox(2);
        }
        if(sourceButton.equals(button3)){
            confirmBox(3);
        }
        if(sourceButton.equals(button4)){
            confirmBox(4);
        }
        if(sourceButton.equals(button5)){
            confirmBox(5);
        }
        if(sourceButton.equals(button6)){
            confirmBox(6);
        }
        if(sourceButton.equals(button7)){
            confirmBox(7);
        }
        if(sourceButton.equals(button8)){
            confirmBox(8);
        }
        if(sourceButton.equals(button9)){
            confirmBox(9);
        }
        if(sourceButton.equals(button10)){
            confirmBox(10);
        }
    }

    @FXML
    private void closeWindow(){

        stage = (Stage) scenePane.getScene().getWindow();
        stage.close();

    }

    private void confirmBox(int number){

        boolean result;

        String message = "You have chosen: " + number;

        result = ConfirmBox.display("CONFIRM", message);

        if(result){
            //send value of the chosen card
            System.out.println("chosen: " + number);
            closeWindow();
        }

    }

}
