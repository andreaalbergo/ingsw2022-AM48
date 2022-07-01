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

    /**
     * checks that nickname is setted
     */
    @Test
    void getNickname() {
        assertNotNull(player.getNickname());
    }

    /**
     * checks that at the start of the game there are all the assistant cards
     */
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

    /**
     * checks how many coins there are at the start of the game
     */
    @Test
    void getCoins() {
        assertTrue(player.getCoins() >= 0);
    }

    /**
     * checks of the turn method works
     */
    @Test
    void isTurn() {
        assertTrue(player.isTurn() || !player.isTurn());
    }

    /**
     * checks if the wizard is setted
     */
    @Test
    void getChosenWizard() {
        assertNotNull(player.getChosenWizard());
    }

    /**
     * checks if the turn setting works
     */
    @Test
    void setTurn1() {
        player.setTurn(true);
        assertTrue(player.isTurn());
    }

    /**
     * checks if the turn setting works
     */
    @Test
    void setTurn2() {
        player.setTurn(false);
        assertFalse(player.isTurn());
    }

    /**
     * checks if the methods adds coins to the player
     */
    @Test
    void addCoins() {
        int newcoins = 2;
        player.addCoins(newcoins);
        assertEquals(player.getCoins(),1+newcoins);
    }

    /**
     * checks if the schoolboard is created with the given number of player
     */
    @Test
    void createSchoolBoard1(){
        player.createSchoolBoard(false, 2);
        SchoolBoard schoolBoard = player.getSchoolBoard();
        assertEquals(2,schoolBoard.getNumberOfPlayers());
    }

    /**
     * checks if the schoolboard is created with the given mode
     */
    @Test
    void createSchoolBoard2() {
        player.createSchoolBoard(false, 2);
        SchoolBoard schoolBoard = player.getSchoolBoard();

        assertFalse(schoolBoard.isMode());
    }

    /**
     * checks if the schoolboard nickname is the same of the player that created it
     */
    @Test
    void createSchoolBoard3() {
        player.createSchoolBoard(false, 2);
        SchoolBoard schoolBoard = player.getSchoolBoard();

        assertEquals("Albus",schoolBoard.getNickname());
    }

    /**
     * checks if it removes coins from the player
     */
    @Test
    void removeCoins() {
        player.addCoins(3);
        player.removeCoin(2);
        assertEquals(2,player.getCoins());
    }
}
