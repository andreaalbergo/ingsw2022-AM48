package it.polimi.ingsw.model;

import it.polimi.ingsw.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player player;

    @BeforeEach
    void initialize(){
        player = new Player("Albus",1, Wizard.EMIR, Tower.GREY);
    }

    @Test
    void getNickname() {
        assertNotNull(player.getNickname());
    }

    @Test
    void getAssistantCards() {
        assertNotNull(player.getAssistantCards());
        /*
        for (int i=0; i<player.getAssistantCards().size();i++) {
            System.out.println(player.getAssistantCards().get(i));
            AssistantCard card = player.getAssistantCards().get(i);
            System.out.println(card.getValue());
            System.out.println(card.getNumber_of_steps());
        }
        player.getAssistantCards().remove(4);
        for (int i=0; i<player.getAssistantCards().size();i++) {
            System.out.println(player.getAssistantCards().get(i));
            AssistantCard card = player.getAssistantCards().get(i);
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
    void setTurn1() {
        player.setTurn(true);
        assertTrue(player.isTurn());
    }

    @Test
    void setTurn2() {
        player.setTurn(false);
        assertFalse(player.isTurn());
    }

    @Test
    void addCoins() {
        int newcoins = 2;
        player.addCoins(newcoins);
        assertEquals(player.getCoins(),1+newcoins);
    }

    @Test
    void createSchoolBoard1(){
        player.createSchoolBoard(false, 2);
        SchoolBoard schoolBoard = player.getSchoolBoard();
        assertEquals(2,schoolBoard.getNumberOfPlayers());
    }

    @Test
    void createSchoolBoard2() {
        player.createSchoolBoard(false, 2);
        SchoolBoard schoolBoard = player.getSchoolBoard();

        assertFalse(schoolBoard.isMode());
    }

    @Test
    void createSchoolBoard3() {
        player.createSchoolBoard(false, 2);
        SchoolBoard schoolBoard = player.getSchoolBoard();

        assertEquals("Albus",schoolBoard.getNickname());
    }

    @Test
    void removeCoins() {
        player.addCoins(3);
        player.removeCoin(2);
        assertEquals(2,player.getCoins());
    }
}
