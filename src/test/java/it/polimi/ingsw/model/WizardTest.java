package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WizardTest {

    /**
     * checks if the list is created correctly
     */
    @Test
    void createList(){
        Wizard.setLists();
        assertFalse(Wizard.getAvailable().isEmpty());
    }

    /**
     * checks if the choose of the wizard works
     */
    @Test
    void freeList(){
        Wizard.setLists();
        Wizard.choose(Wizard.DRUID);
        Wizard.choose(Wizard.EMIR);
        Wizard.choose(Wizard.SAMURAI);
        Wizard.choose(Wizard.WITCH);

        assertTrue(Wizard.getAvailable().isEmpty());
    }

    /**
     * checks if a wizard is already picked
     */
    @Test
    void alreadyPicked(){
        Wizard.setLists();
        Wizard.choose(Wizard.EMIR);

        assertTrue(Wizard.isAlreadyPicked(Wizard.EMIR));
    }

    /**
     * checks if the input stings corresponds to the wizard
     */
    @Test
    void stringToWizard1(){
        assertEquals(Wizard.SAMURAI, Wizard.parseInput("SAMURAI"));
    }

    /**
     * checks if the input stings corresponds to the wizard
     */
    @Test
    void stringToWizard2(){
        assertEquals(Wizard.DRUID, Wizard.parseInput("DRUID"));
    }

}