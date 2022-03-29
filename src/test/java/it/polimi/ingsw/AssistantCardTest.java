package it.polimi.ingsw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssistantCardTest {

    AssistantCard assistantCard = new AssistantCard(7,4,"Green");
    @Test
    void getValue() {
        assertTrue(assistantCard.getValue()>0);
    }

    @Test
    void getWizard_Color() {
        assertTrue(assistantCard.getWizard_Color()!=null);
    }

    @Test
    void getNumber_of_steps() {
        assertTrue(assistantCard.getNumber_of_steps()>0);
    }
}