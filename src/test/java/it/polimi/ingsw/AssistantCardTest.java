package it.polimi.ingsw;

import it.polimi.ingsw.model.AssistantCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssistantCardTest {


    @Test
    void getValue() {
        assertEquals(8, AssistantCard.EIGHT.getValue());
    }

   @Test
    void getNumber_of_steps() {
       assertEquals(4, AssistantCard.EIGHT.getNumber_of_steps());
    }

}
