package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WizardTest {

    @Test
    void createList(){
        Wizard.setLists();
        assertFalse(Wizard.getAvailable().isEmpty());
    }

    @Test
    void freeList(){
        Wizard.setLists();
        Wizard.choose(Wizard.DRUID);
        Wizard.choose(Wizard.EMIR);
        Wizard.choose(Wizard.SAMURAI);
        Wizard.choose(Wizard.WITCH);

        assertTrue(Wizard.getAvailable().isEmpty());
    }

    @Test
    void alreadyPicked(){
        Wizard.setLists();
        Wizard.choose(Wizard.EMIR);

        assertTrue(Wizard.isAlreadyPicked(Wizard.EMIR));
    }

    @Test
    void stringToWizard1(){
        assertEquals(Wizard.SAMURAI, Wizard.parseInput("SAMURAI"));
    }

    @Test
    void stringToWizard2(){
        assertEquals(Wizard.DRUID, Wizard.parseInput("DRUID"));
    }

}