package it.polimi.ingsw.model;

import it.polimi.ingsw.model.AssistantCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssistantCardTest {

    @Test
    void getValue1() {
        assertEquals(8, AssistantCard.EIGHT.getValue());
    }

    @Test
    void getValue2(){
        assertEquals(10, AssistantCard.TEN.getValue());
    }

    @Test
    void getValue3(){
        assertEquals(3, AssistantCard.THREE.getValue());
    }

   @Test
    void getNumber_of_steps1() {
       assertEquals(4, AssistantCard.EIGHT.getNumber_of_steps());
    }

    @Test
    void getNumber_of_steps2() {
        assertEquals(5, AssistantCard.TEN.getNumber_of_steps());
    }

    @Test
    void getNumber_of_steps3() {
        assertEquals(2, AssistantCard.THREE.getNumber_of_steps());
    }

}
