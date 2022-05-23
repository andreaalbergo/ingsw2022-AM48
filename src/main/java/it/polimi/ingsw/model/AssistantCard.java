package it.polimi.ingsw.model;

/**
 * AssistantCard enumeration is...complete it.
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

    AssistantCard(int value, int number_of_steps) {
        this.value = value;
        this.number_of_steps = number_of_steps;
    }

    public int getValue() {
        return value;
    }


    public int getNumber_of_steps() {
        return number_of_steps;
    }
}


