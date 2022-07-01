package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.InvalidSelection;

import java.util.Arrays;

/**
 * AssistantCard enumeration is an enum class that contains the different AssistantCards and their values.
 *
 * @author Andrea Albergo
 */
public enum AssistantCard {
    ONE(1, 1),
    TWO(2, 1),
    THREE(3, 2),
    FOUR(4, 2),
    FIVE(5, 3),
    SIX(6, 3),
    SEVEN(7, 4),
    EIGHT(8, 4),
    NINE(9, 5),
    TEN(10, 5);

    private final int value;
    private final int number_of_steps;

    /**
     * Constructor AssistantCard to create its instance.
     *
     * @param value of type int.
     * @param number_of_steps of type int.
     */
    AssistantCard(int value, int number_of_steps) {
        this.value = value;
        this.number_of_steps = number_of_steps;
    }

    /**
     * Method parseInput in order to type and parse in the correct enumeration after input.
     *
     * @param nextLine of type String.
     * @return of type AssistantCard.
     * @throws InvalidSelection wrong input.
     */
    public static AssistantCard parseInput(String nextLine) throws InvalidSelection {
        AssistantCard assistantCard = Enum.valueOf(AssistantCard.class,nextLine.toUpperCase());
        if(!Arrays.stream(AssistantCard.values()).toList().contains(assistantCard)){
            throw new InvalidSelection();
        }
        return Enum.valueOf(AssistantCard.class,nextLine.toUpperCase());
    }

    /**
     * Method getValue is a getter.
     *
     * @return of type int.
     */
    public int getValue() {
        return value;
    }

    /**
     * Method getNumber_of_steps() is a getter.
     *
     * @return of type int.
     */
    public int getNumber_of_steps() {
        return number_of_steps;
    }
}


