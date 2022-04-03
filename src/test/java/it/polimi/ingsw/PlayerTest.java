package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import java.text.Format;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player player = new Player("Albus",1,true,Wizards.WIZARD1,3,TowersColor.GRAY,true);

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
    void setIdPlayerForTurn() {
        player.setIdPlayerForTurn(3);
        assertEquals(player.getIdPlayerForTurn(),3);
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
}