package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Wizard class is an enumeration containing the four types of wizards which a player can choose from the unpicked ones.
 *
 * @author David Barb, Andrea Albergo
 */
public enum Wizard {
    DRUID, WITCH, EMIR, OLDSAMURAI;

    private static final List<Wizard> alreadyPicked = new ArrayList<>();
    private static final ArrayList<Wizard> available = new ArrayList<>();

    /** Method setLists empties the alreadyPicked list. */
    public static void setLists() {
        available.clear();
        alreadyPicked.clear();
        available.add(DRUID);
        available.add(WITCH);
        available.add(EMIR);
        available.add(OLDSAMURAI);
    }


    /**
     * Method choose adds the chosen wizard to the alreadyPicked list.
     *
     * @param wizard of type Wizard - the chosen wizard.
     */
    public static void choose(Wizard wizard) {
        alreadyPicked.add(wizard);
        available.remove(wizard);
    }

    /**
     * Method isAlreadyChosen returns if the selected wizard has already been chosen.
     *
     * @param wizard of type Wizard - the chosen wizard.
     * @return boolean true if the wizard has already been chosen.
     */
    public static boolean isAlreadyChosen(Wizard wizard) {
        return !alreadyPicked.contains(wizard);
    }

    /**
     * Method alreadyPicked returns the list of picked wizards.
     *
     * @return List&lt;Wizards&gt; - the already picked wizards.
     */
    public static List<Wizard> alreadyPicked() {
        return alreadyPicked;
    }

    public static ArrayList<Wizard> getAvailable() {
        return available;
    }

    /**
     * Method parseInput parses a type String input for wizard choosing.
     *
     * @param input of type String - the chosen wizard.
     * @return Wizard - the Enum value of the desired wizard.
     */
    public static Wizard parseInput(String input) {
        return Enum.valueOf(Wizard.class, input.toUpperCase());
    }
}

