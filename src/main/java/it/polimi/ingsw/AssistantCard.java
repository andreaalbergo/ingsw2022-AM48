package it.polimi.ingsw;

public class AssistantCard {
    private final int value;
    private final int number_of_steps;
    private final String wizard_Color;

    public AssistantCard(int value, int number_of_steps, String wizard_color) {
        this.value = value;
        this.number_of_steps = number_of_steps;
        wizard_Color = wizard_color;
    }

    public int getValue() {
        return value;
    }

    public String getWizard_Color() {
        return wizard_Color;
    }

    public int getNumber_of_steps() {
        return number_of_steps;
    }
}
