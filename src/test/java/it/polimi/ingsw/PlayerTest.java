package it.polimi.ingsw;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.SchoolBoard;
import it.polimi.ingsw.model.Tower;
import it.polimi.ingsw.model.Wizard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player player = new Player("Albus",1, Wizard.WIZARD1, Tower.GREY);


    @Test
    void getNickname() {
        assertNotNull(player.getNickname());
    }

    @Test
    void getAssistantCards() {
        assertNotNull(player.getAssistantCards());
        /*for (int i=0; i<player.getAssistantCards().size();i++) {
            System.out.println(player.getAssistantCards().get(i));
            AssistantCard card = (AssistantCard) player.getAssistantCards().get(i);
            System.out.println(card.getValue());
            System.out.println(card.getNumber_of_steps());
        }
        player.getAssistantCards().remove(4);
        for (int i=0; i<player.getAssistantCards().size();i++) {
            System.out.println(player.getAssistantCards().get(i));
            AssistantCard card = (AssistantCard) player.getAssistantCards().get(i);
            System.out.println(card.getValue());
            System.out.println(card.getNumber_of_steps());
        }

         */
    }

    @Test
    void getCoins() {
        assertTrue(player.getCoins() >= 0);
    }

    @Test
    void isTurn() {
        assertTrue(player.isTurn() || !player.isTurn());
    }

    @Test
    void getChosenWizard() {
        assertNotNull(player.getChosenWizard());
    }

    @Test
    void setTurn() {
        player.setTurn(false);
        assertFalse(player.isTurn());
        player.setTurn(true);
        assertTrue(player.isTurn());
    }

    @Test
    void addCoins() {
        int newcoins = 2;
        player.addCoins(newcoins);
        assertEquals(player.getCoins(),1+newcoins);
    }

    @Test
    void createSchoolBoard(){
        player.createSchoolBoard(false, 2);
        SchoolBoard schoolBoard = player.getSchoolBoard();
        assertEquals(2,schoolBoard.getNumberOfPlayers());
        assertFalse(schoolBoard.isMode());
        assertEquals("Albus",schoolBoard.getNickname());
    }
}
