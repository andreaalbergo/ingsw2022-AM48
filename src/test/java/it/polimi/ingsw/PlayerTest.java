package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player player = new Player("Albus",1,true,"Green");

    @Test
    void getNickname() {
        assertTrue(player.getNickname() != null);
    }

    @Test
    void getAssistantCards() {
        assertTrue(player.getAssistantCards() != null);
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
        assertTrue(player.getChosenWizard() != null);
    }
}